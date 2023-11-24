package web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.impl.BookServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{
    private BookServiceImpl bookService = new BookServiceImpl();
    /**
     * 完成对指定图书得添加操作
     * @param req
     * @param resp
     */
    protected void addCartItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String referer = req.getHeader("referer");
        Book book = bookService.queryBookById(Integer.valueOf(req.getParameter("bookid")));
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 由于Cart是保存在session中的，因此先在session中查看是否存在Cart 如果存在则在原先Cart中添加商品，如果不存在则new一个
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart(0,new BigDecimal(0),new HashMap<Integer,CartItem>());
        }
        cart.addItem(cartItem);
        req.getSession().setAttribute("cart",cart);
        req.getSession().setAttribute("new_add_name",cartItem.getName());
        resp.sendRedirect(referer); // 将网页URL返回到指定的URL下
    }
    protected void deleteCartItem(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        int itemid = Integer.parseInt(req.getParameter("Itemid"));
        String referer = req.getHeader("referer");
        if (cart != null) {
            // 删除该item
            cart.deleteItem(itemid);
            // 重定向到发起请求的网页中
            resp.sendRedirect(referer);
        }
    }

    protected void clear(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        String referer = req.getHeader("referer");
        if(cart != null) {
            cart.clear();
            resp.sendRedirect(referer);
        }
    }

    protected void updateCount(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Integer count = Integer.parseInt(req.getParameter("count"));
        Integer id = Integer.parseInt(req.getParameter("id"));
        String referer = req.getHeader("referer");
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            cart.updateCount(id,count);
            resp.sendRedirect(referer);
        }
    }
}
