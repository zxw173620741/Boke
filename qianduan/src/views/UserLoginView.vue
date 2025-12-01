<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'

const router = useRouter()
const userStore = useUserStore()

// 表单数据
const user = ref({
    userName: '',
    password: ''
})

// 登录按钮逻辑
const handleLogin = async () => {
    if (!user.value.userName || !user.value.password) {
        alert('请输入账号和密码')
        return
    }

    try {
        await userStore.login(user.value)
        // 登录成功后跳转回首页
        router.push('/')
    } catch (error) {
        console.error(error)
        alert('登录失败，请检查用户名或KP')
    }
}
</script>

<template>
    <div class="login-container">
        <div class="login-box">
            <div class="login-header">
                <div class="logo">Boke</div>
                <div class="subtitle">随时随SK，发现新鲜事</div>
            </div>

            <form @submit.prevent="handleLogin">
                <div class="input-group">
                    <label>账号</label>
                    <input type="text" v-model="user.userName" placeholder="请输入用户名 (如: admin)" />
                </div>
                <div class="input-group">
                    <label>KP</label>
                    <input type="password" v-model="user.password" placeholder="请输入KP" />
                </div>

                <button type="submit" class="login-btn">立即登录</button>
            </form>

            <div class="login-footer">
                <span>还没有账号？ <a href="javascript:void(0)" @click="router.push('/register')">立即注册</a></span>
            </div>
        </div>
    </div>
</template>

<style scoped>
.login-container {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    /* 改为柔和的灰白背景，更像社交APP网页版 */
    background-color: #f5f5f5;
    background-image: radial-gradient(#fa7d3c 0.5px, transparent 0.5px), radial-gradient(#fa7d3c 0.5px, #f5f5f5 0.5px);
    background-size: 20px 20px;
    background-position: 0 0, 10px 10px;
    /* 上面几行是为了加一点点淡淡的橙色波点纹理，如果觉得花哨可以直接删掉用纯色 */
}

.login-box {
    background: white;
    padding: 40px 50px;
    border-radius: 8px;
    /* 阴影调淡一点 */
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
    width: 100%;
    max-width: 400px;
}

.login-header {
    text-align: center;
    margin-bottom: 30px;
}

.logo {
    font-size: 36px;
    font-weight: bold;
    color: #fa7d3c;
    /* 品牌橙色 */
    font-style: italic;
    margin-bottom: 5px;
}

.subtitle {
    font-size: 14px;
    color: #999;
    letter-spacing: 1px;
}

.input-group {
    margin-bottom: 20px;
    text-align: left;
}

.input-group label {
    display: block;
    margin-bottom: 8px;
    color: #333;
    font-weight: bold;
    font-size: 14px;
}

.input-group input {
    width: 100%;
    padding: 12px;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 14px;
    transition: all 0.3s;
    outline: none;
}

/* 关键：聚焦时变成品牌色 */
.input-group input:focus {
    border-color: #fa7d3c;
    box-shadow: 0 0 0 2px rgba(250, 125, 60, 0.1);
}

.login-btn {
    width: 100%;
    padding: 12px;
    background-color: #fa7d3c;
    /* 品牌橙色 */
    color: white;
    border: none;
    border-radius: 25px;
    /* 圆角大一点，更显年轻 */
    cursor: pointer;
    font-size: 16px;
    font-weight: bold;
    transition: background 0.3s;
    margin-top: 10px;
}

.login-btn:hover {
    background-color: #e0601b;
    /* 深一点的橙色 */
}

.login-footer {
    margin-top: 20px;
    text-align: center;
    font-size: 12px;
    color: #666;
}

.login-footer a {
    color: #fa7d3c;
    text-decoration: none;
}

.login-footer a:hover {
    text-decoration: underline;
}
</style>