package com.wt.Service.ServiceImpl;

import com.wt.Service.UserService;
import com.wt.dao.UserDao;
import com.wt.dto.UserDto;
import com.wt.model.Food;
import com.wt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-07 14:45
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
//    @Override
//    public List<Food> findallfood() {
//        return userDao.findAllFood();
//    }
//
//    @Override
//    public Food findfoodbyid(int i) {
//        return userDao.findfoodbyid(i);
//    }

    @Override
    public UserDto find(int id) {
        User user = userDao.find(id);
        return converModel12Dto(user);
    }

    @Override
    public User chooseByAccount(String account) {
        return userDao.chooseByAccount(account);
    }

    @Override
    public int sameAccount(String account) {
        return userDao.sameAccount(account);
    }

    @Override
    public void register(User user) {
        userDao.register(user);
    }

    public UserDto converModel12Dto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setAccount(user.getAccount());
        userDto.setName(user.getName());
        return userDto;
    }
}
