import type { RouteRecordRaw } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import UserManage from '@/views/console/usermanage/Index.vue'
import UserRegister from '@/views/uc/register/Index.vue'
import UserLogin from '@/views/uc/login/Index.vue'
// 路由菜单
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      label: '主页',
      icon: 'ant-design:home-outlined',
    },
  },
  {
    path: '/uc/login',
    name: 'userLogin',
    component: UserLogin,
    meta: {
      label: '用户登录',
    },
  },
  {
    path: '/uc/register',
    name: 'userRegister',
    component: UserRegister,
    meta: {
      label: '用户注册',
    },
  },
  {
    path: '/console/usermanage',
    name: 'userManage',
    component: UserManage,
    meta: {
      label: '用户管理',
      icon: 'ant-design:crown-outlined',
      roles: ['admin'],
    },
    children: [
      {
        path: '/console/usermanage/test',
        name: 'userManageTest',
        component: HomeView,
        meta: {
          label: 'test',
          icon: 'ant-design:crown-outlined',
          roles: ['vip'],
        },
      },
    ],
  },
]

export default routes
