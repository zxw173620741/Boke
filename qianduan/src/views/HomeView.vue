<script setup>
import { useHomeStore } from '@/stores/home'
import { computed } from 'vue'
import NavBar from '@/components/HomeView/NavBar.vue'
import LeftSidebar from '@/components/HomeView/LeftSidebar.vue'
import RightSidebar from '@/components/HomeView/RightSidebar.vue'



import FocusCenter from '@/components/HomeView/centers/FocusCenter.vue'
import WatchCenter from '@/components/HomeView/centers/WatchCenter.vue'
import PlayCenter from '@/components/HomeView/centers/PlayCenter.vue'
import ShopCenter from '@/components/HomeView/centers/ShopCenter.vue'
const Homestore = useHomeStore()
const centerMap = {
    'FocusMode': FocusCenter,
    'WatchMode': WatchCenter,
    'PlayMode': PlayCenter,
    'ShopMode': ShopCenter
}

const currentCenter = computed(() => {
    const mode = Homestore.HomeMode
    // 返回对应的组件，如果找不到（为了安全），默认返回 FocusCenter
    return centerMap[mode] || FocusCenter
})


</script>

<template>
    <div class="weibo-container">
        <NavBar />

        <div class="main-layout">
            <LeftSidebar />

            <div class="center-content">
                <KeepAlive>
                    <component :is="currentCenter" />
                </KeepAlive>
            </div>

            <RightSidebar />
        </div>
    </div>
</template>

<style scoped>
.weibo-container {
    background-color: #f2f2f2;
    min-height: 100vh;
    padding-top: 60px;
}

.main-layout {
    width: 1100px;
    margin: 20px auto;
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
}

/* 可以在这里给中间区域定宽，或者在各个子组件里定宽 */
.center-content {
    width: 600px;
}
</style>