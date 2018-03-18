package com.wenny.ysl.portal.service;

import com.wenny.ysl.domain.TbItem;
import com.wenny.ysl.portal.pojo.ItemInfo;

public interface ItemService {
    ItemInfo getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParam(Long itemId);
}
