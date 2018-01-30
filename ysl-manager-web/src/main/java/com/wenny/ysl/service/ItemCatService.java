package com.wenny.ysl.service;

import com.wenny.ysl.domain.TbItemCat;

import java.util.List;

public interface ItemCatService {

    public List<TbItemCat> getItemCatList(Long parentId);
}
