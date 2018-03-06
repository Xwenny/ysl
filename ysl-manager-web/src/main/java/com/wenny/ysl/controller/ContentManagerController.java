package com.wenny.ysl.controller;

import com.wenny.ysl.domain.EUDataGridResult;
import com.wenny.ysl.service.ContentManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content/query")
public class ContentManagerController {

    @Autowired
    ContentManageService contentManageService;

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getContentById(Integer page, Integer rows, Long categoryId){
        EUDataGridResult result = contentManageService.getContentById(page,rows,categoryId);
        return result;
    }

}
