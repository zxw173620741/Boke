<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()
const goLogin = () => {
    router.push('/login')
}
</script>

<template>
    <aside class="right-sidebar">
        <div class="login-panel">
            <div class="login-tips">登录后可以保存您的浏览喜好、评论、收藏，并与好友分享乐趣。</div>
            <button @click="goLogin" class="big-login-btn">立即登录</button>
            <div class="helper-links">
                <span>忘记密码</span> | <span>注册新账号</span>
            </div>
        </div>

        <div class="hot-list">
            <div class="hot-title">微博热搜</div>
            <div class="hot-item">1. Spring Boot 3.0 发布</div>
            <div class="hot-item">2. 考研倒计时</div>
            <div class="hot-item">3. 瑞幸咖啡联名</div>
            <div class="hot-item">4. 为什么 Java 是最好的语言</div>
        </div>
    </aside>
</template>

<style scoped>
.right-sidebar {
    width: 280px;
}

.login-panel {
    background: #fff;
    padding: 20px;
    border-radius: 4px;
    margin-bottom: 15px;
    text-align: center;
}

.login-tips {
    font-size: 12px;
    color: #666;
    margin-bottom: 15px;
    text-align: left;
}

.big-login-btn {
    width: 100%;
    height: 35px;
    background: #fa7d3c;
    color: #fff;
    border: none;
    border-radius: 2px;
    font-size: 14px;
    cursor: pointer;
    margin-bottom: 10px;
}

.helper-links {
    font-size: 12px;
    color: #808080;
}

.hot-list {
    background: #fff;
    padding: 20px;
    border-radius: 4px;
}

.hot-title {
    font-weight: bold;
    margin-bottom: 15px;
}

.hot-item {
    font-size: 14px;
    margin-bottom: 10px;
    cursor: pointer;
}

.hot-item:hover {
    color: #fa7d3c;
}
</style>