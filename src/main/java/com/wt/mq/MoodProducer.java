package com.wt.mq;

import com.wt.dto.MoodDto;
import com.wt.model.MessageDemo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
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
        log.info("生产者---->用户id: "+messageDemo.getUserid()+"给说说id: "+messageDemo.getMoodid()+"点赞");//将点赞信息存入ActiveMQ
        jmsTemplate.convertAndSend(destination,messageDemo);
    }
}
