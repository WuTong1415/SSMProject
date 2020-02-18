package com.wt.service;

import com.wt.dao.UserMoodPraiseRelDao;
import com.wt.model.MessageDemo;
import com.wt.model.UserMoodPraiseRel;
import com.wt.mq.MoodProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @author WuTong
 * @create 2019-09-09 19:34
 */
@Service
public class UserMoodPraiseRelService {
    @Autowired
    private UserMoodPraiseRelDao userMoodPraiseRelDao;
    @Autowired
    private MoodProducer moodProducer;

    private static Destination destination2 = new ActiveMQQueue("wt.queue.high.concurrency.cancelPraise");

    /**
     * 存入点赞关系
     *
     * @param userMoodPraiseRel 点赞关系
     * @return 可改为void
     */
    public boolean save(UserMoodPraiseRel userMoodPraiseRel) {
        return userMoodPraiseRelDao.save(userMoodPraiseRel);
    }

    /**
     * 取消点赞
     *
     * @param userId 点赞人的ID
     * @param moodId 动态的ID
     * @return 可为void
     */
    public boolean cancelPraiseMoodForRedis(int userId, int moodId) {
        MessageDemo messageDemo = new MessageDemo(userId, moodId);
        //将信息发送中activeMQ
        moodProducer.sendMessage(destination2, messageDemo);
        return false;
    }
}
