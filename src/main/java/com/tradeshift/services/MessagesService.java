package com.tradeshift.services;

import com.tradeshift.models.dtos.MessageDTO;
import com.tradeshift.repositoreis.MessagesDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class MessagesService {

    private MessagesDAO dao;

    @Autowired
    public MessagesService(MessagesDAO dao){
        this.dao = dao;
    }

    public void insert(String message) throws IllegalArgumentException {
        if(message == null || message.trim().length() == 0){
            throw new IllegalArgumentException("Message can not be empty or null");
        }
        this.dao.insert(message, new Date());
    }

    public List<MessageDTO> getMessage(int limit){
        return this.dao.getMessage(limit);
    }
}
