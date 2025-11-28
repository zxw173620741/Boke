package org.demo.houduan.Controller;

import org.demo.houduan.Entity.User;
import org.demo.houduan.Service.UserService;
import org.demo.houduan.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) {
        System.out.println("收到登录请求，用户名：" + user.getUserName());
        User loginUser = userService.login(user.getUserName(), user.getPassword());
        if (loginUser != null) {
            loginUser.setPassword(null);
            return Result.success(loginUser);
        } else {
            return Result.error("登录失败，用户名或密码错误！");
        }
    }
}
