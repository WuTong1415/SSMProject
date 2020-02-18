package com.wt.service;

import com.wt.dao.CommentDao;
import com.wt.dao.MoodDao;
import com.wt.dao.UserDao;
import com.wt.dao.UserMoodPraiseRelDao;
import com.wt.dto.CommentDto;
import com.wt.dto.MoodDto;
import com.wt.model.Comment;
import com.wt.model.MessageDemo;
import com.wt.model.Mood;
import com.wt.model.User;
import com.wt.mq.MoodProducer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.jms.Destination;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-09 19:23
 */
@Service
public class MoodService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MoodDao moodDao;
    @Autowired
    private UserMoodPraiseRelDao userMoodPraiseRelDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MoodProducer moodProducer;
    /**
     * MQ队列
     */
    private static Destination destination = new ActiveMQQueue("wt.queue.high.concurrency.praise");

    /**
     * 查找所有动态
     *
     * @return 返回动态集合
     */
    public List<MoodDto> findAll() {
        List<Mood> moodList = moodDao.findAll();
        return changeModel12Dto(moodList);
    }

    /**
     * 根据动态ID查找动态
     *
     * @param moodId 动态ID
     * @return 动态
     */
    public Mood findMoodById(int moodId) {
        return moodDao.findMoodByMoodId(moodId);
    }

    /**
     * 更新动态
     *
     * @param mood 动态
     */
    public void updateMood(Mood mood) {
        moodDao.updateMood(mood);
    }

    /**
     * 点赞
     *
     * @param userId 用户ID
     * @param moodId 动态ID
     * @return 可为void
     */
    public boolean praiseMoodForRedis(int userId, int moodId) {
        MessageDemo messageDemo = new MessageDemo(userId, moodId);
        //将信息发送中activeMQ
        moodProducer.sendMessage(destination, messageDemo);
        return false;
    }

    /**
     * 添加新的动态
     *
     * @param mood 动态
     */
    public void addNewMood(Mood mood) {
        moodDao.addMood(mood);
    }

    private List<MoodDto> changeModel12Dto(List<Mood> moodList) {
        if (CollectionUtils.isEmpty(moodList)) {
            return Collections.EMPTY_LIST;
        }
        List<MoodDto> moodDtoList = new ArrayList<>();
        for (Mood mood : moodList) {
            MoodDto moodDto = new MoodDto();
            moodDto.setId(mood.getId());
            //获取Mysql中点赞的ID
            List<Integer> praiseUserIds = userMoodPraiseRelDao.getAllPraiseUserIdByMoodId(mood.getId());
            //获取所有点赞人的ID(Redis)
            praiseUserIds.addAll(redisTemplate.opsForSet().members(mood.getId()));
            //获取所有点赞账户名
            List<String> userNames = userMoodPraiseRelDao.getAllPraiseUsernameById(praiseUserIds);
            List<Comment> comments = commentDao.selectCommentsByMoodId(mood.getId());
            List<CommentDto> commentDtos = new ArrayList<>();
            for (Comment comment : comments) {
                CommentDto commentDto = new CommentDto();
                commentDto.setComment(comment.getComment());
                commentDto.setMoodid(comment.getMoodid());
                commentDto.setCreatetime(comment.getCreatetime());
                commentDto.setUserid(comment.getUserid());
                commentDto.setId(comment.getId());
                commentDto.setFriendName(userDao.findUserByUserId(comment.getUserid()).getAccount());
                commentDtos.add(commentDto);
            }

            moodDto.setCommentList(commentDtos);
            moodDto.setPraisenames(userNames);
            moodDto.setContent(mood.getContent());
            //获取点赞数,SQL＋Redis
            moodDto.setPraiseNum(userNames.size());
            moodDto.setPublishTime(mood.getPublishTime());
            moodDto.setUserId(mood.getUserId());
            moodDtoList.add(moodDto);
            //设置用户信息
            User user = userDao.findUserByUserId(mood.getUserId());
            moodDto.setUserName(user.getName());
            moodDto.setUserAccount(user.getAccount());
        }
        return moodDtoList;
    }
}
