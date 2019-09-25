package com.wt.model;

import java.util.Date;

/**
 * @author WuTong
 * @create 2019-09-24 10:44
 */
public class Comment {
    private int id;
    private int moodid;
    private String comment;
    private int userid;
    private Date createtime;

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoodid() {
        return moodid;
    }

    public void setMoodid(int moodid) {
        this.moodid = moodid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", moodid=" + moodid +
                ", comment='" + comment + '\'' +
                ", userid=" + userid +
                ", createtime=" + createtime +
                '}';
    }
}
