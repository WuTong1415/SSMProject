package com.wt.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author WuTong
 * @create 2020-02-27 10:52
 */
public class Interceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/");
            System.out.println("未登录,请登录");
            return false;
        }
        System.out.println(request.getLocalAddr());
        System.out.println(request.getRemoteAddr());

        try {
            Integer id = Integer.valueOf(request.getParameter("userId"));
            if (!id.equals(userId)){
                response.sendRedirect(request.getContextPath() + "/main"+"?userId="+userId);
                System.out.println("无权干涉他人");
                return false;
            }
        } catch (NumberFormatException e){
            System.out.println("访问静态资源");
        }

        return true;
    }
}
