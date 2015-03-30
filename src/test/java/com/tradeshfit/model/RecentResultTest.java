package com.tradeshfit.model;

import com.tradeshift.model.RecentResult;
import org.junit.Assert;
import org.junit.Test;
import java.util.Calendar;
import java.util.Date;

public class RecentResultTest {

    @Test
    public void addZeroResults(){
        RecentResult result = new RecentResult();
        Assert.assertNull(result.getLastMessage());
        Assert.assertEquals(result.getMessageCount(), 0);
        Assert.assertEquals(result.getMessages().size(), 0);
    }

    @Test
    public void addOneResult(){
        Date first = new Date();
        String testMessage = "this is a test";
        RecentResult result = new RecentResult();
        result.addMessage(testMessage, first);

        Assert.assertNotNull(result.getLastMessage());
        Assert.assertEquals(result.getLastMessage().getTime(), first.getTime());
        Assert.assertEquals(result.getMessages().size(), 1);
        Assert.assertEquals(result.getMessages().get(0).getMessage().getContent(), testMessage);
    }

    @Test
    public void addTwoResult(){
        Date first = new Date();
        String testMessage = "this is a test";
        RecentResult result = new RecentResult();
        result.addMessage(testMessage, first);
        result.addMessage(testMessage, first);

        Assert.assertNotNull(result.getLastMessage());
        Assert.assertEquals(result.getLastMessage().getTime(), first.getTime());
        Assert.assertEquals(result.getMessages().size(), 2);
        Assert.assertEquals(result.getMessages().get(0).getMessage().getContent(), testMessage);
    }

    @Test
    public void addTwoResultAndNull(){
        Date first = new Date();
        String testMessage = "this is a test";
        RecentResult result = new RecentResult();
        result.addMessage(testMessage, first);
        result.addMessage(testMessage, first);
        result.addMessage(testMessage, null);

        Assert.assertNotNull(result.getLastMessage());
        Assert.assertEquals(result.getLastMessage().getTime(), first.getTime());
        Assert.assertEquals(result.getMessages().size(), 3);
        Assert.assertEquals(result.getMessages().get(0).getMessage().getContent(), testMessage);
    }

    @Test
    public void testLatestMessageInOrder(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -1);
        Date first = cal.getTime();
        Date second = new Date();

        String testMessage = "this is a test";
        RecentResult result = new RecentResult();
        result.addMessage(testMessage + " 1", first);
        result.addMessage(testMessage + " 2", second);

        Assert.assertNotNull(result.getLastMessage());
        Assert.assertEquals(result.getLastMessage().getTime(), second.getTime());
        Assert.assertEquals(result.getMessages().size(), 2);
        Assert.assertEquals(result.getMessages().get(0).getMessage().getContent(), testMessage + " 1");
    }

    @Test
    public void testLatestMessageOutOfOrder(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, -1);
        Date first = cal.getTime();
        Date second = new Date();

        String testMessage = "this is a test";
        RecentResult result = new RecentResult();
        result.addMessage(testMessage + " 2", second);
        result.addMessage(testMessage + " 1", first);

        Assert.assertNotNull(result.getLastMessage());
        Assert.assertEquals(result.getLastMessage().getTime(), second.getTime());
        Assert.assertEquals(result.getMessages().size(), 2);
        Assert.assertEquals(result.getMessages().get(0).getMessage().getContent(), testMessage + " 2");
    }
}
