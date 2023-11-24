package service.impl;

import dao.OrderDao;
import dao.impl.BookDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import pojo.*;
import service.OrderService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDaoImpl orderDaoimpl = new OrderDaoImpl();
    private OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    private BookDaoImpl bookDao = new BookDaoImpl();
    @Override
    public String CreateOrder(Cart cart, Integer user_id) {
        // 注意orderId需要具有唯一性（最常使用的方法为使用时间戳+用户id的方式完成保存操作）
        String orderId= String.valueOf(System.currentTimeMillis()+user_id);
        orderDaoimpl.saveOrder(new Order(orderId, new Date(),new BigDecimal(String.valueOf(cart.getTotalPrice())),0,user_id));
        // 同时需要对Cart中的CartItem进行遍历，将CartItem转化成OrderItem
        for(Map.Entry<Integer, CartItem> entry: cart.getItems().entrySet()){
            OrderItem orderItem = new OrderItem(null,entry.getValue().getName(),entry.getValue().getCount(),entry.getValue().getPrice(), entry.getValue().getTotalPrice(),orderId );
            orderItemDao.saveOrderItem(orderItem);
            Book book = bookDao.queryBookById(1);
            book.setSales(book.getSales()+orderItem.getCount());
            book.setStock(book.getStock()-orderItem.getCount());
            bookDao.updateBook(book); // 更新首页商品的库存和销量
        }
        // 结账完可以clear整个cart
        cart.clear();
        return  orderId;
    }
}
