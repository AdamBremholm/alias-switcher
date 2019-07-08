package org.adam.aliasswitcher;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    public Alias getOne(@PathVariable Long id){
        return storage.findById(id)
                .orElseThrow( () -> new AliasException("No alias with id " + id));
    }


}
