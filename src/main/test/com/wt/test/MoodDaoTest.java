package com.wt.test;

import com.wt.dao.MoodDao;
import com.wt.model.Mood;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-17 9:30
 */
public class MoodDaoTest extends BaseJunit4Test{
    @Autowired
    private MoodDao moodDao;

    @Test
    public void findAll(){
        List<Mood> daoAll = moodDao.findAll();
        for (Mood mood : daoAll) {
            System.out.println(mood.toString());
        }

    }
    @Test
    public void findmoodById(){
        Mood mood = moodDao.findmoodById(2);
        System.out.println(mood.toString());
    }
    @Test
    public void  good(){
        Mood mood = moodDao.findmoodById(2);
        mood.setPraiseNum(mood.getPraiseNum()-1);
        moodDao.updateMood(mood);
    }
    @Test
    public void addnewmood(){
        Mood mood = new Mood();
        mood.setContent("zxcvbn");
        mood.setPublishTime(new Date());
        mood.setUserId(1);
        mood.setPraiseNum(0);
        System.out.println(mood.toString());
        moodDao.addMood(mood);
    }
}
