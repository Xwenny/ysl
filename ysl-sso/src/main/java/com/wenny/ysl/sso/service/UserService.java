package com.wenny.ysl.sso.service;

import com.wenny.ysl.domain.TaotaoResult;
import com.wenny.ysl.domain.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    TaotaoResult checkData(String content, Integer type);
    TaotaoResult createUser(TbUser user);
    TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
    TaotaoResult getUserByToken(String token);
    }

