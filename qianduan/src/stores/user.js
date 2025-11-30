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
    const data = await loginService(loginForm)
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
