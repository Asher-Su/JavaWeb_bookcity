package pojo;

import java.math.BigDecimal;
import java.util.*;

/**
 * 购物车对象
 */
public class Cart {
    private Integer totalCount;
    private BigDecimal totalPrice;
    private Map<Integer,CartItem> items=new HashMap<Integer,CartItem>();


    public Cart() {
    }

    public Cart(Integer totalCount, BigDecimal totalPrice, Map<Integer, CartItem> items) {
        this.totalCount = totalCount;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Integer getTotalCount() {
        totalCount = 0;
        for (Map.Entry<Integer,CartItem> entry:items.entrySet()) {
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        totalPrice=new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry:items.entrySet()) {
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + totalCount +
                ", totalPrice=" + totalPrice +
                ", items=" + items +
                '}';
    }

    /**
     * 添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        // 先查看购物车中是否已经添加过此商品，如果已经添加过，则数量累加，总金额更新，如果没有添加过，直接放到集合中即可
        CartItem item = items.get(cartItem.getId());
        if( item == null){ // 如果map中不存在该item则添加到Map中
            items.put(cartItem.getId(),cartItem);
        }else{
            // 已经添加过的结果
            item.setCount(item.getCount()+1); // 数量累加
            item.setTotalPrice(item.getPrice().multiply (new BigDecimal(item.getCount()))); // 更新总金额
        }
        getTotalCount();
        getTotalPrice();
    }

    /**
     * 删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
        getTotalCount();
        getTotalPrice();
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
        getTotalCount();
        getTotalPrice();
    }

    /**
     * 更新商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count){
        // 先查看购物车中是否有此商品，如果有，修改商品数量，更新总金额
        CartItem item = items.get(id);
        if( item != null){
            item.setCount(count); // 修改商品数量
            item.setTotalPrice(item.getPrice().multiply (new BigDecimal(item.getCount()))); // 修改商品总金额
        }
        getTotalCount();
        getTotalPrice();
    }
}
