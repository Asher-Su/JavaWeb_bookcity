package dao.impl;

import dao.BookDao;
import pojo.Book;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao{
    @Override
    public int addBook(Book book) throws SQLException {
        String sql = "INSERT INTO books (id,name,author,price,sales,stock,imgPath)  " +
                "VALUES (?,?,?,?,?,?,?)";
        return update(sql,book.getId(),book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),
                book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) throws SQLException {;
        String sql = "DELETE FROM books WHERE id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "UPDATE books SET NAME=?, author=?, price=?, sales=?, stock=?, imgpath=? WHERE id = ?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "SELECT id,NAME,author,price,sales,stock,imgpath FROM books WHERE id = ?";
        return queryForone(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "SELECT * FROM books";
        return queryForList(Book.class,sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "SELECT COUNT(*) FROM books";
//      // 注意queryForSingleValue()函数返回Number类型
        Number total_books = (Number) queryForSingleValue(sql);
        return total_books.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin,int pageSize) {
        String sql = "SELECT * FROM books limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize); // 注意传的是Book.class类型的class
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "SELECT COUNT(*) FROM books WHERE price between ? and ?";
//      // 注意queryForSingleValue()函数返回Number类型
        Number total_books = (Number) queryForSingleValue(sql,min,max);
        return total_books.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "SELECT * FROM books  WHERE price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize); // 注意传的是Book.class类型的class
    }
}
