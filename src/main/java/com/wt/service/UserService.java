package com.wt.service;

import com.wt.dao.UserDao;
import com.wt.dto.UserDto;
import com.wt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WuTong
 * @create 2019-09-07 14:44
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    /**
     * 根据用户ID查找用户
     *
     * @param userId 用户ID
     * @return 用户
     */
    public UserDto findUserByUserId(int userId) {
        User user = userDao.findUserByUserId(userId);
        return changeModel12Dto(user);
    }

    /**
     * 根据账户名查找用户
     *
     * @param account 账户名
     * @return 用户
     */
    public User chooseByAccount(String account) {
        return userDao.chooseByAccount(account);
    }

    /**
     * 将用户信息插入数据库
     *
     * @param user 用户
     */
    public void register(User user) {
        userDao.register(user);
    }

    private UserDto changeModel12Dto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setAccount(user.getAccount());
        userDto.setName(user.getName());
        return userDto;
    }
}
