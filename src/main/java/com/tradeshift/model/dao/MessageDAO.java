package com.tradeshift.model.dao;

import java.util.Date;

public class MessageDAO {
    private int id;
    private String message;
    private Date created;

    public MessageDAO(){

    }
    public MessageDAO(int id, String message, Date created) {
        this.id = id;
        this.message = message;
        this.created = created;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
