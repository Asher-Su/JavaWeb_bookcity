package web.client;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Book;
import pojo.Page;
import service.impl.BookServiceImpl;

import java.io.IOException;

public class ClientServlet extends BaseServlet{
    BookServiceImpl bookServiceimpl=new BookServiceImpl();
    /**
     * 处理分页功能
     * @param req
     * @param resp
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.获取请求的参数pageNo 和 pageSize
        int pageNo,pageSize;
        if(req.getParameter("pageNo")==null){
            pageNo = 1;
        }else{
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        if(req.getParameter("pageSize")==null){
            pageSize = Page.PAGE_SIZE;
        }else{
            pageSize = Integer.parseInt(req.getParameter("pageSize"));
        }
        // 2.调用BookService.page(pageNo,pageSize) ------->返回Page对象
        Page<Book> page = bookServiceimpl.page(pageNo, pageSize);
        // 3.保存Page对象到Request域中
        req.setAttribute("page",page);
        // 4.请求转发到manager.jsp页面中
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
    protected void pageByPrice(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        int pageNo,pageSize;
        if(req.getParameter("pageNo")==null){
            pageNo = 1;
        }else{
            pageNo = Integer.parseInt(req.getParameter("pageNo"));
        }
        if(req.getParameter("pageSize")==null){
            pageSize = Page.PAGE_SIZE;
        }else{
            pageSize = Integer.parseInt(req.getParameter("pageSize"));
        }
        int min,max;
        if(req.getParameter("min") == null){
            min=0;
        }else{
            min = Integer.parseInt(req.getParameter("min"));
        }
        if(req.getParameter("max") == null){
            // 用Integer中最大数作为max的默认最大值
            max=Integer.MAX_VALUE;
        }else{
            max = Integer.parseInt(req.getParameter("max"));
        }
        Page<Book> page = bookServiceimpl.pageByPrice(pageNo, pageSize,min,max);
        req.setAttribute("page",page);
        req.setAttribute("min",min);
        req.setAttribute("max",max);
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
