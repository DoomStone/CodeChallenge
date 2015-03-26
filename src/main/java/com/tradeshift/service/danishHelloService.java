package com.tradeshift.service;

import org.jvnet.hk2.annotations.Service;

/**
 * Created by Kasper on 23/03/15.
 */
public class DanishHelloService implements HelloService {

    @Override
    public String formatName(String name) {
        return "Hej " + name;
    }
}
