package com.wt.mq;

import com.wt.model.MessageDemo;
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
    private static final String PRAISE_KEY = "praise:";
    private Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void onMessage(Message message) {
        try {
            //从Message中获取信息
           MessageDemo messageDemo = (MessageDemo) ((ActiveMQObjectMessage)message).getObject();
            //移除存放在SET中
            //将用户Name从Redis中Mood的set中移除
            redisTemplate.opsForSet().remove(PRAISE_KEY+messageDemo.getMoodId(), messageDemo.getUserName());
            logger.info("消费者----->用户ID: "+messageDemo.getUserName()+"给说说ID: "+messageDemo.getMoodId()+"取消点赞");
        }catch (Exception e){
            System.out.println(e);
        }
    }

}
