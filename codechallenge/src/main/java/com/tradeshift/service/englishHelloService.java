package com.tradeshift.service;

import org.springframework.stereotype.Service;

/**
 * Created by Kasper on 23/03/15.
 */
public class EnglishHelloService implements HelloService {

    @Override
    public String formatName(String name){
        return "Hello " + name;
    }

}
