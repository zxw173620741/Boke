import { defineStore } from 'pinia'
import { ref } from 'vue'
// 👇 1. 引入封装好的接口方法
import { loginService } from '@/api/user'

export const useUserStore = defineStore('User', () => {
  const userInfo = ref(JSON.parse(localStorage.getItem('user')) || null)

  const setUser = (data) => {
    userInfo.value = data
  }

  const handleLogout = () => {
    localStorage.removeItem('user')
    userInfo.value = null
  }

  const login = async (loginForm) => {
    // 👇 2. 使用新封装的逻辑
    // 不需要 try-catch 了吗？需要的，但只需要捕获“业务逻辑之外”的错误
    // 因为 request.js 里的响应拦截器已经帮我们处理了 code !== 1 的弹窗报错
    // 所以这里我们可以只关注“成功”的逻辑

    // 发送请求
    const data = await loginService(loginForm)

    // 注意：因为响应拦截器里返回的是 res.data，所以这里的 data 直接就是用户信息
    userInfo.value = data
    localStorage.setItem('user', JSON.stringify(data))

    return true
  }

  return {
    userInfo,
    setUser,
    handleLogout,
    login,
  }
})
