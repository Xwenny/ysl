package com.taotao.order.controller;

import com.taotao.order.pojo.Order;
import com.taotao.order.service.OrderService;
import com.wenny.ysl.domain.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.ExceptionUtil;


@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult createOrder(@RequestBody Order order) {
        try {
            TaotaoResult result = orderService.createOrder(order, order.getOrderItems(), order.getOrderShipping());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/shishi")
    @ResponseBody
    public void createOrder() {
        System.out.println("shishi");
    }

    @RequestMapping("/")
    @ResponseBody
    public void createOrder2() {
        System.out.println("shishi2");
    }
}