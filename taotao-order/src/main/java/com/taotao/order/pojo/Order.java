package com.taotao.order.pojo;

import com.wenny.ysl.domain.TbOrder;
import com.wenny.ysl.domain.TbOrderItem;
import com.wenny.ysl.domain.TbOrderShipping;

import java.util.List;

public class Order extends TbOrder {
    private List<TbOrderItem> orderItems;
    private TbOrderShipping orderShipping;

    public List<TbOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TbOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public TbOrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(TbOrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }
}
