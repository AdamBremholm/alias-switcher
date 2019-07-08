package org.adam.aliasswitcher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class AliasesInit {

    @Bean
    public CommandLineRunner initDatabase(Storage storage) {
        return args -> init(storage);
    }

    public void init(Storage storage) {

        Alias privateVpnTokyo = AliasFactory.createEmptyAlias();
        Alias azireOpenStockholm = AliasFactory.createEmptyAlias();
        Alias azireClosedStockholm = AliasFactory.createEmptyAlias();
        Alias WAN = AliasFactory.createEmptyAlias();

        Host adamDesktop = HostFactory.createHost("adam-desktop", "192.168.1.101");
        Host nazgul = HostFactory.createHost("nazgul", "192.168.1.102");
        Host chromecast = HostFactory.createHost("chromecast", "192.168.1.103");
        Host adamOneplus6 = HostFactory.createHost("adam-oneplus6", "192.168.1.105");
        Host adamLaptopLenovo = HostFactory.createHost("adam-laptop-lenovo", "192.168.1.106");
        Host adamSamsungTablet = HostFactory.createHost("adam-samsung-tablet", "192.168.1.108");
        Host philipsTV = HostFactory.createHost("philips-tv", "192.168.1.109");
        Host erikaOneplus5 = HostFactory.createHost("erika-oneplus5", "192.168.1.110");
        Host erikaLaptopAsus = HostFactory.createHost("erika-laptop-asus", "192.168.1.111");


    }


}
