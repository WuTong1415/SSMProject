package com.wt.test;

import java.lang.reflect.Method;

/**
 * @author WuTong
 * @create 2019-09-07 15:44
 */
public interface MyLogger {
    /**
     * 记录方法进入时间
     */
    void SaveIntoMethodTime(Method method);

    /**
     * 记录方法出去时间
     */
    void SaveOutMethodTime(Method method);
}
