package com.wt.test;

import java.lang.reflect.Method;

/**
 * @author WuTong
 * @create 2019-09-07 15:45
 */
public class MyLoggerImpl implements MyLogger{
    @Override
    public void SaveIntoMethodTime(Method method) {
        System.out.println("进入"+method.getName()+"方法时间为: "+System.currentTimeMillis());
    }

    @Override
    public void SaveOutMethodTime(Method method) {
        System.out.println("出去"+method.getName()+"方法时间为: "+System.currentTimeMillis());
    }
}
