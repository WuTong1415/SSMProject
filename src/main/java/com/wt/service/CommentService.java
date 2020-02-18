package com.wt.service;

import com.wt.dao.CommentDao;
import com.wt.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-24 10:46
 */
@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    /**
     * 将评论存入数据库
     *
     * @param comment 评论
     */
    public void insertComment(Comment comment) {

        commentDao.insertComment(comment);
    }

    /**
     * 根据说说的ID查找评论
     *
     * @param id 评论ID
     * @return 评论集合
     */
    public List<Comment> selectCommentById(int id) {
        return commentDao.selectCommentsByMoodId(id);
    }

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     */
    public void deleteComment(int commentId) {
        commentDao.deleteComment(commentId);
    }
}
