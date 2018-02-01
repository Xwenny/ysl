package com.wenny.ysl.controller;

import com.wenny.ysl.domain.EUDataGridResult;
import com.wenny.ysl.domain.TaotaoResult;
import com.wenny.ysl.domain.TbItem;
import com.wenny.ysl.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }

    @RequestMapping("/item/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows){
        EUDataGridResult result = itemService.getItemList(page,rows);
        return result;
    }

    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    @ResponseBody
    private TaotaoResult createItem(TbItem item){
        TaotaoResult result = itemService.createItem(item);
        return result;
    }
}
