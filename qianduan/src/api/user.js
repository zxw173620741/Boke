// src/api/user.js
import request from '@/utils/request' // 引入刚才写的管家

// 定义登录接口方法
export const loginService = (loginData) => {
  // 这里的 request.post 就会自动带上 baseURL (/api)
  // 这里的 loginData 会自动被转换成 JSON
  return request.post('/user/login', loginData)
}

export const registerService = (registerData) => {
  return request.post('/user/register', registerData)
}
