package com.wt.controller;

import com.wt.Service.MoodService;
import com.wt.Service.UserMoodPraiseRelService;
import com.wt.Service.UserService;
import com.wt.dto.MoodDto;
import com.wt.model.Mood;
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
 * @create 2019-09-09 19:40
 */
@Controller
public class MoodController {
    @Autowired
    private MoodService moodService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMoodPraiseRelService userMoodPraiseRelService;
    @RequestMapping("cancelpraise")//取消点赞
    public String cancelpraise (@RequestParam(value = "moodid") int moodid, @RequestParam(value = "friend") int friendid, Model model){
        boolean b = userMoodPraiseRelService.cancelpraiseMoodForRedis(friendid, moodid);
        User user = userService.find(friendid);
        List<MoodDto> moodDtoList = moodService.findAll();
        model.addAttribute("moods",moodDtoList);
        model.addAttribute("isPraise",b);
        model.addAttribute("user", user);
        return "mood";
    }
    @RequestMapping("writemood")//写说说,跳转至写说说页面
    public String writemood(@RequestParam(value = "userid") int userid,Model model){
        model.addAttribute("userid",userid);
        return "writemood";
    }
    @RequestMapping("addnewmood")//写说说
    public String addnewmood(HttpServletRequest request,Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        String content = request.getParameter("content");
        Mood mood = new Mood();
        mood.setContent(content);
        mood.setPublishTime(new Date());
        mood.setUserId(id);
        mood.setPraiseNum(0);
        moodService.addnewmood(mood);
        User user = userService.find(id);
        List<MoodDto> moodDtoList = moodService.findAll();
        model.addAttribute("moods",moodDtoList);
        model.addAttribute("user", user);
        return "mood";
    }
    @RequestMapping("/praisebyRedis")
    public String goodforRedis(@RequestParam(value = "moodid") int moodid, @RequestParam(value = "friend") int friendid, Model model) {
        boolean b = moodService.praiseMoodForRedis(friendid, moodid);//一个说说不能被两个相同的ID点赞
        User user = userService.find(friendid);
        List<MoodDto> moodDtoList = moodService.findAll();
        model.addAttribute("moods",moodDtoList);
        model.addAttribute("isPraise",b);
        model.addAttribute("user", user);
        return "mood";
    }
}
