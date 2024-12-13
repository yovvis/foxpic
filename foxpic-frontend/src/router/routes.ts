import type { RouteRecordRaw } from 'vue-router'
import HomePage from '@/views/HomeView.vue'
import UserManageView from '@/views/console/UserManagerView.vue'
import UserRegisterView from '@/views/uc/UserRegisterView.vue'
import UserLoginView from '@/views/uc/UserLoginView.vue'
// 路由菜单
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomePage,
    meta: {
      label: '主页',
      icon: 'ant-design:home-outlined',
    },
  },
  {
    path: '/uc/login',
    name: 'userLogin',
    component: UserLoginView,
    meta: {
      label: '用户登录',
      hideInMenu: true,
    },
  },
  {
    path: '/uc/register',
    name: 'userRegister',
    component: UserRegisterView,
    meta: {
      label: '用户注册',
      hideInMenu: true,
    },
  },
  {
    path: '/console/usermanage',
    name: 'userManage',
    component: UserManageView,
    meta: {
      label: '用户管理',
      icon: 'ant-design:crown-outlined',
      roles: ['admin'],
    },
  },
]

export default routes
