package service;

import pojo.Cart;

public interface OrderService {
    public String CreateOrder(Cart cart,Integer user_id);
}
