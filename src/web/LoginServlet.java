package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.User;
import service.impl.UserServiceImpl;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserServiceImpl userServiceimpl=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPost(req, resp);
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if( userServiceimpl.login(new User(null,username,password,null)) != null){
            req.getRequestDispatcher("/pages/login_success.html").forward(req,resp);
        }else{
            req.getRequestDispatcher("/pages/login.html").forward(req,resp);
        }
    }
}
