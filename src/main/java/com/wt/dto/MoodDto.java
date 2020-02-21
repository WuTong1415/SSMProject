package com.wt.dto;

import com.wt.model.Mood;

import java.util.List;
import java.util.Set;

/**
 * @author WuTong
 * @create 2019-09-09 19:17
 */
public class MoodDto extends Mood {
    private String userName;
    private String userAccount;
    private List<String> praiseNames;
    private List<CommentDto> commentList;

    public List<String> getPraiseNames() {
        return praiseNames;
    }

    public void setPraiseNames(List<String> praiseNames) {
        this.praiseNames = praiseNames;
    }

    public List<CommentDto> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDto> commentList) {
        this.commentList = commentList;
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
