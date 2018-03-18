package com.wenny.ysl.rest.service;

import com.wenny.ysl.domain.TaotaoResult;

public interface ItemService {
    TaotaoResult getItemBaseInfo(long itemId);
    TaotaoResult getItemDesc(long itemId);
    TaotaoResult getItemParam(long itemId);
}
