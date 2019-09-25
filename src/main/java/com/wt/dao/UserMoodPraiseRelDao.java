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
    boolean save(@Param("userMoodPraiseRel") UserMoodPraiseRel userMoodPraiseRel);

    List<Integer> getallPraiseUserIdByMoodId(int id);

    List<String> getallPraiseUsernameById(@Param("list") List<Integer> list);

    void deletepraise(@Param("userid") int userid,@Param("moodid") int moodid);
}
