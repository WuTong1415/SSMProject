package com.wt.mq;

import com.wt.model.MessageDemo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Destination;

/**
 * @author WuTong
 * @create 2019-09-19 15:13
 */
@Component
public class MoodProducer {
    @Autowired
    private JmsTemplate jmsTemplate;
    private Logger log = Logger.getLogger(this.getClass());
    public void sendMessage(Destination destination, final MessageDemo messageDemo){
        //将点赞信息存入ActiveMQ
        log.info("生产者---->用户id: "+messageDemo.getUserName()+"给说说id: "+messageDemo.getMoodId()+"点赞");
        jmsTemplate.convertAndSend(destination,messageDemo);
    }
}
