package com.tradeshift.model.dao;

import java.util.List;

public interface MessagesDAO {

    public void insert(String message);
    public List<MessageDAO> getMessage(int limit);
}
