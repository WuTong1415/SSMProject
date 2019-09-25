package com.wt.test;

import com.wt.dao.UserMoodPraiseRelDao;
import com.wt.model.UserMoodPraiseRel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-17 14:34
 */
public class UserMoodPraiseRelTest extends BaseJunit4Test {
    @Autowired
    private UserMoodPraiseRelDao userMoodPraiseRelDao;

    @Test
    public void save() {
        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
        userMoodPraiseRel.setMoodId(1);
        userMoodPraiseRel.setUserId(2);
        userMoodPraiseRelDao.save(userMoodPraiseRel);
    }

    @Test
    public void getallPraiseUserIdByMoodId() {
        List<Integer> list = userMoodPraiseRelDao.getallPraiseUserIdByMoodId(2);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    @Test
    public void getallPraiseUsernameById() {
        List<Integer> list = userMoodPraiseRelDao.getallPraiseUserIdByMoodId(2);
        List<String> names = userMoodPraiseRelDao.getallPraiseUsernameById(list);
        for (String name : names) {
            System.out.println(name);
        }
    }
    @Test
    public void delete(){
        userMoodPraiseRelDao.deletepraise(1,1);
    }
}
