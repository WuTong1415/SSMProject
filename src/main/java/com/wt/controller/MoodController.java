package com.wt.controller;

import com.wt.service.CommentService;
import com.wt.service.MoodService;
import com.wt.service.UserService;
import com.wt.model.Mood;
import com.wt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author WuTong
 * @create 2019-09-09 19:40
 */
@Controller
public class MoodController {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
    private static final String PRAISE_HASH_KEY = "springMVC.mybatis.mood.id.list.key";
    @Autowired
    private MoodService moodService;
    @Autowired
    private UserService userService;
    @Autowired
    CommentService commentService;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 取消点赞
     */
    @RequestMapping("cancelPraise")
    public String cancelPraise(HttpServletRequest request) {
        Integer id = Integer.valueOf(request.getParameter("userId"));
        Integer moodId = Integer.valueOf(request.getParameter("moodId"));
        User user = userService.findUserByUserId(id);
        moodService.cancelPraiseMoodForRedis(user.getName(), moodId);
        String state = request.getParameter("state");
        int friendId = moodService.findMoodById(moodId).getUserId();
        if ("m".equals(state)) {
            return "redirect:/main" + "?userId=" + id;
        }
        return "redirect:/FriendData" + "?userId=" + id + "&friendId=" + friendId;
    }


    /**
     * 写说说,跳转至写说说页面
     */
    @RequestMapping("writeNewMood")
    public String writeMood(@RequestParam(value = "userId") int userId, Model model) {
        model.addAttribute("userId", userId);
        return "writeMood";
    }

    /**
     * 写说说
     */
    @RequestMapping("addNewMood")
    public String addNewMood(HttpServletRequest request, @RequestParam("img") MultipartFile img) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("userId"));
        String content = request.getParameter("content");
        Mood mood = new Mood();
        mood.setContent(content);
        mood.setPublishTime(new Date());
        mood.setUserId(id);

        if (img != null) {
            String filename = img.getOriginalFilename();
            Date date = new Date();
            String filePath = request.getSession().getServletContext().getRealPath("/static/img");
            String imgName = format.format(date) + date.getTime() + filename;
            String newFilePath = filePath + "/" + imgName;
            File file = new File(newFilePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            img.transferTo(file);
            mood.setImg(imgName);
        }
        moodService.addNewMood(mood);
        return "redirect:/main" + "?userId=" + id;
    }

    /**
     * 删除动态
     *
     * @param request 请求
     * @return 主页面
     */
    @RequestMapping("deleteMood")
    public String deleteMood(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("userId"));
        int moodId = Integer.parseInt(request.getParameter("moodId"));
        Mood mood = moodService.findMoodById(moodId);
        String imgPath = request.getSession().getServletContext().getRealPath("/static/img/") + mood.getImg();
        File file = new File(imgPath);
        if (file.exists()) {
            file.delete();
        }
        moodService.deleteMoodById(moodId);
        /*从Redis中删除点赞记录*/
        redisTemplate.opsForSet().remove(PRAISE_HASH_KEY, moodId);
        redisTemplate.opsForSet().pop(moodId);
        /*删除评论*/
        commentService.deleteCommentsByMoodId(moodId);
        return "redirect:/main" + "?userId=" + id;
    }

    /**
     * 点赞
     *
     * @param request 请求
     * @return 主页面
     */
    @RequestMapping("/praiseByRedis")
    public String praiseForRedis(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("userId"));
        int moodId = Integer.parseInt(request.getParameter("moodId"));
        User user = userService.findUserByUserId(id);
        //一个说说不能被两个相同的ID点赞
        moodService.praiseMoodForRedis(user.getName(), moodId);

        String state = request.getParameter("state");
        int friendId = moodService.findMoodById(moodId).getUserId();
        if ("m".equals(state)) {
            return "redirect:/main" + "?userId=" + id;
        }
        return "redirect:/FriendData" + "?userId=" + id + "&friendId=" + friendId;
    }
}
