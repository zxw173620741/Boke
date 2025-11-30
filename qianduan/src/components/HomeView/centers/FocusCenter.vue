<script setup>
import { ref, onMounted } from 'vue'
import { getArticleListService, publishArticleService } from '@/api/article.js' // 引入新API
import { useUserStore } from '@/stores/user' // 1. 引入 UserStore

const userStore = useUserStore() // 2. 初始化 store
const articleList = ref([])
const publishContent = ref('') // 3. 定义响应式变量绑定输入框

// 获取文章列表 (保持不变)
const getArticleList = async () => {
    try {
        const data = await getArticleListService()
        articleList.value = data
    } catch (error) {
        console.error(error)
    }
}

// 格式化内容 (保持不变)
const formatContent = (content) => {
    if (!content) return '' // 如果内容为空，返回空字符串

    // 正则匹配链接
    const urlRegex = /(https?:\/\/[^\s]+)/g

    // 将链接转换为可点击的 a 标签
    return content.replace(urlRegex, (url) => {
        return `<a href="${url}" target="_blank" style="color: #409eff; text-decoration: none;">${url}</a>`
    })
}

// ⭐ 4. 核心逻辑：处理发布
const handlePublish = async () => {
    // 4.1 检查是否登录
    if (!userStore.userInfo) {
        alert('请先登录后再发布新鲜事！')
        return
    }

    // 4.2 检查内容是否为空
    if (!publishContent.value.trim()) {
        alert('写点什么再发吧~')
        return
    }

    try {
        // 4.3 构造参数
        const articleData = {
            userId: userStore.userInfo.id, // 从登录信息中获取 ID
            content: publishContent.value,
            source: '网页版' // 暂时写死
        }

        // 4.4 调用接口
        await publishArticleService(articleData)

        alert('发布成功！')
        publishContent.value = '' // 清空输入框
        getArticleList() // 刷新列表，看到刚才发的内容

    } catch (error) {
        console.error('发布失败', error)
        alert('发布失败，请稍后再试')
    }
}

onMounted(() => {
    getArticleList()
})
</script>

<template>
    <main class="feed-content">
        <!-- 发布框 (暂时保持不变) -->
        <div class="publish-card">
            <div class="publish-title">有什么新鲜事想告诉大家？</div>

            <textarea v-model="publishContent" placeholder="快来分享你的博客心得吧..."></textarea>

            <div class="publish-footer">
                <div class="tools">😊 📷 🔗</div>
                <button class="pub-btn" @click="handlePublish">发布</button>
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