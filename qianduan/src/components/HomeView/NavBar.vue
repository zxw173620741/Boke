<script setup>
import { useRouter } from 'vue-router'
import { ref, onMounted } from 'vue'
const router = useRouter()
const userInfo = ref(null)

onMounted(() => {
    const storedUser = localStorage.getItem('user')
    if (storedUser) {
        userInfo.value = storedUser
    }
})
const goLogin = () => {
    router.push('/login')
}

// 新增：前往个人资料页的方法
const goProfile = () => {
    // 假设你未来会做一个 /profile 页面
    router.push('/profile')
    console.log('去个人资料页')
}

// 新增：退出登录（为了让你能测试效果，我加了个退出功能）
const handleLogout = () => {
    localStorage.removeItem('user') // 删掉本地存储
    userInfo.value = null           // 界面立刻变回未登录状态
}
</script>

<template>
    <header class="navbar">
        <div class="nav-content">
            <div class="logo">
                <span class="logo-text">Boke</span>
            </div>
            <div class="search-box">
                <input type="text" placeholder="大家都在搜：Java学习路线" />
                <button>🔍</button>
            </div>
            <div class="nav-links">
                <a href="#" class="active">首页</a>
                <a href="#">视频</a>
                <a href="#">发现</a>
                <a href="#">游戏</a>
            </div>
            <div class="auth-buttons">
                <div v-if="userInfo" class="logged-in-box">
                    <span class="user-name">Hi, {{ userInfo || '用户' }}</span>

                    <button @click="goProfile" class="btn profile-btn">个人资料</button>
                    <button @click="handleLogout" class="btn logout-btn">退出</button>
                </div>

                <div v-else>
                    <button @click="goLogin" class="btn login-btn">登录</button>
                </div>
            </div>
        </div>
    </header>
</template>

<style scoped>
/* 原有的样式 */
.navbar {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 60px;
    background: #fff;
    border-bottom: 1px solid #dcdcdc;
    box-shadow: 0 0 2px rgba(0, 0, 0, 0.1);
    z-index: 100;
    display: flex;
    justify-content: center;
}

.nav-content {
    width: 1100px;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.logo-text {
    font-size: 24px;
    font-weight: bold;
    color: #fa7d3c;
    font-style: italic;
}

.search-box {
    display: flex;
    background: #f2f2f2;
    padding: 5px 10px;
    border-radius: 20px;
    width: 400px;
}

.search-box input {
    border: none;
    background: transparent;
    outline: none;
    flex: 1;
    font-size: 14px;
}

.nav-links a {
    margin: 0 15px;
    text-decoration: none;
    color: #333;
    font-weight: 500;
}

.nav-links a:hover,
.nav-links a.active {
    color: #fa7d3c;
}

.btn {
    padding: 5px 15px;
    border-radius: 15px;
    cursor: pointer;
    font-size: 14px;
    margin-left: 10px;
}

.login-btn {
    background: #fff;
    border: 1px solid #fa7d3c;
    color: #fa7d3c;
}

/* --- 3. 新增的样式 --- */

/* 登录后的容器 */
.logged-in-box {
    display: flex;
    align-items: center;
}

/* 用户名文字 */
.user-name {
    font-size: 14px;
    color: #666;
    margin-right: 5px;
}

/* 个人资料按钮（橙色实心） */
.profile-btn {
    background: #fa7d3c;
    color: #fff;
    border: 1px solid #fa7d3c;
}

/* 退出按钮（灰色小字） */
.logout-btn {
    background: transparent;
    color: #999;
    border: none;
    font-size: 12px;
    padding: 5px 5px;
    /* 稍微缩小一点 */
}

.logout-btn:hover {
    color: #fa7d3c;
}
</style>