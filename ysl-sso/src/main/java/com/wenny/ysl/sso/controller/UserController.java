package com.wenny.ysl.sso.controller;

import com.google.common.base.Strings;
import com.wenny.ysl.domain.TaotaoResult;
import com.wenny.ysl.domain.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wenny.ysl.sso.service.UserService;
import utils.ExceptionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param,@PathVariable Integer type,String callback){
        TaotaoResult result = null;
        if (Strings.isNullOrEmpty(param)){
            result =  TaotaoResult.build(400,"校验内容不能为空");
        }
        if (type == null){
            result = TaotaoResult.build(400,"内容类型不能为空");
        }
        if (type !=1&&type!=2&&type!=3){
            result = TaotaoResult.build(400,"校验内容类型错误");
        }
        if (result != null){
            if (callback != null){
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue((result));
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }else {
                return result;
            }
        }
        try {
            result = userService.checkData(param, type);
        }catch (Exception e){
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        if (callback != null){
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue((result));
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }else {
            return result;
        }
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createUser(TbUser user){
        try {
            TaotaoResult result = userService.createUser(user);
            return result;
        }catch (Exception e){
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response){
        try {
            TaotaoResult result = userService.userLogin(username,password,request,response);
            return result;
        }catch (Exception e){
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token,String callback){
        TaotaoResult result = null;
        try {
            result = userService.getUserByToken(token);
        }catch (Exception e){
            e.printStackTrace();
            result = TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
        if (Strings.isNullOrEmpty(callback)){
            return result;
        }else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }

    }


}
