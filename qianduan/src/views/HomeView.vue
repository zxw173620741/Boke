<script setup>
// 引入刚刚拆分的四个组件
import NavBar from '@/components/HomeView/NavBar.vue'
import LeftSidebar from '@/components/HomeView/LeftSidebar.vue'
import RightSidebar from '@/components/HomeView/RightSidebar.vue'
import FeedContent from '@/components/HomeView/FeedContent.vue'
</script>

<template>
    <div class="weibo-container">
        <NavBar />

        <div class="main-layout">
            <LeftSidebar />

            <FeedContent />

            <RightSidebar />
        </div>
    </div>
</template>

<style scoped>
/* 这里只保留全局容器的背景和布局样式 */
.weibo-container {
    background-color: #f2f2f2;
    /* 微博经典的浅灰背景 */
    min-height: 100vh;
    padding-top: 60px;
    /* 给固定导航栏留位置 */
}

/* 主布局容器：负责三个栏目的横向排列 */
.main-layout {
    width: 1100px;
    margin: 20px auto;
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
}
</style>