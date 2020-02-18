package com.wt.dao;

import com.wt.model.UserMoodPraiseRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-09 17:57
 */
@Repository
public interface UserMoodPraiseRelDao {
    /**
     * 存入点赞关系
     * @param userMoodPraiseRel 点赞关系
     * @return 可改为void
     */
    boolean save(@Param("userMoodPraiseRel") UserMoodPraiseRel userMoodPraiseRel);

    /**
     * 查找所有给该动态点赞的用户ID
     * @param id 动态的ID
     * @return 用户ID集合
     */
    List<Integer> getAllPraiseUserIdByMoodId(int id);

    /**
     * 查找ID集合对应的名字集合
     * @param list 用户ID的集合
     * @return 名字集合
     */
    List<String> getAllPraiseUsernameById(@Param("list") List<Integer> list);

    /**
     * 取消点赞
     * @param userId 点赞人的ID
     * @param moodId 动态的ID
     */
    void deletePraise(@Param("userId") int userId,@Param("moodId") int moodId);
}
