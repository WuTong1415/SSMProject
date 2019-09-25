package com.wt.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wt.Service.UserService;
import com.wt.dao.UserDao;
import com.wt.dto.UserDto;
import com.wt.model.Food;
import com.wt.model.User;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WuTong
 * @create 2019-09-07 14:53
 */
public class UserDaoTest extends BaseJunit4Test{
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Test
    public void sameAccount(){
        User user = userDao.chooseByAccount("ly");
        System.out.println(user!=null);
    }
    @Test
    public void registerUser(){
        User user = new User();
        user.setAccount("zxc");
        user.setName("周星驰");
        user.setPassword("123456");
        userDao.register(user);
    }
    @Test
    public void find(){
        UserDto userDto = userService.find(1);
        System.out.println(userDto.toString());
    }
//    @Test
//    public void testFindAll(){
//        List<Food> foods = userDao.findAllFood();
//        for (Food food : foods) {
//            System.out.println(food.toString());
//        }
//
//    }
//    @Test
//    public void findfoodbyid(){
//        Food food = userDao.findfoodbyid(2);
//        System.out.println(food.toString());
//    }
//    @Test
//    public void findalllimit(){
//        List<Food> foods = userDao.findall(new RowBounds(0, 3));
//        List<Food> foods2 = userDao.findall(new RowBounds(3, 3));
//        System.out.println("第一页,每页三个数据");
//        for (Food food : foods) {
//            System.out.println(food.toString());
//        }
//        System.out.println("第二页,每页三个数据");
//        for (Food food : foods2) {
//            System.out.println(food.toString());
//        }
//    }
//    @Test
//    public void testPageHelper(){
//        PageHelper.startPage(2,5);//只显示最近的一条(第几页,每页的数量(以此分页))
//        List<Food> allFood = userDao.findAllFood();
//        PageInfo<Food> pageInfo = new PageInfo<>(allFood);
//        System.out.println("总数量"+pageInfo.getTotal());
//        System.out.println("当前页查询记录：" + pageInfo.getList().size());
//        System.out.println("当前页码：" + pageInfo.getPageNum());
//        System.out.println("每页显示数量：" + pageInfo.getPageSize());
//        System.out.println("总页：" + pageInfo.getPages());
//        System.out.println(allFood);
//    }
//    @Autowired
//    private SqlSessionFactoryBean sqlSessionFactoryBean;
//    @Test
//    public void testSessionCache() throws Exception {
//        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        UserDao userDao = sqlSession.getMapper(UserDao.class);
//        //第一次查询
//        Food food = userDao.findfoodbyid(1);
//        System.out.println(food.toString());
//        //第二次查询
//        Food food2 =userDao.findfoodbyid(1);
//        System.out.println(food2.toString());
//        sqlSession.close();
//    }
}
