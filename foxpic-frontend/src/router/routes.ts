import type { RouteRecordRaw } from 'vue-router'
import HomePage from '@/views/HomeView.vue'
import UserManageView from '@/views/console/UserManagerView.vue'
import UserRegisterView from '@/views/uc/UserRegisterView.vue'
import UserLoginView from '@/views/uc/UserLoginView.vue'
import PictureManageView from '@/views/console/PictureManageView.vue'
import PictureDetailView from '@/views/components/PictureDetailView.vue'
import AddPictureView from '@/views/components/AddPictureView.vue'
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
    path: '/console/user_manage',
    name: 'userManage',
    component: UserManageView,
    meta: {
      label: '用户管理',
      icon: 'ant-design:crown-outlined',
      roles: ['admin'],
    },
  },
  {
    path: '/admin/picture_manage',
    name: 'pictureManage',
    component: PictureManageView,
    meta: {
      label: '图片管理',
      icon: 'ant-design:picture-outlined',
      roles: ['admin'],
    },
  },
  {
    path: '/add_picture',
    name: 'addPicture',
    component: AddPictureView,
    meta: {
      label: '创建图片',
      roles: ['admin'],
      hideInMenu: true,
    },
  },
  {
    path: '/picture/:id',
    name: 'pictureDetail',
    component: PictureDetailView,
    props: true,
    meta: {
      label: '图片详情',
      hideInMenu: true,
    },
  },
]

export default routes
