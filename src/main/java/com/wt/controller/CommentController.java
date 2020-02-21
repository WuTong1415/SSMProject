package com.wt.controller;

import com.wt.service.CommentService;
import com.wt.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author WuTong
 * @create 2019-09-24 10:51
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("addNewComment")
    public String addNewComment(HttpServletRequest request) {//写评论
        int userId = Integer.parseInt(request.getParameter("userId"));
        int moodId = Integer.parseInt(request.getParameter("moodId"));
        String commentText = request.getParameter("comment");
        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setCreatetime(new Date());
        comment.setMoodid(moodId);
        comment.setUserid(userId);
        commentService.insertComment(comment);
        return "redirect:/main"+"?id="+userId;
    }
    /**重定向到主页面
    @RequestMapping("/doRedirect")
    public String doRedirect(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        request.setAttribute("userId",id);
        return "forward:/main";
    }*/
    /**删除评论*/
    @RequestMapping("deleteComment")
    public String deleteComment(HttpServletRequest request) {
        Integer commentId = Integer.valueOf(request.getParameter("commentId"));
        commentService.deleteComment(commentId);
        Integer id = Integer.valueOf(request.getParameter("userId"));
        return "redirect:/main"+"?id="+id;

    }
}
