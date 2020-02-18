package com.wt.controller;

import com.wt.service.MoodService;
import com.wt.service.UserMoodPraiseRelService;
import com.wt.service.UserService;
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
    /**取消点赞*/
    @RequestMapping("cancelPraise")
    public String cancelpraise (@RequestParam(value = "moodId") int moodId, @RequestParam(value = "friend") int friendId, Model model){
        boolean b = userMoodPraiseRelService.cancelPraiseMoodForRedis(friendId, moodId);
        User user = userService.findUserByUserId(friendId);
        List<MoodDto> moodDtoList = moodService.findAll();
        model.addAttribute("moods",moodDtoList);
        model.addAttribute("isPraise",b);
        model.addAttribute("user", user);
        return "mood";
    }
    /**写说说,跳转至写说说页面*/
    @RequestMapping("writeMood")
    public String writeMood(@RequestParam(value = "userId") int userId,Model model){
        model.addAttribute("userId",userId);
        return "writeMood";
    }
    /**写说说*/
    @RequestMapping("addNewMood")
    public String addNewMood(HttpServletRequest request,Model model){
        int id = Integer.parseInt(request.getParameter("id"));
        String content = request.getParameter("content");
        Mood mood = new Mood();
        mood.setContent(content);
        mood.setPublishTime(new Date());
        mood.setUserId(id);
        mood.setPraiseNum(0);
        moodService.addNewMood(mood);
        User user = userService.findUserByUserId(id);
        List<MoodDto> moodDtoList = moodService.findAll();
        model.addAttribute("moods",moodDtoList);
        model.addAttribute("user", user);
        return "mood";
    }
    @RequestMapping("/praiseByRedis")
    public String praiseForRedis(@RequestParam(value = "moodId") int moodId, @RequestParam(value = "friend") int friendId, Model model) {
        //一个说说不能被两个相同的ID点赞
        boolean b = moodService.praiseMoodForRedis(friendId, moodId);
        User user = userService.findUserByUserId(friendId);
        List<MoodDto> moodDtoList = moodService.findAll();
        model.addAttribute("moods",moodDtoList);
        model.addAttribute("isPraise",b);
        model.addAttribute("user", user);
        return "mood";
    }
}
