package com.wenny.ysl.service;

import com.wenny.ysl.dao.TbItemMapper;
import com.wenny.ysl.pojo.TbItem;
import com.wenny.ysl.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemById(long itemId){

        //TbItem item = itemMapper.selectByPrimaryKey(itemId);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size()>0){
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }
}
