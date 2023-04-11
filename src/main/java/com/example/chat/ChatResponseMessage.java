package com.example.chat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ChatResponseMessage {
    private String name;
    private String receiver;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    public ChatResponseMessage() {
    }

    public ChatResponseMessage(String name, String content, Date sendTime) {
        this.name = name;
        this.content = content;
        this.sendTime = sendTime;
    }

    public ChatResponseMessage(String name, String receiver, String content, Date sendTime) {
        this.name = name;
        this.receiver = receiver;
        this.content = content;
        this.sendTime = sendTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
