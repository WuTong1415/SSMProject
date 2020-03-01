package com.wt.service;

import com.wt.dao.UserDao;
import com.wt.dto.UserDto;
import com.wt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-07 14:44
 */
@Service
public class UserService {
    private static final String FRIENDS_KEY = "friends:";
    @Autowired
    private UserDao userDao;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 根据用户ID查找用户
     *
     * @param userId 用户ID
     * @return 用户
     */
    @Cacheable(value = "user", key = "'user:'+#userId")
    public User findUserByUserId(int userId) {
        return userDao.findUserByUserId(userId);
    }

    /**
     * 新增好友
     *
     * @param userId   用户ID
     * @param friendId 添加的好友ID
     */
    public void addFriend(int userId, int friendId) {
        redisTemplate.opsForSet().add(FRIENDS_KEY + userId, friendId);
        redisTemplate.opsForSet().add(FRIENDS_KEY + friendId, userId);
    }

    /**
     * 删除好友
     *
     * @param userId   用户ID
     * @param friendId 删除的好友ID
     */
    public void deleteFriend(int userId, int friendId) {
        redisTemplate.opsForSet().remove(FRIENDS_KEY + userId, friendId);
        redisTemplate.opsForSet().remove(FRIENDS_KEY + friendId, userId);
    }


    /**
     * 查找好友
     *
     * @param userId 用户ID
     * @return 好友ID集合
     */
    public List<Integer> selectFriendsById(int userId) {
        return new ArrayList<Integer>(redisTemplate.opsForSet().members(FRIENDS_KEY + userId));
    }

    /**
     * 根据账户名查找用户
     *
     * @param account 账户名
     * @return 用户
     */
    @Cacheable(value = "user", key = "'user:'+#account")
    public User chooseByAccount(String account) {
        return userDao.chooseByAccount(account);
    }

    /**
     * 将用户信息插入数据库
     *
     * @param user 用户
     * @return 账户ID
     */
    @CachePut(value = "user", key = "'user:'+#user.id")
    public User register(User user) {
        userDao.register(user);
        return user;
    }

}
