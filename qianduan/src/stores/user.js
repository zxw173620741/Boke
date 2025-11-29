import { defineStore } from 'pinia'
import { ref } from 'vue'
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
    try {
      const response = await fetch('/api/user/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(loginForm),
      })
      const res = await response.json()
      if (res.code === 1) {
        userInfo.value = res.data
        localStorage.setItem('user', JSON.stringify(res.data))
        return true
      } else {
        throw new Error(res.msg || '登录失败')
      }
    } catch (error) {
      throw error
    }
  }
  return {
    userInfo,
    setUser,
    handleLogout,
    login,
  }
})
