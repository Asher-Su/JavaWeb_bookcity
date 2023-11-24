package filter;

import jakarta.servlet.*;
import utils.JdbcUtils;

import java.io.IOException;
import java.sql.SQLException;

public class TransactionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose(); // 提交事务
        } catch (Exception e){
            try {
                JdbcUtils.rollbackAndClose();  // 回滚事务
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            e.printStackTrace(); // 完成异常的捕获后进行打印输出
            throw new RuntimeException(e); // ！！！完成异常的抛出,将异常抛给Tomcat服务器使得web.xml中的error-page可以捕获并进行处理
        }
    }
}
