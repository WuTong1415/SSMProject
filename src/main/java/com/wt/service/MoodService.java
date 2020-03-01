package com.wt.service;

import com.wt.dao.CommentDao;
import com.wt.dao.MoodDao;
import com.wt.dao.UserDao;
import com.wt.dto.CommentDto;
import com.wt.dto.MoodDto;
import com.wt.model.Comment;
import com.wt.model.MessageDemo;
import com.wt.model.Mood;
import com.wt.model.User;
import com.wt.mq.MoodProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.jms.Destination;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author WuTong
 * @create 2019-09-09 19:23
 */
@Service
public class MoodService {
    @Autowired
    private MoodDao moodDao;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    @Autowired
    private MoodProducer moodProducer;

    /**
     * MQ队列
     */
    private static Destination destination = new ActiveMQQueue("wt.queue.high.concurrency.praise");
    private static Destination destination2 = new ActiveMQQueue("wt.queue.high.concurrency.cancelPraise");

    /**
     * 查找原始好友动态集合
     *
     * @param friends 好友ID集合
     * @return 返回原始动态集合
     */
//    @Cacheable(value = "mood",key = "'mood:'+'All'")
    public List<Mood> findAll(List<Integer> friends) {
        return moodDao.findAll(friends);
    }


    /**
     * 根据动态ID查找动态
     *
     * @param moodId 动态ID
     * @return 动态
     */
    @Cacheable(value = "mood", key = "'mood:'+#moodId")
    public Mood findMoodById(int moodId) {
        return moodDao.findMoodByMoodId(moodId);
    }


    /**
     * 点赞
     *
     * @param userName 用户名字
     * @param moodId   动态ID
     * @return 可为void
     */
    public void praiseMoodForRedis(String userName, int moodId) {
        MessageDemo messageDemo = new MessageDemo(userName, moodId);
        //将信息发送中activeMQ
        moodProducer.sendMessage(destination, messageDemo);
    }

    /**
     * 取消点赞
     *
     * @param userName 点赞人的Name
     * @param moodId   动态的ID
     * @return 可为void
     */
    public void cancelPraiseMoodForRedis(String userName, int moodId) {
        MessageDemo messageDemo = new MessageDemo(userName, moodId);
        //将信息发送中activeMQ
        moodProducer.sendMessage(destination2, messageDemo);
    }

    /**
     * 添加新的动态
     *
     * @param mood 动态
     */
    @Caching(
            put = {
                    @CachePut(value = "mood", key = "'mood:'+#mood.id")
            },
            evict = {
                    @CacheEvict(value = "mood", key = "'mood:'+'All'")
            }
    )
    public Mood addNewMood(Mood mood) {
        moodDao.addMood(mood);
        return mood;
    }


    @Caching(
            evict = {
                    @CacheEvict(value = "mood", key = "'mood:'+'All'"),
                    @CacheEvict(value = "mood", key = "'mood:'+#moodId")
            }
    )
    public void deleteMoodById(int moodId) {
        moodDao.deleteMoodById(moodId);
    }

    public List<Mood> findMoodByUserId(Integer friendId) {
        return moodDao.findMoodByUserId(friendId);
    }
}
