package com.taotao.order.service;

import com.taotao.order.dao.JedisClient;
import com.wenny.ysl.dao.TbOrderItemMapper;
import com.wenny.ysl.dao.TbOrderMapper;
import com.wenny.ysl.dao.TbOrderShippingMapper;
import com.wenny.ysl.domain.TaotaoResult;
import com.wenny.ysl.domain.TbOrder;
import com.wenny.ysl.domain.TbOrderItem;
import com.wenny.ysl.domain.TbOrderShipping;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private JedisClient jedisClient;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;

    @Value("${ORDER_GEN_KEY}")
    private String ORDER_GEN_KEY;

    @Value("${ORDER_INIT_ID}")
    private String ORDER_INIT_ID;
    @Value("${ORDER_DETAIL_GEN_KEY}")
    private String ORDER_DETAIL_GEN_KEY;

    @Override
    public TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping) {
        String string = jedisClient.get(ORDER_GEN_KEY);
        if (StringUtils.isBlank(string)){
            jedisClient.set(ORDER_GEN_KEY,ORDER_INIT_ID);
        }
        long orderId = jedisClient.incr(ORDER_GEN_KEY);
        order.setOrderId(orderId+"");
        order.setStatus(1);
        Date date = new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        order.setBuyerRate(0);
        orderMapper.insert(order);
        for (TbOrderItem tbOrderItem:itemList){
            long orderDetailId = jedisClient.incr(ORDER_DETAIL_GEN_KEY);
            tbOrderItem.setId(orderDetailId+"");
            tbOrderItem.setOrderId(orderId+"");

            orderItemMapper.insert(tbOrderItem);

        }

        orderShipping.setOrderId(orderId+"");
        orderShipping.setCreated(date);
        orderShipping.setUpdated(date);
        orderShippingMapper.insert(orderShipping);
        return TaotaoResult.ok(orderId);
    }
}
