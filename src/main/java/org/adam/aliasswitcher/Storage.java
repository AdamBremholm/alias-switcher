package org.adam.aliasswitcher;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Storage extends JpaRepository<Alias, Long> {

    List<Alias> findByName(String name);

}