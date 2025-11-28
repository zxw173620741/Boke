package org.demo.houduan;

import org.demo.houduan.Entity.User;
import org.demo.houduan.Mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HouduanApplicationTests {
    @Autowired // 2. 自动注入你写好的 Mapper 接口
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        User user = userMapper.selectByUserName("admin");

        if (user != null) {
            System.out.println("🎉 查询成功！");
            System.out.println("用户ID: " + user.getId());
            System.out.println("用户名: " + user.getUserName());
            System.out.println("昵称: " + user.getNickName());
            System.out.println("完整对象: " + user);
        } else {
            System.out.println("❌ 查询失败，未找到该用户。");
        }

        System.out.println("========== 测试结束 ==========");
    }
}
