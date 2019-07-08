package org.adam.aliasswitcher;

public class AliasException extends RuntimeException {

    String s;

    public AliasException(String s) {
        this.s = s;

    }
}