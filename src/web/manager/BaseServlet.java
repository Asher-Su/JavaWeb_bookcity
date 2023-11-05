package web.manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String MethodName = req.getParameter("action");
        Class<? extends BaseServlet> cla = this.getClass(); // 其中的this代表实例化BaseServlet的对象或者实例化BaseServlet子类的对象
//            Method method = cla.getMethod(MethodName,HttpServletRequest.class,HttpServletResponse.class);
//            method.invoke(this,req,resp);

        Method protectedMethod= null;
        try {
            protectedMethod = cla.getDeclaredMethod(MethodName, HttpServletRequest.class, HttpServletResponse.class);
            protectedMethod.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String MethodName = req.getParameter("action");
        Class<? extends BaseServlet> cla = this.getClass(); // 其中的this代表实例化BaseServlet的对象或者实例化BaseServlet子类的对象
        try {
//            Method method = cla.getMethod(MethodName,HttpServletRequest.class,HttpServletResponse.class);
//            method.invoke(this,req,resp);
            Method protectedMethod = cla.getDeclaredMethod(MethodName,HttpServletRequest.class,HttpServletResponse.class);
            // protectedMethod.setAccessible(true);
            protectedMethod.invoke(this,req,resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
