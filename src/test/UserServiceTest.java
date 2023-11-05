package test;

import org.junit.Test;
import pojo.User;
import service.UserService;
import service.impl.UserServiceImpl;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService=new UserServiceImpl();
    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"leiyu","123","123@qq.com"));
    }

    @Test
    public void login() {
        if(userService.login(new User(null,"leiyuu","123","123@qq.com"))!=null){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("leiyu")){
            System.out.println("存在该用户名");
        }else{
            System.out.println("不存在该用户");
        }
    }
}