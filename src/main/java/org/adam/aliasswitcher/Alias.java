package org.adam.aliasswitcher;

import lombok.Data;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "Alias")
@Table(name = "alias")
public class Alias {

    @Id
    @GeneratedValue
    private  Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "alias_id")
    private List<Host> hosts;

    private String name;

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


