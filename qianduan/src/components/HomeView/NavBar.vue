<script setup>
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'
// 1. 引入 HomeStore
import { useHomeStore } from '@/stores/home'

const Userstore = useUserStore()
const Homestore = useHomeStore() // 2. 实例化
const router = useRouter()

const goLogin = () => {
    router.push('/login')
}

const goProfile = () => {
    router.push('/profile')
}

const handleLogout = () => {
    Userstore.handleLogout()
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
                <a href="#" :class="{ active: Homestore.HomeMode === 'FocusMode' }"
                    @click.prevent="Homestore.setFocus">首页</a>

                <a href="#" :class="{ active: Homestore.HomeMode === 'WatchMode' }"
                    @click.prevent="Homestore.setWatch">视频</a>
                <a href="#" :class="{ active: Homestore.HomeMode === 'PlayMode' }"
                    @click.prevent="Homestore.setPlay">游戏</a>

                <a href="#" :class="{ active: Homestore.HomeMode === 'ShopMode' }"
                    @click.prevent="Homestore.setShop">逛逛</a>


            </div>

            <div class="auth-buttons">
                <div v-if="Userstore.userInfo" class="logged-in-box">
                    <span class="user-name">Hi, {{ Userstore.userInfo.nickName || Userstore.userInfo.userName }}</span>
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
/* 样式部分不需要大改，保留原有的 hover 和 active 逻辑即可 */
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
    cursor: pointer;
    transition: color 0.3s;
}

/* 这里对应 :class="{ active: ... }" */
.nav-links a:hover,
.nav-links a.active {
    color: #fa7d3c;
    font-weight: bold;
    /* 选中时加粗一点，效果更好 */
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

.logged-in-box {
    display: flex;
    align-items: center;
}

.user-name {
    font-size: 14px;
    color: #666;
    margin-right: 5px;
}

.profile-btn {
    background: #fa7d3c;
    color: #fff;
    border: 1px solid #fa7d3c;
}

.logout-btn {
    background: transparent;
    color: #999;
    border: none;
    font-size: 12px;
    padding: 5px 5px;
}

.logout-btn:hover {
    color: #fa7d3c;
}
</style>