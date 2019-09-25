package com.wt.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author WuTong
 * @create 2019-09-18 15:19
 */
public class RedisTest extends BaseJunit4Test{
    private static final String PRAISE_HASH_KEY = "springmvc.mybatis.mood.id.list.key";
    @Resource
    private RedisTemplate redisTemplate;
    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("name","ay");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println("Value of name is: "+name);
    }
    @Test
    public void demo(){
        List<Integer> list = new ArrayList<>();
        Set<Integer> set =new HashSet<>();
        set.add(3);
        set.add(4);
        list.add(1);
        list.add(2);
        list.addAll(set);
        for (Integer integer : list) {
            System.out.println(integer);
        }

    }
    @Test
    public void te(){
        Long add = redisTemplate.opsForSet().add(5, 6);
        redisTemplate.opsForSet().add(5, 7);
        System.out.println(add);
    }
    @Test
    public void de(){
        Long remove = redisTemplate.opsForSet().remove(5, 6);
        redisTemplate.opsForSet().remove(5, 7);
        if (remove==0)
            System.out.println("执行MYSQL删除");
        System.out.println(remove);
    }
}
