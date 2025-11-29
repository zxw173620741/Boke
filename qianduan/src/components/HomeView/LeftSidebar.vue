<script setup>
// 左侧栏目前是静态的，暂时不需要 JS逻辑
</script>

<template>
    <aside class="left-sidebar">
        <div class="menu-list">
            <div class="menu-item active">🔥 热门</div>
            <div class="menu-item">📺 视频</div>
            <div class="menu-item">🎮 游戏中心</div>
            <div class="menu-item">🛍️ 逛逛</div>
        </div>
    </aside>
</template>

<style scoped>
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