package com.wt.controller;

import com.wt.dto.CommentDto;
import com.wt.dto.UserDto;
import com.wt.model.Comment;
import com.wt.model.Mood;
import com.wt.service.CommentService;
import com.wt.service.MoodService;
import com.wt.service.UserService;
import com.wt.dto.MoodDto;
import com.wt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.net.idn.Punycode;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-09 19:36
 */
@Controller
public class UserController {
    private static final String PRAISE_KEY = "praise:";
    @Autowired
    private UserService userService;
    @Autowired
    private MoodService moodService;
    @Autowired
    CommentService commentService;
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 登录
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Model model, HttpSession session) {
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        if (account.equals("") || password.equals("")) {
            model.addAttribute("msg", "输入不能为空");
            return "index";
        }
        User user = userService.chooseByAccount(account);
        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("msg", "账号密码错误");
            return "index";
        }
        session.setAttribute("userId", user.getId());
        return "redirect:/main" + "?userId=" + user.getId();
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userId");
        return "index";
    }

    @RequestMapping("/reg")
    public String reg() {
        return "register";
    }

    /**
     * 注册
     */
    @RequestMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (user.getAccount().equals("") || user
                .getPassword().equals("") || user.getName().equals("")) {
            model.addAttribute("msg", "输入不能为空");
            return "register";
        }
        if (userService.chooseByAccount(user.getAccount()) != null) {
            model.addAttribute("msg", "该用户已经存在");
            return "register";
        }
        int id = userService.register(user).getId();
        return "redirect:/main" + "?userId=" + id;
    }

    @RequestMapping("/main")
    public String begin(HttpServletRequest request, Model model) {
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        User user = userService.findUserByUserId(userId);
        List<Integer> friends = userService.selectFriendsById(userId);
        List<MoodDto> moodDtoList = changeModel12Dto(moodService.findAll(friends));
        //绑定说说
        model.addAttribute("moods", moodDtoList);
        //绑定用户
        model.addAttribute("user", user);
        return "mood";
    }

    @RequestMapping("/addFriend")
    public String addFriend(HttpServletRequest request, Model model) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String account = request.getParameter("friendAccount");
        User friend = userService.chooseByAccount(account);
        if (friend == null) {
            request.getSession().setAttribute("msg", "好友账号不存在");
        } else {
            userService.addFriend(userId, friend.getId());
        }
        return "redirect:/friends" + "?userId=" + userId;
    }

    @RequestMapping("/deleteFriends")
    public String deleteFriends(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int friendId = Integer.parseInt(request.getParameter("friendId"));
        userService.deleteFriend(userId, friendId);
        return "redirect:/friends" + "?userId=" + userId;
    }

    @RequestMapping("/FriendData")
    public String friendData(HttpServletRequest request, Model model) {
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer friendId = Integer.valueOf(request.getParameter("friendId"));
        List<Mood> friendMoods = moodService.findMoodByUserId(friendId);
        List<MoodDto> friendMoodList = changeModel12Dto(friendMoods);
        model.addAttribute("moods", friendMoodList);
        //绑定用户
        User user = userService.findUserByUserId(userId);
        model.addAttribute("user", user);
        return "friendMood";
    }

    @RequestMapping("/friends")
    public String friendsList(HttpServletRequest request, Model model) {
        Integer id = Integer.valueOf(request.getParameter("userId"));
        User user = userService.findUserByUserId(id);
        List<User> friends = new ArrayList<>();
        List<Integer> friendIds = userService.selectFriendsById(id);
        for (Integer friendId : friendIds) {
            friends.add(userService.findUserByUserId(friendId));
        }
        try {
            String msg = (String) request.getSession().getAttribute("msg");
            model.addAttribute("msg", msg);
        }catch (Exception e){
            System.out.println("没出错");
        }
        model.addAttribute("friends", friends);
        model.addAttribute("user", user);

        return "friends";
    }

    /**
     * 重新封装所有的动态
     *
     * @param moodList 动态的原始数据
     * @return 封装好的动态展示类集合
     */

    private List<MoodDto> changeModel12Dto(List<Mood> moodList) {
        if (CollectionUtils.isEmpty(moodList)) {
            return Collections.EMPTY_LIST;
        }
        List<MoodDto> moodDtoList = new ArrayList<>();
        for (Mood mood : moodList) {
            //创建新动态展示类
            MoodDto moodDto = new MoodDto();

            //获取所有点赞人的名字(Redis)
            List<String> userNames = new ArrayList<String>(redisTemplate.opsForSet().members(PRAISE_KEY + mood.getId()));
            //获取该动态的所有评论
            List<Comment> comments = commentService.selectCommentsByMoodId(mood.getId());
            //将评论重新封装
            List<CommentDto> commentDtos = new ArrayList<>();
            for (Comment comment : comments) {
                CommentDto commentDto = new CommentDto();
                commentDto.setComment(comment.getComment());
                commentDto.setMoodid(comment.getMoodid());
                commentDto.setCreatetime(comment.getCreatetime());
                commentDto.setUserid(comment.getUserid());
                commentDto.setId(comment.getId());
                commentDto.setFriendName(userService.findUserByUserId(comment.getUserid()).getName());
                commentDtos.add(commentDto);
            }
            //封装展示动态
            if (mood.getImg() != null) {
                moodDto.setImg(mood.getImg());
            }
            moodDto.setId(mood.getId());
            moodDto.setCommentList(commentDtos);
            moodDto.setPraiseNames(userNames);
            moodDto.setContent(mood.getContent());
            //点赞数
            moodDto.setPublishTime(mood.getPublishTime());
            moodDto.setUserId(mood.getUserId());

            //设置用户信息
            User user = userService.findUserByUserId(mood.getUserId());
            moodDto.setUserName(user.getName());
            moodDto.setUserAccount(user.getAccount());
            //插入到展示动态集合
            moodDtoList.add(moodDto);
        }
        return moodDtoList;
    }
}
