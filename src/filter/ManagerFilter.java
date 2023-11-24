package filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session.getAttribute("user")!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("http://localhost:8080/project1/pages/login.html");
        }

    }
}
