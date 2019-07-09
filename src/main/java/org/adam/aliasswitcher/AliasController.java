package org.adam.aliasswitcher;


import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
public class AliasController {

    AliasRepository aliasRepository;
    RestTemplate restTemplate;


    public AliasController(AliasRepository aliasRepository) {
        this.aliasRepository = aliasRepository;
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






}
