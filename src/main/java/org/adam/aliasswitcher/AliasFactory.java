package org.adam.aliasswitcher;

import java.util.ArrayList;
import java.util.List;

public class AliasFactory {

    public static Alias createAlias(String name) {

        List<Host> hosts = new ArrayList<>();
        Alias alias = new Alias();
        alias.setHosts(hosts);
        return alias;
    }


}
