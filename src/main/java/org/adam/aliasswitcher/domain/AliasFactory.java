package org.adam.aliasswitcher.domain;

import org.adam.aliasswitcher.domain.Alias;
import org.adam.aliasswitcher.domain.Host;

import java.util.ArrayList;
import java.util.List;

public class AliasFactory {

    public static Alias createAlias(String name) {
        List<Host> hosts = new ArrayList<>();
        return new Alias(name, hosts);
    }


}
