package com.wt.mq;

import com.wt.dao.MoodDao;
import com.wt.dao.UserMoodPraiseRelDao;
import com.wt.model.MessageDemo;
import com.wt.model.Mood;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author WuTong
 * @create 2019-09-19 15:46
 */
@Component
public class MoodCancelConsumer implements MessageListener {
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMoodPraiseRelDao userMoodPraiseRelDao;
    @Autowired
    private MoodDao moodDao;
    @Override
    public void onMessage(Message message) {
        try {
            //从Message中获取信息
           MessageDemo messageDemo = (MessageDemo) ((ActiveMQObjectMessage)message).getObject();
            //移除存放在SET中
            //将用户ID从Redis中Mood的set中移除
            Long remove = redisTemplate.opsForSet().remove(messageDemo.getMoodId(), messageDemo.getUserId());
            /*若Redis缓存中没有这条消息,执行MYSQL删除*/
            if (remove==0){
                /*点赞数减一*/
                Mood mood = moodDao.findMoodByMoodId(messageDemo.getMoodId());
                mood.setPraiseNum(mood.getPraiseNum()-1);
                moodDao.updateMood(mood);
                /*删除点赞关系*/
                userMoodPraiseRelDao.deletePraise(messageDemo.getUserId(),messageDemo.getMoodId());

            }
            logger.info("消费者----->用户ID: "+messageDemo.getUserId()+"给说说ID: "+messageDemo.getMoodId()+"取消点赞");
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
