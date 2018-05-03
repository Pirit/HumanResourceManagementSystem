package com.pxu.hrms.interceptor;


import com.pxu.hrms.domain.User;
import com.pxu.hrms.util.common.HrmsConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizedInterceptor implements HandlerInterceptor {

    private static final String[] IGNORE_URI = {"/loginForm", "login", "/404.html"};

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        boolean flag = false;
        String servletPath = httpServletRequest.getServletPath();
        for (String s : IGNORE_URI) {
            if (servletPath.contains(s)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            User user = (User) httpServletRequest.getSession().getAttribute(HrmsConstants.USER_SESSION);
            if (user == null) {
                httpServletRequest.setAttribute("message", "请先登录再访问网站！");
                httpServletRequest.getRequestDispatcher(HrmsConstants.LOGIN).forward(httpServletRequest, httpServletResponse);
                return flag;
            } else {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
