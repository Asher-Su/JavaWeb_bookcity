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

public class CartServlet extends BaseServlet{
    private BookServiceImpl bookService = new BookServiceImpl();
    /**
     * 完成对指定图书得添加操作
     * @param req
     * @param resp
     */
    protected void addCartItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Book book = bookService.queryBookById(Integer.valueOf(req.getParameter("bookid")));
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        // 由于Cart是保存在session中的，因此先在session中查看是否存在Cart 如果存在则在原先Cart中添加商品，如果不存在则new一个
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null){
            cart = new Cart(0,new BigDecimal(0),new HashMap<Integer,CartItem>());
        }
        cart.addItem(cartItem);
        System.out.println(cart);
        req.getSession().setAttribute("cart",cart);
        resp.sendRedirect(req.getContextPath());
    }
}
