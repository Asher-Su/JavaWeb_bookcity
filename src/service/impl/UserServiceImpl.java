package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import pojo.User;
import service.UserService;

public class UserServiceImpl implements UserService {
    // 创建实现该接口的接口对象
    private UserDao userDao=new UserDaoImpl();
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username)==null)
        {
            return false;
        }else{
            return true;
        }
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameandpassword(user.getUsername(),user.getPassword());
    }
}
