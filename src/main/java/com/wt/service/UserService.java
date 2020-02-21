package com.wt.service;

import com.wt.dao.UserDao;
import com.wt.dto.UserDto;
import com.wt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "user",key = "'user:'+#userId")
    public User findUserByUserId(int userId) {
        return userDao.findUserByUserId(userId);
    }

    /**
     * 根据账户名查找用户
     *
     * @param account 账户名
     * @return 用户
     */
    @Cacheable(value = "user",key = "'user:'+#account")
    public User chooseByAccount(String account) {
        return userDao.chooseByAccount(account);
    }

    /**
     * 将用户信息插入数据库
     *
     * @param user 用户
     * @return 账户ID
     */
    @CachePut(value = "user",key = "'user:'+#user.id")
    public User register(User user) {
        userDao.register(user);
        return user ;
    }

}
