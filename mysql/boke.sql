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