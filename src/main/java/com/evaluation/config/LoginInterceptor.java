package com.evaluation.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author: ChenXing
 * @date: 2023/4/26 16:38
 * @Description:
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        System.out.println("url = " + url);
        HttpSession session = request.getSession();
        Object loginUserId = session.getAttribute("loginUserId");
        if (loginUserId == null) {
            System.out.println("用户未登陆");
            response.sendRedirect("index");
            return false;
        } else {
            return true;
        }
    }

}
