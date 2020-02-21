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
    /**
     * 查找所有动态
     *
     * @return 返回动态集合
     */
    List<Mood> findAll();

    /**
     * 根据说说ID查找动态
     *
     * @param moodId 动态ID
     * @return 动态
     */
    Mood findMoodByMoodId(int moodId);

    /**
     * 更新动态
     *
     * @param mood 动态
     */
    void updateMood(Mood mood);

    /**
     * 添加新的动态
     *
     * @param mood 动态
     * @return 自增主键ID
     */
    int addMood(Mood mood);

    /**
     * 删除动态
     * @param moodId 动态ID
     */
    void deleteMoodById(int moodId);
}
