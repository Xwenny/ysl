package com.wenny.ysl.service;

import com.wenny.ysl.dao.TbContentCategoryMapper;
import com.wenny.ysl.domain.EUTreeNode;
import com.wenny.ysl.domain.TaotaoResult;
import com.wenny.ysl.domain.TbContentCategory;
import com.wenny.ysl.domain.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;
    @Override
    public List<EUTreeNode> getCategoryList(long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();
        for (TbContentCategory tbContentCategory:list){
            EUTreeNode node = new EUTreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }

    @Override
    public TaotaoResult insertComtentCategory(long parentId, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        contentCategory.setStatus(1);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        contentCategoryMapper.insert(contentCategory);
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (!parentCat.getIsParent()){
            parentCat.setIsParent(true);
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }
        return TaotaoResult.ok(contentCategory);

    }

    @Override
    public TaotaoResult deleteContentCategory(long id) {
        TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(id);

        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        contentCategoryMapper.deleteByExample(example);
        TbContentCategoryExample parentExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria parentCriteria = parentExample.createCriteria();
        parentCriteria.andParentIdEqualTo(tbContentCategory.getParentId());
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(parentExample);
        if (list.isEmpty()) {
            TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
            parentCat.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }

            return TaotaoResult.ok();




        }

}
