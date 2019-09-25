package com.wt.Service;

import com.wt.model.UserMoodPraiseRel;

/**
 * @author WuTong
 * @create 2019-09-09 19:34
 */
public interface UserMoodPraiseRelService {
    boolean save(UserMoodPraiseRel userMoodPraiseRel);
    boolean cancelpraiseMoodForRedis(int userid,int moodid);
}
