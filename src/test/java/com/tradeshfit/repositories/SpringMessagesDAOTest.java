package com.tradeshfit.repositories;

import com.tradeshift.repositoreis.SpringMessagesDAO;
import com.tradeshift.models.dtos.MessageDTO;
import com.tradeshift.repositoreis.mappers.MessageMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;

public class SpringMessagesDAOTest {

    @Mock
    private JdbcTemplate template = mock(JdbcTemplate.class);
    private SpringMessagesDAO dao;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        dao = new SpringMessagesDAO(template);
    }

    @Test
    public void testCreateInstance(){
        DataSource dataSource = mock(DataSource.class);
        new SpringMessagesDAO();
        new SpringMessagesDAO(dataSource);
    }

    @Test
    public void testInsert(){
        Date date = new Date();
        dao.insert("Kasper", date);
        verify(template, Mockito.times(1)).update(any(String.class), eq("Kasper"), eq(date));
    }

    @Test
    public void testGetMessage(){
        when(template.query(any(String.class), any(Object[].class), any(MessageMapper.class)))
                .thenReturn(new ArrayList<MessageDTO>());

        Date date = new Date();
        dao.getMessage(10);
        verify(template, Mockito.times(1)).query(any(String.class), eq(new Object[]{10}), any(MessageMapper.class));
    }

    @Test
    public void testGetMessageLast100(){
        when(template.query(any(String.class), any(Object[].class), any(MessageMapper.class)))
                .thenReturn(new ArrayList<MessageDTO>());

        Date date = new Date();
        dao.getMessage(100);
        verify(template, Mockito.times(1)).query(any(String.class), eq(new Object[]{ 100 }), any(MessageMapper.class));
    }
}
