package com.tradeshift.model.dao;

import com.tradeshift.model.dao.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public class SpringMessagesDAO implements MessagesDAO {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public SpringMessagesDAO(){

    }
    @Autowired
    public SpringMessagesDAO(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void insert(String message) {
        String insertSql = "INSERT INTO recent (message, created) VALUES (?, ?)";
        jdbcTemplateObject.update(insertSql, message, new Date());
    }

    @Override
    public List<MessageDAO> getMessage(int limit) {
        String sql = "SELECT * FROM recent ORDER BY id DESC LIMIT ?";
        return jdbcTemplateObject.query(sql, new Object[] { limit }, new MessageMapper());
    }
}
