package com.tradeshift.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HelloResult {

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
