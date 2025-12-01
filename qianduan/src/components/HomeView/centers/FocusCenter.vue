<script setup>
import { ref, onMounted } from 'vue'
import { getArticleListService, publishArticleService, likeArticleService } from '@/api/article.js'
import { useUserStore } from '@/stores/user'

const userStore = useUserStore()
const articleList = ref([])
const publishContent = ref('')

// 获取文章列表
const getArticleList = async () => {
    try {
        // ⭐ 关键改动：获取当前登录用户的 ID
        // 如果没登录，myUserId 就是 undefined，后端会当做游客处理
        const myUserId = userStore.userInfo ? userStore.userInfo.id : undefined

        // 把 userId 传给后端
        const data = await getArticleListService({ userId: myUserId })
        articleList.value = data
    } catch (error) {
        console.error(error)
    }
}

// 格式化内容 (保持不变)
const formatContent = (content) => {
    if (!content) return ''
    const urlRegex = /(https?:\/\/[^\s]+)/g
    return content.replace(urlRegex, (url) => {
        return `<a href="${url}" target="_blank" style="color: #409eff; text-decoration: none;">${url}</a>`
    })
}

// 处理发布 (保持不变)
const handlePublish = async () => {
    if (!userStore.userInfo) {
        alert('请先登录后再发布新鲜事！')
        return
    }
    if (!publishContent.value.trim()) {
        alert('写点什么再发吧~')
        return
    }
    try {
        const articleData = {
            userId: userStore.userInfo.id,
            content: publishContent.value,
            source: '网页版'
        }
        await publishArticleService(articleData)
        alert('发布成功！')
        publishContent.value = ''
        getArticleList()
    } catch (error) {
        console.error('发布失败', error)
        alert('发布失败，请稍后再试')
    }
}

// ⭐ 新增：处理点赞逻辑
const handleLike = async (item) => {
    // 1. 权限校验
    if (!userStore.userInfo) {
        alert('请登录后再点赞')
        return
    }

    // 2. 乐观更新（Optimistic UI）：先改界面，再发请求
    // 这样用户感觉不到延迟，体验极佳
    const originalLiked = item.isLiked // 备份一下，万一请求失败了可以恢复
    const originalCount = item.likeCount

    // 切换状态
    item.isLiked = !item.isLiked
    // 更新数字
    if (item.isLiked) {
        item.likeCount++
    } else {
        item.likeCount--
    }

    try {
        // 3. 发送请求给后端
        await likeArticleService(item.id, userStore.userInfo.id)
        // 请求成功，什么都不用做，因为界面已经变了
    } catch (error) {
        console.error('点赞失败', error)
        // 4. 如果请求失败，回滚界面状态
        item.isLiked = originalLiked
        item.likeCount = originalCount
        alert('操作失败，请检查网络')
    }
}

onMounted(() => {
    getArticleList()
})
</script>

<template>
    <main class="feed-content">
        <!-- 发布框 -->
        <div class="publish-card">
            <div class="publish-title">有什么新鲜事想告诉大家？</div>
            <textarea v-model="publishContent" placeholder="快来分享你的博客心得吧..."></textarea>
            <div class="publish-footer">
                <div class="tools">😊 📷 🔗</div>
                <button class="pub-btn" :class="{ 'active': userStore.userInfo }" @click="handlePublish">
                    {{ userStore.userInfo ? '点击发布' : '需要登录' }}
                </button>
            </div>
        </div>

        <!-- 文章列表 -->
        <div class="post-card" v-for="item in articleList" :key="item.id">
            <div class="post-header">
                <!-- 头像显示 -->
                <div class="avatar" :style="item.authorAvatar ? { backgroundImage: `url(${item.authorAvatar})` } : {}">
                </div>
                <div class="user-info">
                    <div class="username">{{ item.authorNickName || item.authorName }}</div>
                    <div class="time">{{ item.createTime }} · 来自 {{ item.source || '网页版' }}</div>
                </div>
            </div>

            <div class="post-body">
                <p v-html="formatContent(item.content)"></p>
                <!-- 图片展示预留 -->
                <!-- <div v-if="item.images" class="post-images">...</div> -->
            </div>

            <div class="post-footer">
                <div class="action-item">↪ 转发 {{ item.shareCount || '' }}</div>
                <div class="action-item">💬 评论 {{ item.commentCount || '' }}</div>

                <!-- ⭐ 修改点赞按钮 -->
                <!-- 绑定点击事件，绑定动态 class -->
                <div class="action-item" :class="{ 'liked': item.isLiked }" @click="handleLike(item)">
                    <!-- 图标根据状态变化 -->
                    <span class="icon">{{ item.isLiked ? '👍' : '👍' }}</span>
                    点赞 {{ item.likeCount || '' }}
                </div>
            </div>
        </div>

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
    font-family: inherit;
}

.publish-footer {
    display: flex;
    justify-content: space-between;
    margin-top: 10px;
    align-items: center;
}

.pub-btn {
    background: #e4e4e4;
    color: #999;
    border: none;
    padding: 5px 20px;
    border-radius: 4px;
    cursor: not-allowed;
    transition: all 0.3s;
}

.pub-btn.active {
    background: #fa7d3c;
    color: #fff;
    cursor: pointer;
}

.pub-btn.active:hover {
    background: #e0601b;
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
    background-size: cover;
    background-position: center;
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
    display: flex;
    justify-content: center;
    align-items: center;
    transition: color 0.2s;
}

.action-item:hover {
    color: #fa7d3c;
}

.icon {
    margin-right: 4px;
    font-size: 16px;
}

/* ⭐ 点赞激活状态的样式 */
.action-item.liked {
    color: #fa7d3c;
    /* 橙色 */
    font-weight: bold;
}

.empty-tip {
    text-align: center;
    color: #999;
    padding: 40px 0;
}
</style>