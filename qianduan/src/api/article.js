import request from '@/utils/request'

// 获取文章列表
export const getArticleListService = () => {
  return request.get('/article/list')
}
