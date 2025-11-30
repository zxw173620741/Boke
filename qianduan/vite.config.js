import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'
// ✅ 1. 引入 AutoImport 插件
import AutoImport from 'unplugin-auto-import/vite'
// ✅ 2. 引入 Components 插件
import Components from 'unplugin-vue-components/vite'
// ✅ 3. 引入 Element Plus 解析器
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
    AutoImport({
      resolvers: [ElementPlusResolver()],
      imports: ['vue', 'vue-router'],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
    },
  },
  // 👇 核心配置在这里
  server: {
    host: '0.0.0.0',
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8080',
        changeOrigin: true,
      },
    },
  },
})
