package dao;

import pojo.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    /**
     * 完成对书目的更新操作
     * @param book
     * @return
     */
    public int updateBook(Book book);
    /**
     * 向数据库添加图书
     * @param book
     * @return 返回添加成功与否标签
     */
    public int addBook(Book book) throws SQLException;

    /**
     * 通过id完成对数据库中图书信息的删除
     * @param id
     * @return 返回删除成功与否的标签
     */
    public int deleteBookById(Integer id) throws SQLException;

    /**
     * 根据id完成对目标图书信息的查询
     * @param id
     * @return 返回查询到的图书
     */
    public Book queryBookById(Integer id);

    /**
     * 完成对数据库中所有图书信息的查询
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 完成对总共几页的查询
     * @return
     */
     public Integer queryForPageTotalCount();

     public List<Book> queryForPageItems(int begin,int pageSize);
     
    public Integer queryForPageTotalCountByPrice(int min, int max);

    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
