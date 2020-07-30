package com.lxf.sharding.service;

import com.lxf.sharding.dao.OrderMapper;
import com.lxf.sharding.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl {
    @Autowired
    private OrderMapper orderMapper;

    public void insert(Order record){
       int i= orderMapper.insert(record);
        System.out.println("插入成功----i:"+i);
    }
    public Order selectByPrimaryKey(Long id){
       return orderMapper.selectByPrimaryKey(id);
    }
    public  Long count(){
        return orderMapper.count();
    }
    public List<Order> selectAllByOrder(){
        return orderMapper.countAllByOrder();
    }
}
