CREATE DATABASE IF NOT EXISTS boke;

USE boke;

CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 这里改成了 BIGINT
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `nickname` VARCHAR(50) DEFAULT NULL
);

INSERT INTO `user` (`username`, `password`, `nickname`) VALUES 
('admin', 'admin123', '超级管理员'),
('zhangsan', '123456', '法外狂徒张三'),
('lisi', 'password', '李四'),
('wangwu', 'qwerty', '隔壁老王'),
('xiaoming', '111111', '小明');

