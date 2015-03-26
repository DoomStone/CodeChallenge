package com.tradeshift.model.dao.mapper;

import com.tradeshift.model.dao.MessageDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MessageMapper implements RowMapper<MessageDAO> {

    public MessageDAO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new MessageDAO(
                resultSet.getInt("id"),
                resultSet.getString("message"),
                resultSet.getTimestamp("created"));
    }
}
