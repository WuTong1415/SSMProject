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
    void insertComment(Comment comment);

    List<Comment> selectcommentByid(int id);

    void deletecomment(int commentid);
}
