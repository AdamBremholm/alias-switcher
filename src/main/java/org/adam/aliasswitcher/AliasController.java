package org.adam.aliasswitcher;



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



    public AliasController(AliasRepository aliasRepository) {
        this.aliasRepository = aliasRepository;

        updatePfSenseAliases();
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


    public void updatePfSenseAliases() {

        //Gör en unsecure connection för att skippa certifcateexception
        RestTemplate restTemplate = null;
        try {restTemplate = getInsecureRestTemplate();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        HttpEntity<String> entity = getAcceptJsonAndAuthedEntity();

        ResponseEntity<String> response = null;
        try {
             response = restTemplate
                    .exchange(pfsenseUrl + "/fauxapi/v1/?action=alias_update_urltables", HttpMethod.GET, entity, String.class);

        } catch (RestClientException e) {
            e.printStackTrace();
        } finally {
            System.out.println("response: " + response);
        }

    }

    private HttpEntity<String> getAcceptJsonAndAuthedEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("fauxapi-auth", Auth.fauxapiAuth());
        return new HttpEntity<String>(headers);
    }

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
