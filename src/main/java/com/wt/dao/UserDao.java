package com.wt.dao;

import com.wt.model.Food;
import com.wt.model.Mood;
import com.wt.model.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-07 10:17
 */
@Repository
public interface UserDao {
    /*//用配置文件
    List<Food> findAllFood();
    //用注解,不用配置文件
    @Select("select * from food where fid = #{id}")
    Food findfoodbyid(int id);
    @Select("select * from food")
    List<Food> findall(RowBounds rowBounds);*/

    User find(int id);

    User chooseByAccount(String account);
    
    int sameAccount(String account);

    void register(User user);
}
