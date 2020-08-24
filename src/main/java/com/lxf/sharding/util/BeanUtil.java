package com.lxf.sharding.util;

import com.lxf.sharding.entity.Order;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lxf
 * @create: 2020-08-23 20:39
 * @description: bean工具类------待删除
 */
@Slf4j
public class BeanUtil {
    public void mapToBean(Map map, Object bean ){
        if(null==bean){
            log.info("bean 对象不能为空");
        }
        Class clazz=bean.getClass();
        //获取clazz的所有
        Field[] fields= clazz.getDeclaredFields();
        //为对应的fild字段设置值
        for (Field f: fields) {
            String fildName =f.getName();
            //为每一个字段赋值
            f.setAccessible(true);
            try {
                f.set(bean,map.get(fildName));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    };
    public void beanToMap(Object bean,Map map) throws IllegalAccessException {
        Class clazz=bean.getClass();
        Field[] fields=clazz.getDeclaredFields();
        for (Field f:fields) {
            f.setAccessible(true);
            map.put(f.getName(),f.get(bean));
        }

    }
    public static void main(String[] args) throws IllegalAccessException {
        BeanUtil beanUtil=new BeanUtil();
        Order order=new Order();
        Map map=new HashMap();
        map.put("orderNo","233");
        beanUtil.mapToBean(map,order);
        Map newMap=new HashMap();
        beanUtil.beanToMap(order,newMap);
    }
}
