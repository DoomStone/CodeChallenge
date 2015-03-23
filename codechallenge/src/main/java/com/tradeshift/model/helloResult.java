package com.tradeshift.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HelloResult {

    // The doc requires the m to be a capital M
    @XmlElement(name = "Message")
    public MessageModel getMessage() {
        return this.message;
    }

    public void setMessage(MessageModel message) {
        this.message = message;
    }

    private MessageModel message;
}
