package com.wt.Service;

import com.wt.model.Comment;

import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-24 10:46
 */
public interface CommentService {
    void insertComment(Comment comment);
    List<Comment> selectcommentByid(int id);

    void deletecomment(int commentid);
}
