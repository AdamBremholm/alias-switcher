package org.adam.aliasswitcher.domain;

import org.adam.aliasswitcher.domain.Host;

public class HostFactory {

    public static Host createHost(String name, String ip){
        return new Host(name, ip);
    }
}
