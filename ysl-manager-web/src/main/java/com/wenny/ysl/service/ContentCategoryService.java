package com.wenny.ysl.service;

import com.wenny.ysl.domain.EUTreeNode;
import com.wenny.ysl.domain.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    List<EUTreeNode> getCategoryList(long parentId);
    TaotaoResult insertComtentCategory(long parentId,String name);
    TaotaoResult deleteContentCategory(long id);
    TaotaoResult updateContentCategory(long id,String name);

}
