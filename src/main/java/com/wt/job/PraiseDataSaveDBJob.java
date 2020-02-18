package com.wt.job;

import com.wt.service.MoodService;
import com.wt.service.UserMoodPraiseRelService;
import com.wt.model.Mood;
import com.wt.model.UserMoodPraiseRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * @author Wutong
 * @create 2019-09-18 17:13
 */
@Component
@Configurable
@EnableScheduling
/**定时任务,从Redis中获取数据存入MYSQL中*/
public class PraiseDataSaveDBJob {
    @Autowired
    private RedisTemplate redisTemplate;
    private static final String PRAISE_HASH_KEY = "springMVC.mybatis.mood.id.list.key";
    @Autowired
    private UserMoodPraiseRelService userMoodPraiseRelService;
    @Autowired
    private MoodService moodService;

    /**
     * 每50秒执行一次
     */
    @Scheduled(cron = "*/10 * *  * * *")
    public void savePraiseDataToDb2() {
        //获取所有说说
        Set<Integer> moods = redisTemplate.opsForSet().members(PRAISE_HASH_KEY);
        if (CollectionUtils.isEmpty(moods)) {
            return;
        }
        for (Integer moodid : moods) {
            if (redisTemplate.opsForSet().members(moodid) == null) {
                continue;
            } else {
                Set<Integer> userIds = redisTemplate.opsForSet().members(moodid);
                if (CollectionUtils.isEmpty(userIds)) {
                    continue;
                } else {
                    for (Integer userId : userIds) {
                        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
                        userMoodPraiseRel.setUserId(userId);
                        userMoodPraiseRel.setMoodId(moodid);
                        userMoodPraiseRelService.save(userMoodPraiseRel);
                    }
                    Mood mood = moodService.findMoodById(moodid);
                    mood.setPraiseNum(mood.getPraiseNum() + redisTemplate.opsForSet().size(moodid).intValue());
                    moodService.updateMood(mood);
                    redisTemplate.delete(moodid);
                }
            }
        }
        redisTemplate.delete(PRAISE_HASH_KEY);
        System.out.println("DB更新完成");
    }
}
