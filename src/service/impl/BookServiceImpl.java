package service.impl;

import dao.impl.BookDaoImpl;
import pojo.Book;
import pojo.Page;
import service.BookService;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDaoImpl bookdaoimpl = new BookDaoImpl();

    @Override
    public void updateBook(Book book) {
        bookdaoimpl.updateBook(book);
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page_book=new Page<Book>();
        // 设置当前页码
        page_book.setPageNo(pageNo);
        // 边界判断
        if(pageNo < 1){
            pageNo = 1;
            page_book.setPageNo(1);
        }
        // 设置每页展示的数量
        page_book.setPageSize(pageSize);
        // 设置图书总数
        Integer pageTotalCount = null;
        page_book.setPageTotal(pageTotalCount);
        // 设置图书总数
        Integer totalCount= bookdaoimpl.queryForPageTotalCount();
        page_book.setPageTotalCount(totalCount);
        // 设置页码总数
        Integer pageTotal = totalCount / pageSize;
        if (totalCount % pageSize > 0){
            pageTotal++;
        }
        page_book.setPageTotal(pageTotal);
        // 边界判断
        if(pageNo > page_book.getPageTotal()){
            pageNo = page_book.getPageTotal();
            page_book.setPageNo(pageNo);
        }
        // 设置最后返回给页面的图书list
        int begin = (page_book.getPageNo()-1) * page_book.getPageSize(); //表示返回的第一个图书编号
        List<Book> bookList = bookdaoimpl.queryForPageItems(begin, pageSize);
        page_book.setItems(bookList);

        return page_book;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize,int min,int max) {
        Page<Book> page_book=new Page<Book>();
        // 设置当前页码
        page_book.setPageNo(pageNo);
        // 边界判断
        if(pageNo < 1){
            pageNo = 1;
            page_book.setPageNo(1);
        }
        // 设置每页展示的数量
        page_book.setPageSize(pageSize);
        // 设置图书总数
        Integer pageTotalCount = null;
        page_book.setPageTotal(pageTotalCount);
        // 设置图书总数
        Integer totalCount= bookdaoimpl.queryForPageTotalCountByPrice(min,max);
        page_book.setPageTotalCount(totalCount);
        // 设置页码总数
        Integer pageTotal = totalCount / pageSize;
        if (totalCount % pageSize > 0){
            pageTotal++;
        }
        page_book.setPageTotal(pageTotal);
        // 边界判断
        if(pageNo > page_book.getPageTotal()){
            pageNo = page_book.getPageTotal();
            page_book.setPageNo(pageNo);
        }
        // 设置最后返回给页面的图书list
        int begin = (page_book.getPageNo()-1) * page_book.getPageSize(); //表示返回的第一个图书编号
        List<Book> bookList = bookdaoimpl.queryForPageItemsByPrice(begin, pageSize,min,max);
        page_book.setItems(bookList);

        return page_book;
    }

    @Override
    public void addBook(Book book) throws SQLException {
        bookdaoimpl.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) throws SQLException {
        bookdaoimpl.deleteBookById(id);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookdaoimpl.queryBookById(id);
    }

    @Override
    public List<Book> queryBookList() {
        return bookdaoimpl.queryBooks();
    }
}
