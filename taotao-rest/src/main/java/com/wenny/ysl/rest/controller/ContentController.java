package com.wenny.ysl.rest.controller;

import com.wenny.ysl.domain.TaotaoResult;
import com.wenny.ysl.domain.TbContent;
import com.wenny.ysl.rest.service.ContentService;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public TaotaoResult getContentList(@PathVariable Long contentCategoryId){
        try {
            List<TbContent> list = contentService.getContentList(contentCategoryId);
            return TaotaoResult.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500, utils.ExceptionUtil.getStackTrace(e));
        }

    }
}
