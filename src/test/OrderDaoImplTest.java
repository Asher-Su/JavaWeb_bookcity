package test;

import dao.impl.OrderDaoImpl;
import org.junit.Test;
import pojo.Order;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDaoImplTest {
    OrderDaoImpl orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        Order order = new Order("1",new Date(),new BigDecimal(1),1,1);
        orderDao.saveOrder(order);
    }
}