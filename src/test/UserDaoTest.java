package test;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import org.junit.Test;
import pojo.User;

import static org.junit.Assert.*;

public class UserDaoTest {
    // 完成一个接口的实例化
    UserDao userDao=new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        if(userDao.queryUserByUsername("admin")==null)
        {
            System.out.println("用户名可用");
        }else{
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameandpassword() {
        if(userDao.queryUserByUsernameandpassword("admin","admin")==null)
        {
            System.out.println("用户名或密码错误");
        }else{
            System.out.println("用户成功登录");
        }
    }

    @Test
    public void saveUser() {
        if(userDao.saveUser(new User(null,"1","1","1"))!=-1){
            System.out.println("成功创建");
        }else{
            System.out.println("创建失败");
        }
    }


}