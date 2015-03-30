package com.tradeshfit.model.dao;

import com.tradeshift.model.dto.MessageDTO;

import java.util.Date;
import java.util.List;

public interface MessagesDAO {

    public void insert(String message, Date created);
    public List<MessageDTO> getMessage(int limit);
}
