import request from '@/utils/request'

// 获取文章列表
export const getArticleListService = (params) => {
  return request.get('/article/list', { params })
}
// ⭐ 新增：发布文章接口
export const publishArticleService = (data) => {
  return request.post('/article/add', data)
}

// 3. ⭐BS (新增)：点赞接口
// 对应后端 Controller: @PostMapping("/like/{articleId}")
// 参数 userId 需要作为 Query Parameter 传递 (即 ?userId=xxx)
export const likeArticleService = (articleId, userId) => {
  return request.post(`/article/like/${articleId}`, null, {
    params: { userId },
  })
}
