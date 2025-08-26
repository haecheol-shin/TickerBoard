import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'

// 환경변수 가져오기
const API_BASE = process.env.VITE_API_BASE_URL || 'http://localhost:8080'

export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src')
    }
  },
  server: {
    host: '0.0.0.0',   // 컨테이너 외부 접근 허용
    port: 5173,
    historyApiFallback: true,
    proxy: {
      // 모든 /api 요청을 Spring 컨테이너로 포워딩
      '/api': {
        target: API_BASE,
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})
