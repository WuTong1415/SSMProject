package com.wt.Service.ServiceImpl;

import com.wt.Service.CommentService;
import com.wt.dao.CommentDao;
import com.wt.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-24 10:47
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Override
    public void insertComment(Comment comment) {
        commentDao.insertComment(comment);
    }

    @Override
    public List<Comment> selectcommentByid(int id) {
        return commentDao.selectcommentByid(id);
    }

    @Override
    public void deletecomment(int commentid) {
        commentDao.deletecomment(commentid);
    }
}
