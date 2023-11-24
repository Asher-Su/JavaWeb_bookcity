package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Cart;
import service.impl.OrderServiceImpl;
import utils.JdbcUtils;

import java.io.IOException;
import java.sql.SQLException;

public class OrderServlet extends BaseServlet{
    OrderServiceImpl orderService = new OrderServiceImpl();
    private String orderId;
    protected void createOrder(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException, SQLException {
        if (req.getSession().getAttribute("user") != null) {
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            // 保证创建订单时候的业务正常，出错进行rollback操作，不出错则commit操作
            orderId = orderService.CreateOrder(cart, 1);
            req.getSession().setAttribute("orderId", orderId); // 由于重定向之后request域是不共享的因此需要进行这样的设置
            resp.sendRedirect("pages/cart/checkout.jsp");
        }else{
            resp.sendRedirect("pages/login.html");
            return ; // 养成习惯在重定向之后不发生任何操作时写，return ;
        }
    }
}
