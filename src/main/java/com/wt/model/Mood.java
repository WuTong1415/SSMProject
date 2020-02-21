package com.wt.model;

import java.util.Date;

public class Mood {
    private int id;

    private String content;

    private int userId;

    private Date publishTime;

    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }


    @Override
    public String toString() {
        return "Mood{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", publishTime=" + publishTime +
                ", img='" + img + '\'' +
                '}';
    }
}