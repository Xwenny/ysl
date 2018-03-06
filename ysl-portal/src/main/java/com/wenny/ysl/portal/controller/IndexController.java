package com.wenny.ysl.portal.controller;

import com.wenny.ysl.domain.TaotaoResult;
import com.wenny.ysl.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

@Controller
public class IndexController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/index")
    public String showIndex(Model model) {
        String adJson = contentService.getContentList();
        model.addAttribute("ad1",adJson);
        return "index";
    }

    @RequestMapping(value = "/httpclient/post", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String testPost(String username,String password) {
        String result = "username:"+username + "\t" + "password:"+password;
        System.out.println(result);
        return "username:"+username + "\t" + "password:"+password;
    }

}

