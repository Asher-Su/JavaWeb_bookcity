package service;

import pojo.Book;
import pojo.Page;

import java.sql.SQLException;
import java.util.List;

public interface BookService {

    /**
     * 完成书目的增添操作
     * @param book
     */
    public void addBook(Book book) throws SQLException;

    /**
     * 完成书目的删除操作
     * @param id
     */
    public void deleteBookById(Integer id) throws SQLException;

    /**
     * 完成对指定id数目的返回
     * @param id
     * @return 返回数目
     */
    public Book queryBookById(Integer id);

    /**
     * 完成对所有数目的返回
     * @return 返回对所有书目的list
     */
    public List<Book> queryBookList();

    /**
     * 完成对book的更新
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 完成对分页book的内容返回
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Book> page(int pageNo,int pageSize);
}
