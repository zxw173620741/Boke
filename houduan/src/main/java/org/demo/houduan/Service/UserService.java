package org.demo.houduan.Service;

import org.demo.houduan.Entity.User;
import org.demo.houduan.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User login(String userName, String password) {
        User user = userMapper.selectByUserName(userName);
        if (user != null && user.getPassword().equals(password)) {
            return user; // 密码正确，登录成功
        }
        return null; // 用户不存在或密码错误
    }
}
