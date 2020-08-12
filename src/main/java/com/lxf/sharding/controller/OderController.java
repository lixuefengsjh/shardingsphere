package com.lxf.sharding.controller;

import com.lxf.sharding.entity.Order;
import com.lxf.sharding.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/order/")
public class OderController {
     @Autowired
     private OrderServiceImpl orderServiceImpl;

     @GetMapping("{id}")
     public Order get(@PathVariable("id") Long id){
          return  orderServiceImpl.selectByPrimaryKey(id);
     }

     @PostMapping("update")
     public void update(){
          Order order =new Order();
          order.setUserId(0l);
          order.setOrderAmount(BigDecimal.ONE);
          order.setOrderId(0l);
          order.setOrderNo("lxf--000");
          order.setOrderStatus(1);
          order.setRemark("第一条测试数据--修改--001");
          orderServiceImpl.updateByPrimaryKey(order);
     }
}
