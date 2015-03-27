package com.tradeshfit.model.dao;

import java.util.Date;
import java.util.List;

public interface MessagesDAO {

    public void insert(String message, Date created);
    public List<MessageDAO> getMessage(int limit);
}
