package com.tradeshift;

import com.tradeshift.model.dao.JdbcMessagesDAO;
import com.tradeshift.model.dao.MessagesDAO;
import com.tradeshift.model.dao.SpringMessagesDAO;
import com.tradeshift.service.EnglishHelloService;
import com.tradeshift.service.HelloService;
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
    public DataSource getDataSource(){
        PGPoolingDataSource source = new PGPoolingDataSource();
        source.setDataSourceName("The Data Source of pure awesomeness");
        source.setServerName("localhost");
        source.setDatabaseName("CodeChallenge");
        source.setUser("Kasper");
        source.setPassword("D12345");
        source.setMaxConnections(10);
        return source;
    }
}
