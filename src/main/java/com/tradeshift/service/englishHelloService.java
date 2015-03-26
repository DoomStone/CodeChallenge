package com.tradeshift.service;

public class EnglishHelloService implements HelloService {

    @Override
    public String formatName(String name){
        return "Hello " + name;
    }

}
