package com.example.chat;

public class ChatMessage {
    private String name;
    private String receiver;
    private String content;

    public ChatMessage() {
    }

    public ChatMessage(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public ChatMessage(String name, String receiver, String content) {
        this.name = name;
        this.receiver = receiver;
        this.content = content;
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
}
