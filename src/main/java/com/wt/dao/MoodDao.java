package com.wt.dao;

import com.wt.model.Mood;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-09 17:53
 */
@Repository
public interface MoodDao {
    List<Mood> findAll();

    Mood findmoodById(int moodid);

    void updateMood(Mood mood);

    void addMood(Mood mood);
}
