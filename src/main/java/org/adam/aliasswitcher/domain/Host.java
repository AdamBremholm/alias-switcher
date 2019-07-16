package org.adam.aliasswitcher.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity(name = "Host")
@Table(name = "host")
public class Host {

    @Id
    @GeneratedValue
    private Long id;

    private String address;
    private String name;

    public Host(String name, String address) {
        this.address = address;
        this.name = name;
    }

    public Host() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host = (Host) o;
        return Objects.equals(getId(), host.getId()) &&
                Objects.equals(getAddress(), host.getAddress()) &&
                Objects.equals(getName(), host.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress(), getName());
    }
}
