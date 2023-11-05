package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.User;
import service.impl.UserServiceImpl;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private UserServiceImpl serviceimpl=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doGet(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPost(req, resp);
        String username=req.getParameter("username");
        String password=req.getParameter("password1");
        String email=req.getParameter("email");
        // 现在写死验证码为abcd
        String code=req.getParameter("code");

        // 通过equalsIgnoreCase函数来完成忽略大小写操作
        if("abcd".equalsIgnoreCase(code))
        {
            if(!serviceimpl.existsUsername(username)){
                serviceimpl.registerUser(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/register_success.html").forward(req,resp);
            }else{
                System.out.println("用户已存在 注册失败");
                req.getRequestDispatcher("/pages/register.html").forward(req,resp);
            }
        }else{
            // 不正确跳回注册页面
            // 通过getRequestDispatcher函数设定重定向地址，通过forward函数跳转到指定地址
            req.getRequestDispatcher("/pages/register.html").forward(req,resp);
            System.out.println("验证码错误");
        }
    }
}
