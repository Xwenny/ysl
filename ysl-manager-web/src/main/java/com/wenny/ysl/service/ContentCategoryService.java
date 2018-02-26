package com.wenny.ysl.service;

import com.wenny.ysl.domain.EUTreeNode;

import java.util.List;

public interface ContentCategoryService {
    List<EUTreeNode> getCategoryList(long parentId);
}
