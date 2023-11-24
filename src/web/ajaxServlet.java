package web;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ajaxServlet extends BaseServlet{
    UserServiceImpl userService = new UserServiceImpl();
    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        boolean userexistsUsername = userService.existsUsername(username);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("userexistsUsername",userexistsUsername);
        Gson gson = new Gson();
        String json = gson.toJson(map);
        response.getWriter().write(json);
    }
}
