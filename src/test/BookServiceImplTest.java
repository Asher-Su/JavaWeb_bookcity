package test;

import org.junit.Test;
import pojo.Book;
import pojo.Page;
import service.impl.BookServiceImpl;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class BookServiceImplTest {
    BookServiceImpl bookServiceimpl = new BookServiceImpl();

    @Test
    public void addBook() throws SQLException {
        Book TestaddBook = new Book(3,"好人一生平安","syh", new BigDecimal(30),
                500,1000,"static/img/haorenyishengpingan.jpg");
        bookServiceimpl.addBook(TestaddBook);
    }

    @Test
    public void deleteBookById() throws SQLException {
        bookServiceimpl.deleteBookById(3);
    }

    @Test
    public void queryBookById() {
        System.out.println(bookServiceimpl.queryBookById(2));
    }

    @Test
    public void queryBookList() {
        System.out.println(bookServiceimpl.queryBookList());
    }

    @Test
    public void updateBook() {
        bookServiceimpl.updateBook(new Book(3,"好人一生平安","syh", new BigDecimal(30),
                500,1000,"static/img/haorenyishengpingan.jpg"));
    }

    @Test
    public void page(){
        Page<Book> page = bookServiceimpl.page(1, 4);
        System.out.println(page.getItems());
    }

    @Test
    public void pageByPrice(){
        Page<Book> bookPage = bookServiceimpl.pageByPrice(1, 2, 6, 50);
        System.out.println(bookPage.getItems());
    }
}