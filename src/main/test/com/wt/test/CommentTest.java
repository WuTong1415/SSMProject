package com.wt.test;

import com.wt.Service.CommentService;
import com.wt.model.Comment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-24 15:01
 */
public class CommentTest extends BaseJunit4Test{
    @Autowired
    private CommentService commentService;
    @Test
    public void addComment(){
        Comment comment = new Comment();
        comment.setComment("辣是真滴");
        comment.setCreatetime(new Date());
        comment.setMoodid(1);
        comment.setUserid(1);
        System.out.println(comment.toString());
        commentService.insertComment(comment);
    }
    @Test
    public void findall(){
        List<Comment> comments = commentService.selectcommentByid(1);
        for (Comment comment : comments) {
            System.out.println(comment.toString());
        }

    }
}
