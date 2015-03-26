package com.tradeshift;

import com.tradeshift.service.EnglishHelloService;
import com.tradeshift.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationContext {

    @Bean
    public HelloService getHelloService(){
        System.out.println("Creating new instance of EnglishHelloService");
        return new EnglishHelloService();
    }
}
