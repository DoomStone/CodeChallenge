package com.tradeshfit.resources.api;

import com.tradeshift.repositoreis.MessagesDAO;
import com.tradeshift.models.dtos.MessageDTO;
import com.tradeshift.models.HelloResult;
import com.tradeshift.models.RecentResult;
import com.tradeshift.resources.api.MessagesResource;
import com.tradeshift.services.HelloService;
import com.tradeshift.services.MessagesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class MessagesResourceTest {

    @Mock
    private MessagesDAO messagesDAO;
    @Mock
    private HelloService helloService;
    @Mock
    private MessagesResource resource;


    private MessagesService messagesService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.messagesService = new MessagesService(this.messagesDAO);
        this.resource = new MessagesResource(this.helloService, this.messagesService);
        when(this.helloService.formatName("Kasper")).thenReturn("Hello Kasper");
    }

    @Test
    public void testConstructor(){
        new MessagesResource();
    }

    @Test
    public void testNullName(){
        try{
            resource.names(null);
            Assert.fail();
        }
        catch (Exception exp){
            Assert.assertTrue(true);
        }
        verify(this.messagesDAO, never()).insert(any(String.class), any(Date.class));
    }

    @Test
    public void testWidespaceName(){
        try{
            resource.names("   ");
            Assert.fail();
        }
        catch (Exception exp){
            Assert.assertTrue(true);
        }
        verify(this.messagesDAO, never()).insert(any(String.class), any(Date.class));
    }

    @Test
    public void testNormalName(){
        HelloResult result = resource.names("Kasper");
        Assert.assertEquals(result.getMessage().getContent(), "Hello Kasper");
        verify(this.messagesDAO, times(1)).insert(eq("Hello Kasper"), any(Date.class));
    }

    @Test
    public void testRecentNoReturn(){
        RecentResult result = resource.recent();
        Assert.assertEquals(0, result.getMessages().size());
        Assert.assertNull(result.getLastMessage());
        Assert.assertEquals(0, result.getMessageCount());
        verify(this.messagesDAO, times(1)).getMessage(anyInt());
    }

    @Test
    public void testRecentFourReturn(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -5);
        List<MessageDTO> mockList = new ArrayList<MessageDTO>();
        mockList.add(new MessageDTO(1, "Test message 1", cal.getTime()));
        cal.add(Calendar.HOUR, 1);
        mockList.add(new MessageDTO(1, "Test message 2", cal.getTime()));
        cal.add(Calendar.HOUR, 2);
        mockList.add(new MessageDTO(1, "Test message 3", cal.getTime()));
        cal.add(Calendar.HOUR, 3);
        mockList.add(new MessageDTO(1, "Test message 4", cal.getTime()));
        when(this.messagesDAO.getMessage(10)).thenReturn(mockList);

        RecentResult result = resource.recent();
        Assert.assertEquals(4, result.getMessages().size());
        Assert.assertNotNull(result.getLastMessage());
        Assert.assertEquals(4, result.getMessageCount());
        verify(this.messagesDAO, times(1)).getMessage(anyInt());
        Assert.assertEquals(result.getLastMessage().getTime(), cal.getTime().getTime());
    }
}
