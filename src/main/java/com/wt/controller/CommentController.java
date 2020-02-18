package com.wt.controller;

import com.wt.service.CommentService;
import com.wt.service.MoodService;
import com.wt.service.UserService;
import com.wt.dto.MoodDto;
import com.wt.model.Comment;
import com.wt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-24 10:51
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private MoodService moodService;
    @Autowired
    private UserService userService;

    @RequestMapping("addNewComment")
    public String addNewComment(HttpServletRequest request, Model model) {//写评论
        int userId = Integer.parseInt(request.getParameter("userId"));
        int moodId = Integer.parseInt(request.getParameter("moodId"));
        String commentText = request.getParameter("comment");
        Comment comment = new Comment();
        comment.setComment(commentText);
        comment.setCreatetime(new Date());
        comment.setMoodid(moodId);
        comment.setUserid(userId);
        commentService.insertComment(comment);
        User user = userService.findUserByUserId(userId);
        List<MoodDto> moodDtoList = moodService.findAll();
        model.addAttribute("moods", moodDtoList);
        model.addAttribute("user", user);
        return "mood";
    }

    /**删除评论*/
    @RequestMapping("deleteComment")
    public String deleteComment(@RequestParam(value = "commentId") int commentId, @RequestParam(value = "userId") int userId, Model model) {
        commentService.deleteComment(commentId);
        User user = userService.findUserByUserId(userId);
        List<MoodDto> moodDtoList = moodService.findAll();
        model.addAttribute("moods", moodDtoList);
        model.addAttribute("user", user);
        return "mood";
    }
}
