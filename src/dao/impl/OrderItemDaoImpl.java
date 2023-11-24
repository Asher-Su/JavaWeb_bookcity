package dao.impl;

import dao.OrderItemDao;
import pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        // 由于id列是自增的因此不需要使用update语句完成自增操作
        String sql = "insert into order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalprice(),orderItem.getOrderId());
    }
}
