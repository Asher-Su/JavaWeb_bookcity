package dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

// 由于BaseDao只作为类复用模板，因此只用设置为abstract即可
public abstract class BaseDao {
    private QueryRunner queryRunner=new QueryRunner();
    // 增删改操作
    /*
    * 用于执行update()方法用来执行：Insert\Update\Delete语句(“增删改”)
    * @return 返回-1代表执行失败，返回其他表示影响的行数
    */
    // Object ...args用于接收多个不定长参数
    public int update(String sql,Object ...args){
        Connection conn=null;
        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            // 通过JdbcUtils.close函数完成对连接的关闭
            JdbcUtils.close(conn);
        }
        return -1;
    }

    /**
     *
     * @param type 查询返回一个javaBean的sql语句
     * @param sql 执行sql语句
     * @param args sql对应的参数值
     * @return
     * @param <T> 返回的类型泛型
     */
    public <T> T queryForone(Class<T> type,String sql,Object ...args){
        Connection conn=null;
        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.close(conn);
        }
    }

    /**
     * 查询返回多个JavaBean的sql语句
     * @param type 返回的对象类型
     * @param sql 执行sql语句
     * @param args sql对应的参数值
     * @return
     * @param <T> 返回的类型的泛值
     */
    public <T> List<T> queryForList(Class<T> type,String sql,Object ...args){
        Connection conn=null;
        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.close(conn);
        }
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @return
     */
    public Object queryForSingleValue(String sql,Object ... args){
        Connection conn = null;
        try {
            conn = JdbcUtils.getConnection();
            return queryRunner.query(conn, sql, new ScalarHandler<>(),args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.close(conn);
        }
    }
}
