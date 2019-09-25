package com.wt.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author WuTong
 * @create 2019-09-07 15:48
 */
public class MyLoggerHandler implements InvocationHandler {
    //原始对象
    private Object objOriginal;
    private MyLogger myLogger = new MyLoggerImpl();

    public MyLoggerHandler(Object objOriginal) {
        this.objOriginal = objOriginal;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        myLogger.SaveIntoMethodTime(method);
        result = method.invoke(this.objOriginal,args);
        myLogger.SaveOutMethodTime(method);
        return result;
    }
}
