package com.wt.Service;

import com.wt.dto.UserDto;
import com.wt.model.Food;
import com.wt.model.User;

import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-07 14:44
 */

public interface UserService {
//    List<Food> findallfood();
//
//    Food findfoodbyid(int i);

    UserDto find(int id);

    User chooseByAccount(String account);

    int sameAccount(String account);

    void register(User user);
}
