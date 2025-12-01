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
    public void register(User user) {
        // 1. 先检查用户名是否被占用
        User existingUser = userMapper.selectByUserName(user.getUserName());
        if (existingUser != null) {
            throw new RuntimeException("用户名已存在，请换一个试试");
        }

        // 2. 如果前端没传昵称，默认设置为用户名（兜底逻辑）
        if (user.getNickName() == null || user.getNickName().isEmpty()) {
            user.setNickName("用户" + user.getUserName());
        }

        // 3. 保存到数据库
        userMapper.insert(user);
    }
}
