package com.wt.controller;

import com.wt.service.MoodService;
import com.wt.service.UserService;
import com.wt.dao.UserMoodPraiseRelDao;
import com.wt.dto.MoodDto;
import com.wt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-09 19:36
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MoodService moodService;
    /**登录*/
    @RequestMapping("/login")
    public String login(@RequestParam(value = "account") String account, @RequestParam(value = "password") String password, Model model) {
        System.out.println(account.equals(""));
        if (account.equals("") || password.equals("")) {
            System.out.println("输入不能为空");
            return "../../index";
        }
        User user = userService.chooseByAccount(account);
        if (user == null || !user.getPassword().equals(password)) {
            System.out.println("账号密码错误");
            return "../../index";
        }
        List<MoodDto> moodDtoList = moodService.findAll();
        //绑定说说
        model.addAttribute("moods", moodDtoList);
        //绑定用户
        model.addAttribute("user", user);
        return "mood";
    }
    /**注册*/
    @RequestMapping("/register")
    public String register(@ModelAttribute User user) {
        if (user.getAccount().equals("") || user
                .getPassword().equals("") || user.getName().equals("")) {
            System.out.println("输入不能为空");
            return "../../register";
        }
        if (userService.chooseByAccount(user.getAccount()) != null) {
            System.out.println("该用户已经存在");
            return "../../register";
        }
        userService.register(user);
        System.out.println("注册成功");
        return "../../index";
    }
}
