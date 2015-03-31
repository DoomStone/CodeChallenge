package com.tradeshift;

import com.tradeshift.repositoreis.MessagesDAO;
import com.tradeshift.repositoreis.SpringMessagesDAO;
import com.tradeshift.services.EnglishHelloService;
import com.tradeshift.services.HelloService;
import com.tradeshift.services.MessagesService;
import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ApplicationContext {

    @Bean
    public HelloService getHelloService(){
        return new EnglishHelloService();
    }

    @Bean
    public MessagesDAO getMessagesDAO(){
        return new SpringMessagesDAO(getDataSource());
    }

    @Bean
    public MessagesService getMessagesService(){
        return new MessagesService(getMessagesDAO());
    }

    @Bean
    public DataSource getDataSource(){
        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setDataSourceName("The Data Source of pure awesomeness");
        source.setServerName("192.168.1.222");
        source.setDatabaseName("CodeChallenge");
        source.setUser("porta");
        source.setPassword("porta");
        source.setMaxConnections(10);
        return source;
    }
}
