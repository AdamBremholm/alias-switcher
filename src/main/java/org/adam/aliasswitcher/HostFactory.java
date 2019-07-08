package org.adam.aliasswitcher;

public class HostFactory {

    public static Host createHost(String name, String ip){
        return new Host(name, ip);
    }
}
