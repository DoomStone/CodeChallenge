package com.tradeshift.service;

public class DanishHelloService implements HelloService {

    @Override
    public String formatName(String name) {
        return "Hej " + name;
    }
}
