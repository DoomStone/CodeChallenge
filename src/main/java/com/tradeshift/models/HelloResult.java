package com.tradeshift.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class HelloResult {

    private HelloResult(){
    }
    public HelloResult(MessageModel messageModel) {
        this.message = messageModel;
    }

    public MessageModel getMessage() {
        return this.message;
    }

    // The doc requires the m to be a capital M
    @XmlElement(name = "Message")
    private MessageModel message;
}
