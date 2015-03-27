package com.tradeshfit.service;

import com.tradeshfit.model.dao.MessageDAO;
import com.tradeshfit.model.dao.MessagesDAO;
import com.tradeshift.service.MessagesService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MessagesServiceTest {

    @Test
    public void TestZeroItemFetch(){
        MessagesDAO dao = mock(MessagesDAO.class);
        when(dao.getMessage(10)).thenReturn(new ArrayList<MessageDAO>());
        MessagesService service = new MessagesService(dao);
        List<MessageDAO> result = service.getMessage(10);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void TestOneItemFetch(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -5);
        MessagesDAO dao = mock(MessagesDAO.class);
        List<MessageDAO> mockList = new ArrayList<MessageDAO>();
        mockList.add(new MessageDAO(1, "Test message 1", cal.getTime()));

        when(dao.getMessage(10)).thenReturn(mockList);

        MessagesService service = new MessagesService(dao);
        List<MessageDAO> result = service.getMessage(10);

        Assert.assertEquals(result.size(), 1);
    }

    @Test
    public void TestNormalName(){
        MessagesDAO dao = mock(MessagesDAO.class);

        MessagesService service = new MessagesService(dao);
        service.insert("Kasper");
        verify(dao).insert(eq("Kasper"), any(Date.class));
    }

    @Test
    public void TestNullName(){
        MessagesDAO dao = mock(MessagesDAO.class);

        MessagesService service = new MessagesService(dao);
        try{
            service.insert(null);
            Assert.assertTrue(false);
        } catch (NullPointerException exp){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void TestWidespaceName(){
        MessagesDAO dao = mock(MessagesDAO.class);

        MessagesService service = new MessagesService(dao);
        try{
            service.insert("     ");
            Assert.assertTrue(false);
        } catch (NullPointerException exp){
            Assert.assertTrue(true);
        }
    }
}