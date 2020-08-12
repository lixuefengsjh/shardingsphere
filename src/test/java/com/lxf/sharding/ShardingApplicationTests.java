package com.lxf.sharding;

import com.lxf.sharding.entity.Order;
import com.lxf.sharding.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class ShardingApplicationTests {
    /**
     * 1.水平分表：单表查询，排序，统计
     */
    @Autowired
    OrderServiceImpl orderServiceImpl;
    @Test
   public void testInsert(){
        Order order =new Order();
        order.setUserId(0l);
        order.setOrderAmount(BigDecimal.ONE);
        order.setOrderId(0l);
        order.setOrderNo("lxf--000");
        order.setOrderStatus(1);
        order.setRemark("第一条测试数据");
        orderServiceImpl.insert(order);
    }
    @Test
    public void testSelect(){
        Order o=orderServiceImpl.selectByPrimaryKey(1l);
        System.out.println(o);
    }
    @Test
    public  void testCount(){
        Long l=orderServiceImpl.count();
        System.out.println(l);
    }
    @Test
    public void testOder(){
        List<Order> list=orderServiceImpl.selectAllByOrder();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    @Test
    public void updateByPrimaryKey(){
        Order order =new Order();
        order.setUserId(0l);
        order.setOrderAmount(BigDecimal.ONE);
        order.setOrderId(0l);
        order.setOrderNo("lxf--000");
        order.setOrderStatus(1);
        order.setRemark("第一条测试数据--修改--001");
       int i= orderServiceImpl.updateByPrimaryKey(order);
        System.out.println("修改成功");
    }
}
