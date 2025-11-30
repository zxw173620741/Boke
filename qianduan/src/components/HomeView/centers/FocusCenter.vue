<script setup>
import { ref, onMounted } from 'vue'
import { getArticleListService } from '@/api/article.js'

// 定义响应式数据，存放文章列表
const articleList = ref([])

// 获取文章数据的函数
const getArticleList = async () => {
    try {
        // 发送请求
        const data = await getArticleListService()
        // 更新数据
        articleList.value = data
        console.log('获取到的文章数据:', data)
    } catch (error) {
        console.error('获取文章列表失败:', error)
    }
}
// 识别文本中的 URL 并转换为链接
const formatContent = (content) => {
    if (!content) return ''

    // 正则表达式匹配 http/https 开头的链接
    const urlRegex = /(https?:\/\/[^\s]+)/g

    // 将匹配到的链接替换为 <a href="..." target="_blank">...</a>
    // target="_blank" 表示在新标签页打开
    // @click.stop 阻止冒泡（在 v-html 里写不了 @click，但这里是个纯静态替换）
    return content.replace(urlRegex, (url) => {
        return `<a href="${url}" target="_blank" style="color: #409eff; text-decoration: none;">${url}</a>`
    })
}

// 组件挂载后，立刻获取数据
onMounted(() => {
    getArticleList()
})
</script>

<template>
    <main class="feed-content">
        <!-- 发布框 (暂时保持不变) -->
        <div class="publish-card">
            <div class="publish-title">有什么新鲜事想告诉大家？</div>
            <textarea placeholder="快来分享你的博客心得吧..."></textarea>
            <div class="publish-footer">
                <div class="tools">😊 📷 🔗</div>
                <button class="pub-btn">发布</button>
            </div>
        </div>

        <!-- 文章列表 (从后端获取真实数据) -->
        <!-- 这里的 item 就是后端返回的 ArticleVO 对象 -->
        <div class="post-card" v-for="item in articleList" :key="item.id">
            <div class="post-header">
                <!-- 头像: 这里暂时用默认样式，后面可以换成 :style="{ backgroundImage: `url(${item.authorAvatar})` }" -->
                <div class="avatar"></div>

                <div class="user-info">
                    <!-- 显示作者昵称，如果没有昵称就显示用户名 -->
                    <div class="username">{{ item.authorNickName || item.authorName }}</div>

                    <!-- 显示发布时间 (简单处理，后端返回的是 ISO 格式字符串) -->
                    <div class="time">{{ item.createTime }} · 来自 {{ item.source || '网页版' }}</div>
                </div>
            </div>

            <div class="post-body">
                <!-- 显示文章内容 -->

                <p v-html="formatContent(item.content)"></p>


                <!-- 如果有图片，可以在这里解析 item.images 并显示 -->
                <!-- <div v-if="item.images" class="post-images">...</div> -->
            </div>

            <div class="post-footer">
                <!-- 显示真实的互动数据 -->
                <div class="action-item">↪ 转发 {{ item.shareCount || '' }}</div>
                <div class="action-item">💬 评论 {{ item.commentCount || '' }}</div>
                <div class="action-item">👍 点赞 {{ item.likeCount || '' }}</div>
            </div>
        </div>

        <!-- 如果没有数据，显示空状态 (可选) -->
        <div v-if="articleList.length === 0" class="empty-tip">
            暂无内容，快去发布第一条博客吧！
        </div>
    </main>
</template>

<style scoped>
.feed-content {
    width: 600px;
}

.publish-card {
    background: #fff;
    padding: 20px;
    border-radius: 4px;
    margin-bottom: 15px;
}

.publish-title {
    font-size: 14px;
    color: #333;
    margin-bottom: 10px;
}

.publish-card textarea {
    width: 100%;
    height: 80px;
    border: 1px solid #ddd;
    border-radius: 4px;
    padding: 10px;
    resize: none;
    box-sizing: border-box;
}

.publish-footer {
    display: flex;
    justify-content: space-between;
    margin-top: 10px;
    align-items: center;
}

.pub-btn {
    background: #ffc09f;
    color: #fff;
    border: none;
    padding: 5px 20px;
    border-radius: 4px;
    cursor: pointer;
}

.post-card {
    background: #fff;
    padding: 20px;
    border-radius: 4px;
    margin-bottom: 15px;
}

.post-header {
    display: flex;
    margin-bottom: 10px;
}

.avatar {
    width: 50px;
    height: 50px;
    background: #ddd;
    border-radius: 50%;
    margin-right: 10px;
}

.username {
    font-weight: bold;
    color: #333;
}

.time {
    font-size: 12px;
    color: #808080;
    margin-top: 4px;
}

.post-body {
    font-size: 14px;
    line-height: 1.6;
    margin-bottom: 15px;
    white-space: pre-wrap;
    /* 关键：保留文章里的换行符 */
}

.post-footer {
    display: flex;
    border-top: 1px solid #f2f2f2;
    padding-top: 10px;
}

.action-item {
    flex: 1;
    text-align: center;
    color: #808080;
    cursor: pointer;
    font-size: 14px;
}

.action-item:hover {
    color: #fa7d3c;
}

.empty-tip {
    text-align: center;
    color: #999;
    padding: 40px 0;
}
</style>