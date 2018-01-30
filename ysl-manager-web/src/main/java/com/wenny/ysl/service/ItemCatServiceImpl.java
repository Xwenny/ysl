package com.wenny.ysl.service;

import com.wenny.ysl.dao.TbItemCatMapper;
import com.wenny.ysl.domain.TbItemCat;
import com.wenny.ysl.domain.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> getItemCatList(Long parentId){

        TbItemCatExample example = new TbItemCatExample();
        //设置查询条件
        TbItemCatExample.Criteria criteria = example.createCriteria();
        //根据parentid查询子节点
        criteria.andParentIdEqualTo(parentId);
        //返回子节点列表
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        return list;
    }


}
