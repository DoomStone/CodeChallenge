package com.tradeshfit.dao;

import com.tradeshift.model.dto.MessageDTO;
import com.tradeshfit.model.dao.SpringMessagesDAO;
import com.tradeshfit.model.dao.mapper.MessageMapper;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

public class SpringMessagesDAOTest {

    @Test
    public void testInsert(){
        JdbcTemplate template = mock(JdbcTemplate.class);
        SpringMessagesDAO dao = new SpringMessagesDAO(template);

        Date date = new Date();
        dao.insert("Kasper", date);
        verify(template, Mockito.times(1)).update(any(String.class), eq("Kasper"), eq(date));
    }

    @Test
    public void testGetMessage(){
        JdbcTemplate template = mock(JdbcTemplate.class);
        SpringMessagesDAO dao = new SpringMessagesDAO(template);

        when(template.query(any(String.class), any(Object[].class), any(MessageMapper.class)))
                .thenReturn(new ArrayList<MessageDTO>());

        Date date = new Date();
        dao.getMessage(10);
        verify(template, Mockito.times(1)).query(any(String.class), eq(new Object[]{10}), any(MessageMapper.class));
    }

    @Test
    public void testGetMessageLast100(){
        JdbcTemplate template = mock(JdbcTemplate.class);
        SpringMessagesDAO dao = new SpringMessagesDAO(template);

        when(template.query(any(String.class), any(Object[].class), any(MessageMapper.class)))
                .thenReturn(new ArrayList<MessageDTO>());

        Date date = new Date();
        dao.getMessage(100);
        verify(template, Mockito.times(1)).query(any(String.class), eq(new Object[]{ 100 }), any(MessageMapper.class));
    }
}
