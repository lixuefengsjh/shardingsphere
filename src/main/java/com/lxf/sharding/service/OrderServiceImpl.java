package com.lxf.sharding.service;

import cn.hutool.core.bean.BeanUtil;
import com.lxf.sharding.dao.OrderMapper;
import com.lxf.sharding.entity.Order;
import com.lxf.sharding.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RedisUtil  redisUtil;

    private static  final  String ORDER_PRIFIX="ORDER_";

    public void insert(Order record){
       int i= orderMapper.insert(record);
        System.out.println("插入成功----i:"+i);
    }
    public Order selectByPrimaryKey(Long id){
        Order order=new Order();
        if(null!=redisUtil.hGetAll(String.format("ORDER_PRIFIX%s",id))){
             BeanUtils.copyProperties(order,redisUtil.hGetAll(String.format("ORDER_PRIFIX%s",id)));
            return order;
        }
        order= orderMapper.selectByPrimaryKey(id);

        redisUtil.hPutAll(String.format("ORDER_PRIFIX%s",id),BeanUtil.beanToMap(order));
        return order;
    }
    public  Long count(){
        return orderMapper.count();
    }

    public List<Order> selectAllByOrder(){
        return orderMapper.countAllByOrder();
    }

    public int updateByPrimaryKey(Order order){

        return orderMapper.updateByPrimaryKey(order);
    }
}
