package com.tradeshfit;

import com.tradeshfit.model.dao.MessageDAO;
import com.tradeshfit.model.dao.MessagesDAO;
import com.tradeshift.HelloResource;
import com.tradeshift.model.HelloResult;
import com.tradeshift.model.RecentResult;
import com.tradeshift.service.HelloService;
import com.tradeshift.service.MessagesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class HelloResourceTest {

    private MessagesDAO messagesDAO;
    private MessagesService messagesService;
    private HelloService helloService;
    private HelloResource resource;

    @Before
    public void SetUp(){
        this.messagesDAO = mock(MessagesDAO.class);
        this.messagesService = new MessagesService(messagesDAO);
        this.helloService = mock(HelloService.class);
        this.resource = new HelloResource(helloService, messagesService);

        when(this.helloService.formatName("Kasper")).thenReturn("Hello Kasper");
    }

    @Test
    public void TestNullName(){
        HelloResult result = resource.names(null);
        Assert.assertEquals(result.getMessage().getContent(), "Error: Name can not be empty");
        verify(this.messagesDAO, never()).insert(any(String.class), any(Date.class));
    }

    @Test
    public void TestWidespaceName(){
        HelloResult result = resource.names("      ");
        Assert.assertEquals(result.getMessage().getContent(), "Error: Name can not be empty");
        verify(this.messagesDAO, never()).insert(any(String.class), any(Date.class));
    }

    @Test
    public void TestNormalName(){
        HelloResult result = resource.names("Kasper");
        Assert.assertEquals(result.getMessage().getContent(), "Hello Kasper");
        verify(this.messagesDAO, times(1)).insert(eq("Hello Kasper"), any(Date.class));
    }

    @Test
    public void TestRecentNoReturn(){
        RecentResult result = resource.recent();
        Assert.assertEquals(result.getMessages().size(), 0);
        Assert.assertEquals(result.getLastMessage(), null);
        Assert.assertEquals(result.getMessageCount(), 0);
        verify(this.messagesDAO, times(1)).getMessage(any(int.class));
    }

    @Test
    public void TestRecentFourReturn(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -5);
        List<MessageDAO> mockList = new ArrayList<MessageDAO>();
        mockList.add(new MessageDAO(1, "Test message 1", cal.getTime()));
        cal.add(Calendar.HOUR, 1);
        mockList.add(new MessageDAO(1, "Test message 2", cal.getTime()));
        cal.add(Calendar.HOUR, 2);
        mockList.add(new MessageDAO(1, "Test message 3", cal.getTime()));
        cal.add(Calendar.HOUR, 3);
        mockList.add(new MessageDAO(1, "Test message 4", cal.getTime()));
        when(this.messagesDAO.getMessage(10)).thenReturn(mockList);

        RecentResult result = resource.recent();
        Assert.assertEquals(result.getMessages().size(), 4);
        Assert.assertNotEquals(result.getLastMessage(), null);
        Assert.assertEquals(result.getMessageCount(), 4);
        verify(this.messagesDAO, times(1)).getMessage(any(int.class));
        Assert.assertEquals(result.getLastMessage().getTime(), cal.getTime().getTime());
    }
}