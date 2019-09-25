package com.wt.test;

import java.lang.reflect.Proxy;

/**
 * @author WuTong
 * @create 2019-09-07 16:00
 */
public class MyLoggerTest {
    public static void main(String[] args) {
        BusinessClassService businessClassService = new BusinessClassServiceImpl();
        MyLoggerHandler myLoggerHandler = new MyLoggerHandler(businessClassService);
        BusinessClassService businessClass = (BusinessClassService) Proxy.newProxyInstance(businessClassService.getClass().getClassLoader(), businessClassService.getClass().getInterfaces(), myLoggerHandler);
        businessClass.dosomething();
    }
}
