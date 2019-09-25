package com.wt.dto;

import com.wt.model.Comment;

/**
 * @author WuTong
 * @create 2019-09-25 15:30
 */
public class CommentDto extends Comment {
    private String friendName;

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }
}
