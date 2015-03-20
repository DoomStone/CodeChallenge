package com.tradeshift.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class helloResult {

    // The doc requires the m to be a capital M
    @XmlElement(name = "Message")
    public messageModel getMessage() {
        return this.message;
    }

    public void setMessage(messageModel message) {
        this.message = message;
    }

    private messageModel message;
}
