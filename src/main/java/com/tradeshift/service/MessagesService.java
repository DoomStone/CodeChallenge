package com.tradeshift.service;

import com.tradeshift.model.dto.MessageDTO;
import com.tradeshfit.model.dao.MessagesDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class MessagesService {

    private MessagesDAO dao;

    @Autowired
    public MessagesService(MessagesDAO dao){
        this.dao = dao;
    }

    public void insert(String message){
        if(message == null || message.trim().length() == 0){
            throw new NullPointerException("Message can not be empty or null");
        }
        this.dao.insert(message, new Date());
    }

    public List<MessageDAO> getMessage(int limit){
        return this.dao.getMessage(limit);
    }
}
