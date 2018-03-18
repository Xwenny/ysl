package com.wenny.ysl.portal.interceptor;

import com.wenny.ysl.domain.TbUser;
import com.wenny.ysl.portal.service.UserService;
import com.wenny.ysl.portal.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginIntereptor implements HandlerInterceptor{
    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String token = CookieUtils.getCookieValue(httpServletRequest, "TT_token");
        TbUser user = userService.getUserByToken(token);
        if (user == null) {
            httpServletResponse.sendRedirect(UserServiceImpl.SSO_BASE_URL + UserServiceImpl.SSO_PAGE_LOGIN + "?redirect=" + httpServletRequest.getRequestURL());

            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
