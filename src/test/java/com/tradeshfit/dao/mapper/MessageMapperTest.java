package com.tradeshfit.dao.mapper;

import com.tradeshift.model.dto.MessageDTO;
import com.tradeshfit.model.dao.mapper.MessageMapper;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import static org.mockito.Mockito.*;

public class MessageMapperTest {
    @Test
    public void TestMapperCraeted(){
        try {
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
        catch (Exception exp){
            Assert.assertTrue(false);
        }
    }
}
