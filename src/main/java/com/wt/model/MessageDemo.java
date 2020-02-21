package com.wt.model;

import java.io.Serializable;

/**
 * 消息中间件的点赞信息封装类
 *
 * @author WuTong
 * @create 2019-09-19 15:39
 */
public class MessageDemo implements Serializable {
    private String userName;
    private int moodId;

    public MessageDemo(String userName, int moodId) {
        this.userName = userName;
        this.moodId = moodId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }
}
