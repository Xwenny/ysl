package com.wenny.ysl.controller;

import com.wenny.ysl.domain.EUTreeNode;
import com.wenny.ysl.domain.TaotaoResult;
import com.wenny.ysl.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCatgoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCatList(@RequestParam(value = "id",defaultValue = "0") long parentid){
       List<EUTreeNode> list = contentCategoryService.getCategoryList(parentid);
       return list;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createContentCatgory(Long parentId,String name){
        TaotaoResult result = contentCategoryService.insertComtentCategory(parentId,name);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(long id){
        TaotaoResult result = contentCategoryService.deleteContentCategory(id);
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateContentCategory(long id,String name){
        TaotaoResult result = contentCategoryService.updateContentCategory(id,name);
        return result;
    }


}
