package org.adam.aliasswitcher;

import org.adam.aliasswitcher.domain.Alias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface AliasRepository extends JpaRepository<Alias, Long> {

    List<Alias> findByName(@Param("name") String name);


}