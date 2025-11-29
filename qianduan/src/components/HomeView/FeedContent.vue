<script setup>
// 这里将来可以写获取帖子数据的逻辑
</script>

<template>
    <main class="feed-content">
        <div class="publish-card">
            <div class="publish-title">有什么新鲜事想告诉大家？</div>
            <textarea placeholder="快来分享你的博客心得吧..."></textarea>
            <div class="publish-footer">
                <div class="tools">😊 📷 🔗</div>
                <button class="pub-btn">发布</button>
            </div>
        </div>

        <div class="post-card" v-for="i in 5" :key="i">
            <div class="post-header">
                <div class="avatar"></div>
                <div class="user-info">
                    <div class="username">技术博主{{ i }}号</div>
                    <div class="time">2小时前 · 来自 网页版</div>
                </div>
            </div>
            <div class="post-body">
                <p>这是第 {{ i }} 条模拟的微博内容。今天学习了 Vue3 的路由配置，感觉非常丝滑！前端工程化开发效率真的很高。#编程 #学习打卡</p>
            </div>
            <div class="post-footer">
                <div class="action-item">↪ 转发</div>
                <div class="action-item">💬 评论</div>
                <div class="action-item">👍 点赞</div>
            </div>
        </div>
    </main>
</template>

<style scoped>
.feed-content {
    width: 600px;
}

.publish-card {
    background: #fff;
    padding: 20px;
    border-radius: 4px;
    margin-bottom: 15px;
}

.publish-title {
    font-size: 14px;
    color: #333;
    margin-bottom: 10px;
}

.publish-card textarea {
    width: 100%;
    height: 80px;
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 10px;
    resize: none;
    box-sizing: border-box;
}

.publish-footer {
    display: flex;
    justify-content: space-between;
    margin-top: 10px;
    align-items: center;
}

.pub-btn {
    background: #ffc09f;
    color: #fff;
    border: none;
    padding: 5px 20px;
    border-radius: 4px;
    cursor: pointer;
}

.post-card {
    background: #fff;
    padding: 20px;
    border-radius: 4px;
    margin-bottom: 15px;
}

.post-header {
    display: flex;
    margin-bottom: 10px;
}

.avatar {
    width: 50px;
    height: 50px;
    background: #ddd;
    border-radius: 50%;
    margin-right: 10px;
}

.username {
    font-weight: bold;
    color: #333;
}

.time {
    font-size: 12px;
    color: #808080;
    margin-top: 4px;
}

.post-body {
    font-size: 14px;
    line-height: 1.6;
    margin-bottom: 15px;
}

.post-footer {
    display: flex;
    border-top: 1px solid #f2f2f2;
    padding-top: 10px;
}

.action-item {
    flex: 1;
    text-align: center;
    color: #808080;
    cursor: pointer;
    font-size: 14px;
}

.action-item:hover {
    color: #fa7d3c;
}
</style>