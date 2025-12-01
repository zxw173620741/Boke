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
@RequestMapping("/api/user")
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
    // ⭐ 新增：注册接口
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        System.out.println("收到注册请求，用户名：" + user.getUserName());

        // 简单参数校验
        if (user.getUserName() == null || user.getUserName().trim().isEmpty()) {
            return Result.error("用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return Result.error("密码不能为空");
        }

        try {
            userService.register(user);
            return Result.success("注册成功");
        } catch (Exception e) {
            // 捕获 Service 层抛出的“用户名已存在”等异常
            return Result.error(e.getMessage());
        }
    }
}
