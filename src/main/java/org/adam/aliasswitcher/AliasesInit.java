package org.adam.aliasswitcher;

import org.adam.aliasswitcher.domain.Alias;
import org.adam.aliasswitcher.domain.AliasFactory;
import org.adam.aliasswitcher.domain.Host;
import org.adam.aliasswitcher.domain.HostFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AliasesInit {

    @Bean
    public CommandLineRunner initDatabase(AliasRepository aliasRepository) {
        return args -> init(aliasRepository);
    }

    public void init(AliasRepository aliasRepository) {



    }


}
