package com.itheIma.dao;

import com.itheIma.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author 意风秋
 * @Date 2020/08/27 18:24
 * @Creed 这一页的代码我看不懂
 **/
public interface OrderSettingDao {
    

    void editNumberByOrderDate(OrderSetting orderSetting);

    void add(OrderSetting orderSetting);


    long findCountByOrderDate(Date orderDate);

    public List<OrderSetting> getOrderSettingByMonth(Map date);
}
