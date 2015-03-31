package com.tradeshift.repositoreis;

import com.tradeshift.models.dtos.MessageDTO;

import java.util.Date;
import java.util.List;

public interface MessagesDAO {

    public void insert(String message, Date created);
    public List<MessageDTO> getMessage(int limit);
}
