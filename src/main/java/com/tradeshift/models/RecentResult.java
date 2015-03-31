package com.tradeshift.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class RecentResult {
    @XmlElement
    private Date lastMessage;
    @XmlElement
    private int messageCount;
    @XmlElement
    private List<HelloResult> messages;

    public RecentResult(){
        this.lastMessage = null;
        this.messageCount = 0;
        this.messages = new ArrayList<HelloResult>();
    }

    public Date getLastMessage() {
        return lastMessage;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public List<HelloResult> getMessages() {
        return messages;
    }

    public void addMessage(String content, Date created){
        if(created != null &&
                (this.lastMessage == null || created.after(this.lastMessage))){
            this.lastMessage = created;
        }
        this.messageCount++;
        this.messages.add(new HelloResult(new MessageModel(content)));
    }
}
