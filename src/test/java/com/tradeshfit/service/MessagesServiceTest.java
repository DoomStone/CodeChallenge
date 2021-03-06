package com.tradeshfit.service;

import com.tradeshift.models.dtos.MessageDTO;
import com.tradeshift.repositoreis.MessagesDAO;
import com.tradeshift.services.MessagesService;
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
    public void testZeroItemFetch(){
        MessagesDAO dao = mock(MessagesDAO.class);
        when(dao.getMessage(10)).thenReturn(new ArrayList<MessageDTO>());
        MessagesService service = new MessagesService(dao);
        List<MessageDTO> result = service.getMessage(10);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void testOneItemFetch(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -5);
        MessagesDAO dao = mock(MessagesDAO.class);
        List<MessageDTO> mockList = new ArrayList<MessageDTO>();
        mockList.add(new MessageDTO(1, "Test message 1", cal.getTime()));

        when(dao.getMessage(10)).thenReturn(mockList);

        MessagesService service = new MessagesService(dao);
        List<MessageDTO> result = service.getMessage(10);

        Assert.assertEquals(result.size(), 1);
    }

    @Test
    public void testNormalName(){
        MessagesDAO dao = mock(MessagesDAO.class);

        MessagesService service = new MessagesService(dao);
        service.insert("Kasper");
        verify(dao).insert(eq("Kasper"), any(Date.class));
    }

    @Test
    public void testNullName(){
        MessagesDAO dao = mock(MessagesDAO.class);

        MessagesService service = new MessagesService(dao);
        try{
            service.insert(null);
            Assert.fail();
        } catch (IllegalArgumentException exp){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testWidespaceName(){
        MessagesDAO dao = mock(MessagesDAO.class);

        MessagesService service = new MessagesService(dao);
        try{
            service.insert("     ");
            Assert.fail();
        } catch (IllegalArgumentException exp){
            Assert.assertTrue(true);
        }
    }
}
