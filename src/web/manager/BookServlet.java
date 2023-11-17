package web.manager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Book;
import pojo.Page;
import service.impl.BookServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookServiceImpl bookServiceimpl = new BookServiceImpl();
    protected void list(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        List<Book> bookslist = bookServiceimpl.queryBookList();
        req.setAttribute("book_list",bookslist);
//        req.getRequestDispatcher("/pages/manager/manager.jsp").forward(req,resp);
        req.getRequestDispatcher("/pages/manager/manager.jsp").forward(req,resp);
    }

    /**
     * 处理分页功能
     * @param req
     * @param resp
     */
    protected void page(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
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
        req.setAttribute("page_list",page.getItems());
        // 4.请求转发到manager.jsp页面中
        req.getRequestDispatcher("/pages/manager/manager.jsp").forward(req,resp);
    }

    protected void add(HttpServletRequest req,HttpServletResponse resp) throws SQLException, ServletException, IOException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        pageNo++;
        Book book =new Book(null,req.getParameter("name"),req.getParameter("author"),
                new BigDecimal(req.getParameter("price")),Integer.parseInt(req.getParameter("sales")),
                Integer.parseInt(req.getParameter("stock")),null);
        bookServiceimpl.addBook(book);
//        注意这段代码采用了一次request请求的过程完成了需求，但这也会导致客户在浏览器刷新时，重新提交table重新发送请求，注意这里要用请求重定向发两次request
//        req.getRequestDispatcher("/manager/servlet?action=list").forward(req,resp);
        resp.sendRedirect(req.getContextPath()+"/manager/servlet?action=page&pageNo="+pageNo);
    }
    protected void delete(HttpServletRequest req,HttpServletResponse resp) throws SQLException, IOException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        String id = req.getParameter("id");
        bookServiceimpl.deleteBookById(Integer.parseInt(id));
        resp.sendRedirect(req.getContextPath()+"/manager/servlet?action=page&pageNo="+pageNo);
    }
    protected void update(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        Book book =new Book(Integer.parseInt(req.getParameter("id")),req.getParameter("name"),req.getParameter("author"),
                new BigDecimal(req.getParameter("price")),Integer.parseInt(req.getParameter("sales")),
                Integer.parseInt(req.getParameter("stock")),null);
        bookServiceimpl.updateBook(book);
        resp.sendRedirect(req.getContextPath()+"/manager/servlet?action=page&pageNo="+pageNo);
    }
    protected void getBook(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = Integer.parseInt(req.getParameter("pageNo"));
        String id = req.getParameter("id");
        Book book = bookServiceimpl.queryBookById(Integer.parseInt(id));
        req.setAttribute("edit_bookitem",book);
        req.setAttribute("pageNo",pageNo);
        req.getRequestDispatcher("/pages/manager/manager_edit.jsp?pageNo="+pageNo).forward(req,resp);
    }
}
