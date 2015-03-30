package com.tradeshift.service;

public class EnglishHelloService implements HelloService {

    @Override
    public String formatName(String name) throws IllegalArgumentException {
        if(name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("Name can not be null or empty");
        }
        return "Hello " + name;
    }

}
