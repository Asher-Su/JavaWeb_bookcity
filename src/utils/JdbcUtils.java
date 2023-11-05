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
        Connection conn = null;
        conn = dataSource.getConnection();
        return conn;
    }

    // 关闭连接，放回数据库连接池
    public static void close(Connection conn){
        try {
            if(conn != null){ // 对connection的
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
