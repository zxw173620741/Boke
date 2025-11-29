<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const user = ref({
    userName: '',
    password: ''
})
const handleLogin = async () => {
    // 1. 简单的非空校验
    if (!user.value.userName || !user.value.password) {
        alert('请输入账号和密码')
        return
    }

    try {
        const response = await fetch('/api/user/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(user.value)
        })

        const res = await response.json()

        // 3. 处理结果
        if (res.code === 1) {
            // alert('🎉 登录成功！')
            console.log(res.data)
            localStorage.user = res.data.userName;
            router.push('/')
        } else {
            alert('❌ ' + (res.msg || '登录失败'))
        }
    } catch (error) {
        console.error(error)
        alert('网络连接失败，请检查后端服务')
    }
}
</script>

<template>
    <div class="login-container">
        <div class="login-box">
            <h2>后台管理系统</h2>
            <form @submit.prevent="handleLogin">
                <div class="input-group">
                    <label>账号</label>
                    <input type="text" v-model="user.userName" placeholder="请输入用户名" />
                </div>
                <div class="input-group">
                    <label>密码</label>
                    <input type="password" v-model="user.password" placeholder="请输入密码" />
                </div>
                <button type="submit" class="login-btn">登录</button>
            </form>
        </div>
    </div>
</template>

<style scoped>
.login-container {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background: linear-gradient(135deg, #74ebd5 0%, #9face6 100%);
}

.login-box {
    background: white;
    padding: 40px;
    border-radius: 12px;
    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 360px;
    text-align: center;
}

h2 {
    margin-bottom: 30px;
    color: #333;
}

.input-group {
    margin-bottom: 20px;
    text-align: left;
}

.input-group label {
    display: block;
    margin-bottom: 8px;
    color: #666;
    font-size: 14px;
}

.input-group input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 6px;
    box-sizing: border-box;
}

.login-btn {
    width: 100%;
    padding: 12px;
    background-color: #667eea;
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-size: 16px;
    transition: background 0.3s;
}

.login-btn:hover {
    background-color: #5a67d8;
}
</style>