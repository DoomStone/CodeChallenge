package com.tradeshfit.repositories.mappers;

import com.tradeshift.models.dtos.MessageDTO;
import com.tradeshift.repositoreis.mappers.MessageMapper;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import static org.mockito.Mockito.*;

public class MessageMapperTest {
    @Test
    public void testMapperCraeted() throws SQLException {
        ResultSet resultSet = mock(ResultSet.class);
        Date date = new Date();
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("message")).thenReturn("Hello Kasper");
        when(resultSet.getTimestamp("created")).thenReturn(new Timestamp(date.getTime()));

        MessageDTO message = new MessageMapper().mapRow(resultSet, 1);
        verify(resultSet, times(1)).getInt("id");
        verify(resultSet, times(1)).getString("message");
        verify(resultSet, times(1)).getTimestamp("created");

        Assert.assertEquals(message.getMessage(), "Hello Kasper");
    }
}
