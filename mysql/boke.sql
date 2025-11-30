CREATE DATABASE IF NOT EXISTS boke;

USE boke;

CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user_name` VARCHAR(50) NOT NULL COMMENT '对应Java属性: userName',
  `password` VARCHAR(100) NOT NULL COMMENT '对应Java属性: password',
  `nick_name` VARCHAR(50) DEFAULT NULL COMMENT '对应Java属性: nickName'
);

INSERT INTO `user` (`user_name`, `password`, `nick_name`) VALUES 
('admin', 'admin123', '超级管理员'),
('zhangsan', '123456', '法外狂徒张三'),
('lisi', 'password', '李四'),
('wangwu', 'qwerty', '隔壁老王'),
('xiaoming', '111111', '小明');

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '文章ID',
  `user_id` BIGINT NOT NULL COMMENT '作者ID (关联user表)',
  
  `content` TEXT NOT NULL COMMENT '文章内容',
  `images` TEXT DEFAULT NULL COMMENT '图片地址(多张用逗号分隔)', 
  
  `source` VARCHAR(50) DEFAULT '网页版' COMMENT '发布来源',
  
  `like_count` INT DEFAULT 0 COMMENT '点赞数',
  `comment_count` INT DEFAULT 0 COMMENT '评论数',
  `share_count` INT DEFAULT 0 COMMENT '转发数',
  
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  
  -- 建立外键约束（可选，也可以不做物理外键，只做逻辑关联）
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';