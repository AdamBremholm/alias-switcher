package org.adam.aliasswitcher;

import java.util.ArrayList;
import java.util.List;

public class AliasFactory {

    public static Alias createEmptyAlias() {

        List<Host> hosts = new ArrayList<>();
        Alias alias = new Alias();
        alias.setHosts(hosts);
        return alias;
    }


}
