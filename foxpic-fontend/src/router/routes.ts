import type { RouteRecordRaw } from 'vue-router'
import HomePage from '@/views/home-page.vue'
import UserManage from '@/views/console/user-manage.vue'
import UserRegister from '@/views/uc/user-register.vue'
import UserLogin from '@/views/uc/user-login.vue'
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
        component: HomePage,
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
