<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
// 引入注册接口 (稍后在 api/user.js 中定义)
import { registerService } from '@/api/user'

const router = useRouter()

// 注册表单数据
const form = ref({
    userName: '',
    nickName: '',
    password: '',
    confirmPassword: ''
})

const handleRegister = async () => {
    // 1. 基础校验
    if (!form.value.userName || !form.value.password || !form.value.nickName) {
        alert('请将信息填写完整')
        return
    }
    if (form.value.password !== form.value.confirmPassword) {
        alert('❌ 两次输入的密码不一致')
        return
    }

    try {
        // 2. 调用注册接口 (后端需要对应实现)
        await registerService({
            userName: form.value.userName,
            password: form.value.password,
            nickName: form.value.nickName
        })

        alert('🎉 注册成功！请登录')
        router.push('/login')

    } catch (error) {
        console.error(error)
        alert('注册失败：' + (error.message || '系统繁忙'))
    }
}
</script>

<template>
    <div class="register-container">
        <div class="register-box">
            <div class="header">
                <div class="logo">Boke</div>
                <div class="title">加入我们，发现更多精彩</div>
            </div>

            <form @submit.prevent="handleRegister">
                <div class="input-group">
                    <label>账号</label>
                    <input type="text" v-model="form.userName" placeholder="设置你的登录账号 (英文/数字)" />
                </div>

                <div class="input-group">
                    <label>昵称</label>
                    <input type="text" v-model="form.nickName" placeholder="大家怎么称呼你？" />
                </div>

                <div class="input-group">
                    <label>密码</label>
                    <input type="password" v-model="form.password" placeholder="设置登录KP" />
                </div>

                <div class="input-group">
                    <label>确认密码</label>
                    <input type="password" v-model="form.confirmPassword" placeholder="再次输入KP" />
                </div>

                <button type="submit" class="reg-btn">立即注册</button>
            </form>

            <div class="footer-links">
                <span>已有账号？ <a @click="$router.push('/login')">去登录</a></span>
            </div>
        </div>
    </div>
</template>

<style scoped>
/* 复用登录页风格 */
.register-container {
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f5f5f5;
}

.register-box {
    background: white;
    padding: 40px 50px;
    border-radius: 8px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 400px;
}

.header {
    text-align: center;
    margin-bottom: 30px;
}

.logo {
    font-size: 32px;
    font-weight: bold;
    color: #fa7d3c;
    font-style: italic;
    margin-bottom: 5px;
}

.title {
    font-size: 16px;
    color: #666;
    letter-spacing: 1px;
}

.input-group {
    margin-bottom: 15px;
    /* 比登录页稍微紧凑一点，因为输入框多了 */
    text-align: left;
}

.input-group label {
    display: block;
    margin-bottom: 5px;
    color: #333;
    font-weight: bold;
    font-size: 14px;
}

.input-group input {
    width: 100%;
    padding: 10px;
    /* 稍微调小一点 padding */
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 14px;
    outline: none;
    transition: border-color 0.3s;
}

.input-group input:focus {
    border-color: #fa7d3c;
}

/* 注册按钮样式 */
.reg-btn {
    width: 100%;
    padding: 12px;
    background-color: #fa7d3c;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    font-weight: bold;
    margin-top: 15px;
    transition: all 0.5s;
    /* 缓慢变色效果 */
}

.reg-btn:hover {
    background-color: #e0601b;
    box-shadow: 0 4px 12px rgba(224, 96, 27, 0.3);
}

.footer-links {
    margin-top: 20px;
    text-align: center;
    font-size: 13px;
    color: #666;
}

.footer-links a {
    color: #fa7d3c;
    cursor: pointer;
    text-decoration: none;
    font-weight: bold;
}

.footer-links a:hover {
    text-decoration: underline;
}
</style>