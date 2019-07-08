package org.adam.aliasswitcher;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

import java.util.List;

@Data
@Entity
public class Alias {

    private @Id @GeneratedValue Long id;
    @OneToMany(targetEntity=Host.class, mappedBy = "name", fetch=FetchType.EAGER)
    private @NonNull List<Host> hosts;
    private @NonNull String name;

    public Alias(String name, List<Host> hosts) {
        this.name = name;
        this.hosts = hosts;
    }

    public Alias() {
    }

    public void add(Host host) {
        hosts.add(host);
    }
}


