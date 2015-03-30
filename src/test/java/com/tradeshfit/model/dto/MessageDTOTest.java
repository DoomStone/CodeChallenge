package com.tradeshfit.model.dto;

import com.tradeshift.model.dto.MessageDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class MessageDTOTest {

    @Test
    public void testGetter(){
        String message = "test message";
        int id = 123;
        Date created = new Date();

        MessageDTO dto = new MessageDTO(id, message, created);
        Assert.assertEquals(message, dto.getMessage());
        Assert.assertEquals(id, dto.getId());
        Assert.assertEquals(created, dto.getCreated());
    }

    @Test
    public void testSetters(){
        String message = "test message";
        int id = 123;
        Date created = new Date();

        MessageDTO dto = new MessageDTO();
        dto.setMessage(message);
        Assert.assertEquals(message, dto.getMessage());
        dto.setId(id);
        Assert.assertEquals(id, dto.getId());
        dto.setCreated(created);
        Assert.assertEquals(created, dto.getCreated());
    }

}
