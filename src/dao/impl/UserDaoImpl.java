package dao.impl;

import dao.UserDao;
import pojo.User;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id,'username','password','email' from users where username = ?";
        return queryForone(User.class,sql,username);
    }

    @Override
    public int saveUser(User user) {
        String sql="INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public User queryUserByUsernameandpassword(String username,String password) {
        String sql = "select id,'username','password','email' from users where username = ? and password = ?";
        return queryForone(User.class,sql,username,password);
    }
}
