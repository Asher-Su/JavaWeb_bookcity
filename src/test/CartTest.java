package test;

import org.junit.Test;
import pojo.Cart;
import pojo.CartItem;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CartTest {
    Map<Integer, CartItem> map= new HashMap<Integer,CartItem>();
    Cart cart = new Cart();
    CartItem cartItem = new CartItem(1,"ll",1,new BigDecimal(10),new BigDecimal(10));
    @Test
    public void addItem() {
        cart.addItem(cartItem);
        cart.addItem(cartItem);
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.addItem(cartItem);
        cart.addItem(cartItem);
        cart.deleteItem(cartItem.getId());
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(cartItem);
        cart.addItem(cartItem);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.addItem(cartItem);
        cart.addItem(cartItem);
        cart.updateCount(1,3);
        System.out.println(cart);
    }
}