package com.wt.Service.ServiceImpl;

import com.wt.Service.MoodService;
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
 * @create 2019-09-09 19:24
 */
@Service
public class MoodServiceImpl implements MoodService {
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
    //MQ队列
    private static Destination destination = new ActiveMQQueue("wt.queue.high.concurrency.praise");

    @Override
    public List<MoodDto> findAll() {
        List<Mood> moodList = moodDao.findAll();
        return converModel12Dto(moodList);
    }
    @Override
    public Mood findmoodById(int moodid) {

        return moodDao.findmoodById(moodid);
    }
    @Override
    public void updateMood(Mood mood) {
        moodDao.updateMood(mood);
    }

    @Override
    public boolean praiseMoodForRedis(int userid,int moodid) { //点赞
        MessageDemo messageDemo = new MessageDemo(userid,moodid);
        moodProducer.sendMessage(destination,messageDemo); //将信息发送中activeMQ
        return false;
    }

    @Override
    public void addnewmood(Mood mood) {
        moodDao.addMood(mood);
    }


    private List<MoodDto> converModel12Dto(List<Mood> moodList) {
        if (CollectionUtils.isEmpty(moodList))
            return Collections.EMPTY_LIST;
        List<MoodDto> moodDtoList = new ArrayList<>();
        for (Mood mood : moodList) {
            MoodDto moodDto = new MoodDto();
            moodDto.setId(mood.getId());
            List<Integer> praiseuserids = userMoodPraiseRelDao.getallPraiseUserIdByMoodId(mood.getId());//获取Mysql中点赞的ID
            praiseuserids.addAll(redisTemplate.opsForSet().members(mood.getId()));              //获取所有点赞人的ID(Redis)
            List<String> usernames = userMoodPraiseRelDao.getallPraiseUsernameById(praiseuserids);//获取所有点赞账户名
            List<Comment> comments = commentDao.selectcommentByid(mood.getId());
            List<CommentDto> commentDtos = new ArrayList<>();
            for (Comment comment : comments) {
                CommentDto commentDto = new CommentDto();
                commentDto.setComment(comment.getComment());
                commentDto.setMoodid(comment.getMoodid());
                commentDto.setCreatetime(comment.getCreatetime());
                commentDto.setUserid(comment.getUserid());
                commentDto.setId(comment.getId());
                commentDto.setFriendName(userDao.find(comment.getUserid()).getAccount());
                commentDtos.add(commentDto);
            }

            moodDto.setCommentList(commentDtos);
            moodDto.setPraisenames(usernames);
            moodDto.setContent(mood.getContent());
            moodDto.setPraiseNum(usernames.size());//获取点赞数,SQL＋Redis
            moodDto.setPublishTime(mood.getPublishTime());
            moodDto.setUserId(mood.getUserId());
            moodDtoList.add(moodDto);
            //设置用户信息
            User user = userDao.find(mood.getUserId());
            moodDto.setUserName(user.getName());
            moodDto.setUserAccount(user.getAccount());
        }
        return moodDtoList;
    }
}
