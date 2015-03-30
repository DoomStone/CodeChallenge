package com.tradeshfit.model.dao;

import com.tradeshfit.model.dao.mapper.MessageMapper;
import com.tradeshift.model.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

public class SpringMessagesDAO implements MessagesDAO {

    private JdbcTemplate jdbcTemplateObject;

    public SpringMessagesDAO(){

    }
    @Autowired
    public SpringMessagesDAO(DataSource dataSource){
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    @Autowired
    public SpringMessagesDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplateObject = jdbcTemplate;
    }


    public void insert(String message, Date created) {
        String insertSql = "INSERT INTO recent (message, created) VALUES (?, ?)";
        jdbcTemplateObject.update(insertSql, message, created);
    }

    public List<MessageDTO> getMessage(int limit) {
        String sql = "SELECT * FROM recent ORDER BY id DESC LIMIT ?";
        return jdbcTemplateObject.query(sql, new Object[] { limit }, new MessageMapper());
    }
}
