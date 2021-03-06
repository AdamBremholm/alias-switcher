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

       /* Host adamDesktop = HostFactory.createHost("adam-desktop", "192.168.1.101");
        Host nazgul = HostFactory.createHost("nazgul", "192.168.1.102");
        Host chromecast = HostFactory.createHost("chromecast", "192.168.1.103");
        Host adamOneplus6 = HostFactory.createHost("adam-oneplus6", "192.168.1.105");
        Host adamLaptopLenovo = HostFactory.createHost("adam-laptop-lenovo", "192.168.1.106");
        Host adamSamsungTablet = HostFactory.createHost("adam-samsung-tablet", "192.168.1.108");
        Host philipsTV = HostFactory.createHost("philips-tv", "192.168.1.109");
        Host erikaOneplus5 = HostFactory.createHost("erika-oneplus5", "192.168.1.110");
        Host erikaLaptopAsus = HostFactory.createHost("erika-laptop-asus", "192.168.1.111");

        Alias privateVpnTokyo = AliasFactory.createAlias("privateVpnTokyo");
        Alias azireOpenStockholm = AliasFactory.createAlias("azireOpenStockholm");
        Alias azireClosedStockholm = AliasFactory.createAlias("azireClosedStockholm");
        Alias privateVpnNyc1 = AliasFactory.createAlias("privateVpnNyc1");
        Alias wan = AliasFactory.createAlias("wan");

        privateVpnTokyo.add(philipsTV);
        privateVpnTokyo.add(chromecast);
        azireClosedStockholm.add(nazgul);
        azireClosedStockholm.add(adamDesktop);
        azireClosedStockholm.add(adamOneplus6);
        wan.add(adamLaptopLenovo);
        wan.add(adamSamsungTablet);
        wan.add(erikaOneplus5);
        wan.add(erikaLaptopAsus);

        aliasRepository.save(privateVpnTokyo);
        aliasRepository.save(azireOpenStockholm);
        aliasRepository.save(azireClosedStockholm);
        aliasRepository.save(privateVpnNyc1);
        aliasRepository.save(wan);*/


    }


}
