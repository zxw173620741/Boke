<script setup>
import { useHomeStore } from '@/stores/home'
const Homestore = useHomeStore()

// 定义菜单配置数组
const menuList = [
    { label: '🔥 热门', mode: 'FocusMode', action: Homestore.setFocus },
    { label: '📺 视频', mode: 'WatchMode', action: Homestore.setWatch },
    { label: '🎮 游戏中心', mode: 'PlayMode', action: Homestore.setPlay },
    { label: '🛍️ 逛逛', mode: 'ShopMode', action: Homestore.setShop },
]
</script>

<template>
    <aside class="left-sidebar">
        <div class="menu-list">
            <div v-for="(item, index) in menuList" :key="index" class="menu-item"
                :class="{ active: Homestore.HomeMode === item.mode }" @click="item.action">
                {{ item.label }}
            </div>
        </div>
    </aside>
</template>

<style scoped>
/* 样式同上 */
.left-sidebar {
    width: 150px;
    position: sticky;
    top: 80px;
}

.menu-item {
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 20px;
    cursor: pointer;
    padding: 5px 10px;
    border-radius: 5px;
}

.menu-item:hover,
.menu-item.active {
    background-color: rgba(250, 125, 60, 0.1);
    color: #fa7d3c;
}
</style>