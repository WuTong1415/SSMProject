package com.wt.controller;

import com.wt.service.CommentService;
import com.wt.model.Comment;
import com.wt.service.MoodService;
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
    @Autowired
    MoodService moodService;

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
        String state = request.getParameter("state");
        int friendId = moodService.findMoodById(moodId).getUserId();
        if ("m".equals(state)) {
            return "redirect:/main" + "?userId=" + userId;
        }
        return "redirect:/FriendData" + "?userId=" + userId + "&friendId=" + friendId;
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
        int moodId = Integer.parseInt(request.getParameter("moodId"));
        int friendId = Integer.parseInt(request.getParameter("friendId"));
        commentService.deleteComment(commentId,moodId);
        Integer id = Integer.valueOf(request.getParameter("userId"));
        String state = request.getParameter("state");
        if ("m".equals(state)) {
            return "redirect:/main" + "?userId=" + id;
        }
        return "redirect:/FriendData" + "?userId=" + id + "&friendId=" + friendId;
    }
}
