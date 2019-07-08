package org.adam.aliasswitcher;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
public class AliasController {

    Storage storage;
    RestTemplate restTemplate;


    public AliasController(Storage storage) {
        this.storage = storage;
    }

    @GetMapping("/aliases")
    public List<Alias> getAll(){
        return storage.findAll();
    }

    @GetMapping("/aliases/{id}")
    public List<Host> getOne(@PathVariable Long id){
        Alias alias = storage.findById(id)
                .orElseThrow( () -> new AliasException("No alias with id " + id));

        return alias.getHosts();
    }
  //t.ex: alias?name=azireClosedStockholm
    @GetMapping("/alias")
    public String getIps(@RequestParam String name){


       Alias aliasExample = new Alias(name);
        Example<Alias> example = Example.of(aliasExample);

        List<Alias> aliases = storage.findAll(example);

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
