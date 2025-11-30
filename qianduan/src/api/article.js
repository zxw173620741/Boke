import request from '@/utils/request'

// 获取文章列表
export const getArticleListService = () => {
  return request.get('/article/list')
}
// ⭐ 新增：发布文章接口
export const publishArticleService = (data) => {
  return request.post('/article/add', data)
}
