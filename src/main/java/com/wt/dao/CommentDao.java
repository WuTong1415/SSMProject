package com.wt.dao;

import com.wt.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-24 10:47
 */
@Repository
public interface CommentDao {
    /**
     * 将评论存入数据库
     * @param comment 评论
     */
    void insertComment(Comment comment);

    /**
     * 根据说说的ID查找评论
     * @param id 评论ID
     * @return 评论集合
     */
    List<Comment> selectCommentsByMoodId(int id);

    /**
     * 删除评论
     * @param commentId 评论ID
     */
    void deleteComment(int commentId);
}
