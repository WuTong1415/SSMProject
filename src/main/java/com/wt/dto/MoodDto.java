package com.wt.dto;

import com.wt.model.Comment;
import com.wt.model.Mood;

import java.io.Serializable;
import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-09 19:17
 */
public class MoodDto extends Mood {
    private String userName;
    private String userAccount;
    private List<String> praisenames;
    private List<CommentDto> commentList;

    public List<CommentDto> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDto> commentList) {
        this.commentList = commentList;
    }

    public List<String> getPraisenames() {
        return praisenames;
    }

    public void setPraisenames(List<String> praisenames) {
        this.praisenames = praisenames;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return super.toString() +
                "userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                '}';
    }
}