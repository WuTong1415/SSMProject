package com.wt.dao;

import com.wt.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author WuTong
 * @create 2019-09-07 10:17
 */
@Repository
public interface UserDao {

    /**
     * 根据用户ID查找用户
     *
     * @param userId 用户ID
     * @return 用户
     */
    User findUserByUserId(int userId);

    /**
     * 根据账户名查找用户
     *
     * @param account 账户名
     * @return 用户
     */
    User chooseByAccount(String account);

    /**
     * 查看是否有相同的账户名(用于注册的账户名判断)
     *
     * @param account 账户名
     * @return 账户名对应的ID
     */
    int sameAccount(String account);

    /**
     * 将用户信息插入数据库
     *
     * @param user 用户
     */
    void register(User user);
}
