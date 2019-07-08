package org.adam.aliasswitcher;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class Alias {

    private List<Host> hosts;

    public Alias(List<Host> hosts) {
        this.hosts = hosts;
    }

    public Alias() {
    }
}


