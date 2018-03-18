package com.taotao.order.service;

import com.wenny.ysl.domain.TaotaoResult;
import com.wenny.ysl.domain.TbOrder;
import com.wenny.ysl.domain.TbOrderItem;
import com.wenny.ysl.domain.TbOrderShipping;

import java.util.List;

public interface OrderService {
    TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);
}
