package com.asche.wetalk.entity;

import java.util.Date;

public class ConfirmationToken {

    private Integer id;
    private String token;
    private Date date;
    private Integer userId;

    public ConfirmationToken() {
        date = new Date();
    }

    public ConfirmationToken(Integer userId) {
        this.userId = userId;
        date = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
