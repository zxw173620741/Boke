import axios from 'axios'

// 1. 创建实例
const request = axios.create({
  baseURL: '/api',
  timeout: 5000,
})

// 2. 响应拦截器 (只保留最核心的：成功剥离数据，失败统一弹窗)
request.interceptors.response.use(
  (response) => {
    const res = response.data

    // 成功 (code === 1): 直接返回 data，组件里不用写 .data.data 了
    if (res.code === 1) {
      return res.data
    }

    // 失败 (密码错等): 统一弹窗，并抛出错误打断流程
    console.log(res.msg || '系统异常')
    return Promise.reject(res.msg || 'Error')
  },
  (error) => {
    // 网络错误 (404/500 等): 统一弹窗
    console.log('网络连接失败')
    console.error(error)
    return Promise.reject(error)
  },
)

export default request
