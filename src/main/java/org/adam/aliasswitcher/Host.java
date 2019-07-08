package org.adam.aliasswitcher;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Host {

    private @Id @GeneratedValue Long id;
    private String address;
    private String name;

    public Host(String address, String name) {
        this.address = address;
        this.name = name;
    }

    public Host() {
    }
}
