package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;
import service.impl.OrderServiceImpl;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.Assert.*;

public class OrderServiceImplTest {
    OrderServiceImpl orderService = new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart = new Cart(1,new BigDecimal(10),new HashMap<Integer, CartItem>());
        String s = orderService.CreateOrder(cart, 1);
        System.out.println(s);
    }
}