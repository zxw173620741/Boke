import { createRouter, createWebHistory } from 'vue-router'
import UserLoginView from '../views/UserLoginView.vue'
import HomeView from '@/views/HomeView.vue'
import UserRegisterView from '@/views/UserRegisterView.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: UserLoginView,
    },
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path: '/register',
      name: 'register',
      component: UserRegisterView,
    },
  ],
})

export default router
