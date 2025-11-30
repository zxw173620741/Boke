import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useHomeStore = defineStore('Home', () => {
  //热门模式 → FocusMode（焦点模式，对应「🔥 热门」的流量聚焦属性）
  //视频模式 → WatchMode（观看模式，对应「📺 视频」的核心行为）
  //游戏模式 → PlayMode（游玩模式，对应「🎮 游戏中心」的互动属性）
  //逛逛模式 → ShopMode（逛买模式，对应「🛍️ 逛逛」的种草购物属性）
  const HomeMode = ref('FocusMode')
  const setFocus = () => {
    HomeMode.value = 'FocusMode'
  }
  const setWatch = () => {
    HomeMode.value = 'WatchMode'
  }
  const setPlay = () => {
    HomeMode.value = 'PlayMode'
  }
  const setShop = () => {
    HomeMode.value = 'ShopMode'
  }
  return {
    HomeMode,
    setFocus,
    setWatch,
    setPlay,
    setShop,
  }
})
