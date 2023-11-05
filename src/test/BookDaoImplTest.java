package test;

import dao.impl.BookDaoImpl;
import org.junit.Test;
import pojo.Book;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoImplTest {

    BookDaoImpl bookdaoimpl= new BookDaoImpl();
    @Test
    public void addBook() throws SQLException {
        Book TestaddBook = new Book(3,"好人一生平安","syh",new BigDecimal(30),500,1000,"static/img/haorenyishengpingan.jpg");
        bookdaoimpl.addBook(TestaddBook);
    }

    @Test
    public void deleteBookById() throws SQLException {
        bookdaoimpl.deleteBookById(3);
    }

    @Test
    public void queryBookById() {
        Book book = bookdaoimpl.queryBookById(1);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> BookList = bookdaoimpl.queryBooks();
        System.out.println(BookList);
    }
    @Test
    public void updateBook() {
        Book TestupdataBook = new Book(3,"4","5",new BigDecimal(6),7,8,"9");
        bookdaoimpl.updateBook(TestupdataBook);
    }
    @Test
    public void queryForPageTotalCount(){
        Integer totalCount = bookdaoimpl.queryForPageTotalCount();
        System.out.println(totalCount);
    }
    @Test
    public void queryForPageItems(){
        List<Book> bookList = bookdaoimpl.queryForPageItems(0, 4);
        for (Book book : bookList){
            System.out.println(book);
        }
    }
}