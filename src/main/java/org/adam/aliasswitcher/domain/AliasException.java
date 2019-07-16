package org.adam.aliasswitcher.domain;

public class AliasException extends RuntimeException {

    String s;

    public AliasException(String s) {
        this.s = s;

    }
}