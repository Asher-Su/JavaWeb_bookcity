package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pojo.User;
import service.impl.UserServiceImpl;

import java.io.IOException;

public class LoginServlet extends BaseServlet {
    private UserServiceImpl userServiceimpl=new UserServiceImpl();
    protected void login(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if( userServiceimpl.login(new User(null,username,password,null)) != null){
            // 登录成功
            // 保存用户登录之后的信息到session域中
            HttpSession session = req.getSession();
            session.setAttribute("user",username);
            req.getRequestDispatcher("/pages/login_success.jsp").forward(req,resp);
        }else{
            req.getRequestDispatcher("/pages/login.html").forward(req,resp);
        }
    }

    protected void logout(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        // 1.销毁session（中的user信息）
        req.getSession().invalidate();
        // 2.重定向到首页
        resp.sendRedirect(req.getContextPath());
    }
}
