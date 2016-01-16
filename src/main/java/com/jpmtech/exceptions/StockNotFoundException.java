package com.jpmtech.exceptions;

public class StockNotFoundException extends RuntimeException {

    public StockNotFoundException(String s) {
        super(s);
    }
}
