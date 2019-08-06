package org.adam.aliasswitcher.domain;

import lombok.Data;

import javax.persistence.*;

import java.util.List;

@Data
@Entity
public class Alias {

    @Id
    @GeneratedValue
    private  Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Host> hosts;


    public Alias(String name, List<Host> hosts) {
        this.name = name;
        this.hosts = hosts;
    }

    public Alias(String name) {
        this.name = name;
    }

    public Alias() {
    }

    public void add(Host host) {
        hosts.add(host);
    }
}


