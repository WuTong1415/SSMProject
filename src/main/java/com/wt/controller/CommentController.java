package com.wt.controller;

import com.wt.Service.CommentService;
import com.wt.Service.MoodService;
import com.wt.Service.UserService;
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

    @RequestMapping("addnewcomment")
    public String addnewcomment(HttpServletRequest request, Model model) {//写评论
        int userid = Integer.parseInt(request.getParameter("userid"));
        int moodid = Integer.parseInt(request.getParameter("moodid"));
        String commenttext = request.getParameter("comment");
        Comment comment = new Comment();
        comment.setComment(commenttext);
        comment.setCreatetime(new Date());
        comment.setMoodid(moodid);
        comment.setUserid(userid);
        commentService.insertComment(comment);
        User user = userService.find(userid);
        List<MoodDto> moodDtoList = moodService.findAll();
        model.addAttribute("moods", moodDtoList);
        model.addAttribute("user", user);
        return "mood";
    }

    @RequestMapping("deletecomment")//删除评论
    public String DeleteComment(@RequestParam(value = "commentid") int commentid, @RequestParam(value = "userid") int userid, Model model) {
        commentService.deletecomment(commentid);
        User user = userService.find(userid);
        List<MoodDto> moodDtoList = moodService.findAll();
        model.addAttribute("moods", moodDtoList);
        model.addAttribute("user", user);
        return "mood";
    }
}
