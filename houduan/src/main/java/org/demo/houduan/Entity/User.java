package org.demo.houduan.Entity;

import lombok.Data;

/**
 * 最简版 User 实体类
 */
@Data
public class User {
    private Long id;
    private String userName; // 自动对应数据库的 user_name
    private String password; // 自动对应数据库的 password
    private String nickName; // 自动对应数据库的 nick_name
}