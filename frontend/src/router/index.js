import { createRouter, createWebHistory } from 'vue-router'
import Dashboard from '@/pages/Dashboard.vue'
import StockDetail from '@/pages/StockDetail.vue'

const routes = [
  { path: '/', name: 'Dashboard', component: Dashboard },
  { path: '/stocks/:ticker', name: 'StockDetail', component: StockDetail }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
