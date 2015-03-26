package com.tradeshift.service;

public class EnglishHelloService implements HelloService {

    @Override
    public String formatName(String name) throws NullPointerException {
        if(name == null || name.trim().length() == 0) {
            throw new NullPointerException("Name can not be null or empty");
        }
        return "Hello " + name;
    }

}
