package com.wt.model;

import java.io.Serializable;

/**
 * 消息中间件的点赞信息封装类
 * @author WuTong
 * @create 2019-09-19 15:39
 */
public class MessageDemo implements Serializable {
    private int userId;
    private int moodId;

    public MessageDemo(int userId, int moodId) {
        this.userId = userId;
        this.moodId = moodId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }
}
