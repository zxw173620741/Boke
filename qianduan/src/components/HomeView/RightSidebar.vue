<script setup>
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'

const Userstore = useUserStore()
const router = useRouter()

// 跳转登录
const goLogin = () => {
    router.push('/login')
}

const handleLogout = () => {
    Userstore.handleLogout()
}
</script>

<template>
    <aside class="right-sidebar">

        <div v-if="!Userstore.userInfo" class="login-panel">
            <div class="login-tips">登录后可以保存您的浏览喜好、评论、收藏，并与好友分享乐趣。</div>
            <button @click="goLogin" class="big-login-btn">立即登录</button>
            <div class="helper-links">
                <span>忘记密码</span> | <span>注册新账号</span>
            </div>
        </div>

        <div v-else class="login-panel user-panel">
            <div class="user-header">
                <div class="avatar"></div>
                <div class="username">{{ Userstore.userInfo.nickName || Userstore.userInfo.userName }}</div>
            </div>

            <div class="user-stats">
                <div class="stat-item">
                    <div class="count">128</div>
                    <div class="label">关注</div>
                </div>
                <div class="stat-item">
                    <div class="count">3200</div>
                    <div class="label">粉丝</div>
                </div>
                <div class="stat-item">
                    <div class="count">56</div>
                    <div class="label">微博</div>
                </div>
            </div>

            <button @click="handleLogout" class="big-logout-btn">退出登录</button>
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
/* 保持原有样式... */
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

/* === 新增：已登录状态的样式 === */
.user-header {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 15px;
}

.avatar {
    width: 60px;
    height: 60px;
    background-color: #ddd;
    /* 暂时用灰色占位 */
    border-radius: 50%;
    margin-bottom: 10px;
    border: 2px solid #fa7d3c;
}

.username {
    font-weight: bold;
    font-size: 16px;
    color: #333;
}

.user-stats {
    display: flex;
    justify-content: space-around;
    margin-bottom: 20px;
    padding: 10px 0;
    border-top: 1px solid #f2f2f2;
    border-bottom: 1px solid #f2f2f2;
}

.stat-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    cursor: pointer;
}

.stat-item:hover .count {
    color: #fa7d3c;
}

.stat-item .count {
    font-weight: bold;
    font-size: 16px;
    color: #333;
}

.stat-item .label {
    font-size: 12px;
    color: #808080;
    margin-top: 2px;
}

.big-logout-btn {
    width: 100%;
    height: 30px;
    background: #fff;
    color: #808080;
    border: 1px solid #dcdcdc;
    border-radius: 2px;
    font-size: 12px;
    cursor: pointer;
}

.big-logout-btn:hover {
    background: #f2f2f2;
    color: #333;
}
</style>