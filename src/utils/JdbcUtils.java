package utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    // 设置数据库连接池
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();
    static {
        try {
            Properties properties = new Properties();
            // 读取jdbc.properties属性配置文件
            InputStream inputstream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 通过load函数完成对输入流资源的加载
            properties.load(inputstream);
            // 根据properties提供的资源创建资源池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            // System.out.println(dataSource.getConnection()); // 尝试打印从数据库连接池获取连接,有打印证明数据库连接池连接数据成功
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*
    * 获取数据库连接池中的连接
    * @return 如果返回null证明连接建立失败，如果返回connection证明连接建立成功
    */
    public static Connection getConnection() throws SQLException {
        Connection conn = conns.get();
        if(conn == null){
            conn = dataSource.getConnection(); //从数据库连接池获取连接
            conns.set(conn); // 将连接保存到ThreadLocal中,供之后的jdbc操作使用
            conn.setAutoCommit(false); // 调用setAutoCommit函数来完成对conn设置为手动管理事务
        }
        return conn;
    }

    /**
     * 提交并关闭事务
     */
    public static void commitAndClose() throws SQLException {
        Connection connection = conns.get();
        if(connection != null){
            connection.commit(); // 完成事务的提交操作
            connection.close();
        }
        conns.remove(); //！！！在结束时一定记住执行remove操作,否则会出错(由于Tomcat底层使用了线程池技术)
    }

    /**
     * 回滚并关闭事务
     */
    public static void rollbackAndClose() throws SQLException {
        Connection connection = conns.get();
        if (connection != null) {
            connection.rollback();
            connection.close();
        }
    }
    // 关闭连接，放回数据库连接池
//    public static void close(Connection conn){
//        try {
//            if(conn != null){ // 对connection的
//                conn.close();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
