package com.wt.Service;

import com.wt.dto.MoodDto;
import com.wt.model.Mood;

import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-09 19:23
 */
public interface MoodService {
    List<MoodDto> findAll();

    Mood findmoodById(int moodid);

    void updateMood(Mood mood);

    boolean praiseMoodForRedis(int userid,int moodid);


    void addnewmood(Mood mood);
}
