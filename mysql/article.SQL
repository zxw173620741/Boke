USE boke;

-- 1. 先清空旧数据 (重置 ID 从 1 开始)
TRUNCATE TABLE article;

-- 2. 插入模拟文章数据
-- 注意：user_id 对应你之前 user 表里的 id (1=admin, 2=zhangsan, 3=lisi...)

INSERT INTO article (user_id, content, images, source, like_count, comment_count, share_count, create_time) VALUES 
-- 第一条：纯文字微博
(1, '今天终于把 Spring Boot + Vue3 的环境搭建好了！前后端分离开发模式真的很爽，尤其是 Pinia 的状态管理，比 Vuex 简洁太多了。加油，继续肝！💪 #编程 #学习记录', NULL, '网页版', 12, 5, 2, NOW()),

-- 第二条：带单张图片
(2, '周末去爬山了，风景真不错。放松一下心情，回来继续写代码。大家周末都在干嘛呢？', '/uploads/2023/mountains.jpg', 'iPhone 15 Pro', 56, 12, 0, DATE_SUB(NOW(), INTERVAL 2 HOUR)),

-- 第三条：技术分享（长文模拟）
(1, '【Java 基础复习】关于 HashMap 的扩容机制。\n很多同学面试都被问到这个问题。简单来说，当元素个数超过 阈值(capacity * loadFactor) 时，会进行 resize。默认容量是 16，负载因子 0.75，所以超过 12 个就会扩容。扩容时，数组长度翻倍，元素重新哈希...\n\n详细内容见我的博客长文。', NULL, 'VS Code', 108, 34, 15, DATE_SUB(NOW(), INTERVAL 5 HOUR)),

-- 第四条：多张图片（逗号分隔）
(3, '新买的机械键盘到了！红轴的手感确实轻盈，打字像飞一样。这就写个 Hello World 庆祝一下。⌨️', '/uploads/kb1.jpg,/uploads/kb2.jpg,/uploads/kb3.jpg', 'Android客户端', 23, 8, 1, DATE_SUB(NOW(), INTERVAL 1 DAY)),

-- 第五条：生活吐槽
(4, '食堂今天的红烧肉太好吃了，但是排队排了半小时...😭 下次一定要早点去！', NULL, '网页版', 5, 2, 0, DATE_SUB(NOW(), INTERVAL 1 DAY)),

-- 第六条：深夜感慨
(2, '凌晨三点，终于修好了这个 Bug。原来是数据库字段类型不匹配导致的。以后建表一定要规范啊！大家晚安。💤', NULL, 'MacBook Pro', 89, 45, 10, DATE_SUB(NOW(), INTERVAL 2 DAY)),

-- 第七条：转发抽奖（测试高互动数据）
(1, '【转发抽奖】庆祝博客上线！转发本条微博，下周五抽取 3 位幸运粉丝送出《Java 编程思想》一本！快来参与吧！🎁', '/uploads/book.jpg', '网页版', 520, 230, 666, DATE_SUB(NOW(), INTERVAL 3 DAY)),

-- 第八条：简单心情
(5, '天气好热。', NULL, 'iPhone 13', 2, 0, 0, DATE_SUB(NOW(), INTERVAL 4 DAY)),

-- 第九条：带链接（模拟）
(3, '推荐一个超级好用的前端 UI 组件库：Element Plus。文档详细，组件丰富，完美支持 Vue 3。链接：https://element-plus.org', '/uploads/element.png', 'Chrome浏览器', 45, 6, 3, DATE_SUB(NOW(), INTERVAL 5 DAY)),

-- 第十条：又是 Admin 发的
(1, '项目进度汇报：目前完成了登录注册、文章列表展示。下一步准备做文章详情和评论功能。一步一个脚印，软件工程就是这样练出来的。', NULL, '网页版', 66, 18, 4, DATE_SUB(NOW(), INTERVAL 1 WEEK));