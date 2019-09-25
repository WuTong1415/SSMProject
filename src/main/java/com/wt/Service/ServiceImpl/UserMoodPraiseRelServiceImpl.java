package com.wt.Service.ServiceImpl;

import com.wt.Service.UserMoodPraiseRelService;
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
 * @create 2019-09-09 19:35
 */
@Service
public class UserMoodPraiseRelServiceImpl implements UserMoodPraiseRelService {
    @Autowired
    private UserMoodPraiseRelDao userMoodPraiseRelDao;
    @Autowired
    private MoodProducer moodProducer;

    private static Destination destination2 = new ActiveMQQueue("wt.queue.high.concurrency.cancelpraise");
    @Override
    public boolean save(UserMoodPraiseRel userMoodPraiseRel) {
        return userMoodPraiseRelDao.save(userMoodPraiseRel);
    }
    @Override
    public boolean cancelpraiseMoodForRedis(int userid,int moodid) { //取消点赞
        MessageDemo messageDemo = new MessageDemo(userid,moodid);
        moodProducer.sendMessage(destination2,messageDemo); //将信息发送中activeMQ
        return false;
    }
}
