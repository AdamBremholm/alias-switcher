package org.adam.aliasswitcher.controller;



import com.fasterxml.jackson.databind.JsonNode;
import org.adam.aliasswitcher.AliasRepository;
import org.adam.aliasswitcher.auth.Auth;
import org.adam.aliasswitcher.domain.Alias;
import org.adam.aliasswitcher.domain.AliasException;
import org.adam.aliasswitcher.domain.Host;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@EnableWebMvc
public class AliasController {

    AliasRepository aliasRepository;
    String pfsenseUrl = "https://192.168.1.2";
    RestTemplate insecureRestTemplate;



    public AliasController(AliasRepository aliasRepository) {
        this.aliasRepository = aliasRepository;
        this.insecureRestTemplate = null;
        try {
             insecureRestTemplate = getInsecureRestTemplate();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }


  //t.ex: aliases/search/findAddressesByName?name=privateVpnTokyo
    @GetMapping("/aliases/search/findAddressesByName")
    public String getIps(@RequestParam String name){

        List<Alias> aliases = aliasRepository.findByName(name);

        if (aliases.size()<1){
            throw new AliasException("No aliases found named " + name);
        }

        List<Host> hostList = new ArrayList<>();

        for (Alias alias:aliases) {
            hostList.addAll(alias.getHosts());
        }

        StringBuilder ipList = new StringBuilder();

        for (Host host : hostList) {
            ipList.append(host.getAddress()).append(System.lineSeparator());
        }

        return ipList.toString();
    }

    @GetMapping("/aliases/update_urltables")
    public ResponseEntity<String> updatePfSenseAliases() {

        ResponseEntity<String> response = null;
        try {
             response = insecureRestTemplate
                    .exchange(pfsenseUrl + "/fauxapi/v1/?action=alias_update_urltables", HttpMethod.GET, getAcceptJsonAndAuthedEntity(), String.class);

        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return response;


    }

    public void getPfsenseConfig() {

        ResponseEntity<JsonNode> response = null;

        try {
            response = insecureRestTemplate
                    .exchange(pfsenseUrl + "/fauxapi/v1/?action=config_get", HttpMethod.GET, getAcceptJsonAndAuthedEntity(), JsonNode.class);

        } catch (RestClientException e) {
            e.printStackTrace();
        }

        JsonNode jsonNode = response.getBody();
        System.out.println(jsonNode);

    }

    private HttpEntity<String> getAcceptJsonAndAuthedEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("fauxapi-auth", Auth.fauxapiAuth());
        return new HttpEntity<String>(headers);
    }
    //Gör en insecure connection för att skippa certifcateexception
    public RestTemplate getInsecureRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                return true;
            }
        };
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }







}
