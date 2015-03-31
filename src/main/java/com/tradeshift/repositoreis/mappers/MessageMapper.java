package com.tradeshift.repositoreis.mappers;

import com.tradeshift.models.dtos.MessageDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MessageMapper implements RowMapper<MessageDTO> {

    public MessageDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new MessageDTO(
                resultSet.getInt("id"),
                resultSet.getString("message"),
                resultSet.getTimestamp("created"));
    }
}
