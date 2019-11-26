package com.org.home.exception;

public class ZipCodeNotFoundException extends RuntimeException {
    public ZipCodeNotFoundException(String msg) {
        super(msg);
    }
}
