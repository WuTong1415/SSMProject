package com.wt.service;

import com.wt.dao.CommentDao;
import com.wt.dao.MoodDao;
import com.wt.model.Comment;
import com.wt.model.Mood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CacheEvict(value = "comment",key = "'comment:'+#comment.moodid")
    public Comment insertComment(Comment comment) {

        commentDao.insertComment(comment);
        return comment;
    }

    /**
     * 根据说说的ID查找评论
     *
     * @param moodId 评论ID
     * @return 评论集合
     */
    @Cacheable(value = "comment",key = "'comment:'+#moodId")
    public List<Comment> selectCommentsByMoodId(int moodId) {
        return commentDao.selectCommentsByMoodId(moodId);
    }

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     */
    @CacheEvict(value = "comment",key = "'comment:'+#commentId")
    public void deleteComment(int commentId) {
        commentDao.deleteComment(commentId);
    }

    /**
     * 删除动态的所有评论
     * @param moodId 动态ID
     */
    @CacheEvict(value = "comment",key = "'comment:'+#moodId")
    public void deleteCommentsByMoodId(int moodId) {
        commentDao.deleteCommentsByMoodId(moodId);
    }


}
