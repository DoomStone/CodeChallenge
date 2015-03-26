package com.tradeshift.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.NONE)
public class MessageModel {

    private  MessageModel(){
    }
    public MessageModel(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @XmlElement(name = "Content")
    private String content;
}
