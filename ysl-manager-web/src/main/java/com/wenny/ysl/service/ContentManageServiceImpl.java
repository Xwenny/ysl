package com.wenny.ysl.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wenny.ysl.dao.TbContentMapper;
import com.wenny.ysl.domain.EUDataGridResult;
import com.wenny.ysl.domain.TbContent;
import com.wenny.ysl.domain.TbContentCategoryExample;
import com.wenny.ysl.domain.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentManageServiceImpl implements ContentManageService {

    @Autowired
    TbContentMapper tbContentMapper;

    @Override
    public EUDataGridResult getContentById(Integer page, Integer rows, long id) {
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(id);
        PageHelper.startPage(page,rows);
        List<TbContent> list = tbContentMapper.selectByExample(tbContentExample);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
