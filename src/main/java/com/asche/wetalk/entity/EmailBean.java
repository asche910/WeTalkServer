package com.asche.wetalk.entity;

public class EmailBean {
    private String from;
    private String to;
    private String name;
    private String subject;
    private String messageText;

    public EmailBean() {
    }

    public EmailBean(String from, String to, String name, String subject, String messageText) {
        this.from = from;
        this.to = to;
        this.name = name;
        this.subject = subject;
        this.messageText = messageText;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
}
