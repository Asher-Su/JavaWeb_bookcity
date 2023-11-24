package test;

import dao.impl.OrderItemDaoImpl;
import org.junit.Test;
import pojo.OrderItem;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoImplTest {
    OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        OrderItem orderItem = new OrderItem(1,"1",1,new BigDecimal(1),new BigDecimal(1),"1");
        orderItemDao.saveOrderItem(orderItem);
    }
}