package com.wenny.ysl.service;

import com.wenny.ysl.domain.EUDataGridResult;
import com.wenny.ysl.domain.TbItem;

public interface ItemService {
    TbItem getItemById(long itemId);

    EUDataGridResult getItemList(int page, int rows);
}
