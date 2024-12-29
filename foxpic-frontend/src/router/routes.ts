import type { RouteRecordRaw } from 'vue-router'
import HomePage from '@/views/HomeView.vue'
import UserManageView from '@/views/console/UserManagerView.vue'
import UserRegisterView from '@/views/user/UserRegisterView.vue'
import UserLoginView from '@/views/user/UserLoginView.vue'
import PictureManageView from '@/views/console/PictureManageView.vue'
import PictureDetailView from '@/views/picture/PictureDetailView.vue'
import AddPictureView from '@/views/picture/AddPictureView.vue'
import AddPictureBatchView from '@/views/picture/AddPictureBatchView.vue'
import SpaceManageView from '@/views/console/SpaceManageView.vue'
import AddSpaceView from '@/views/space/AddSpaceView.vue'
import mySpaceView from '@/views/account/MySpaceView.vue'
import spaceDetailView from '@/views/space/SpaceDetailView.vue'
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
    path: '/user',
    name: 'User',
    meta: {
      hideInMenu: true,
    },
    children: [
      {
        path: '/user/login',
        name: 'userLogin',
        component: UserLoginView,
        meta: {
          label: '用户登录',
        },
      },
      {
        path: '/user/register',
        name: 'userRegister',
        component: UserRegisterView,
        meta: {
          label: '用户注册',
        },
      },
    ],
  },
  {
    path: '/account',
    name: 'account',
    meta: {
      hideInMenu: true,
      roles: ['user'],
    },
    children: [
      {
        path: '/account/my_space',
        name: 'mySpace',
        component: mySpaceView,
        meta: {
          label: '我的空间',
          roles: ['user'],
        },
      },
    ],
  },
  {
    path: '/console',
    name: 'console',
    meta: {
      label: '后台管理',
      icon: 'ant-design:crown-outlined',
      roles: ['admin'],
    },
    children: [
      {
        path: '/console/user_manage',
        name: 'userManage',
        component: UserManageView,
        meta: {
          label: '用户管理',
          roles: ['admin'],
        },
      },
      {
        path: '/console/space_manage',
        name: 'spaceManage',
        component: SpaceManageView,
        meta: {
          label: '空间管理',
          roles: ['admin'],
        },
      },
      {
        path: '/admin/picture_manage',
        name: 'pictureManage',
        component: PictureManageView,
        meta: {
          label: '图片管理',
          roles: ['admin'],
        },
      },
    ],
  },
  {
    path: '/picture',
    name: 'Picture',
    meta: {
      label: '图片管理',
      icon: 'ant-design:picture-filled',
      roles: ['user'],
    },
    children: [
      {
        path: '/add_picture',
        name: 'addPicture',
        component: AddPictureView,
        meta: {
          label: '创建图片',
          roles: ['user'],
        },
      },
      {
        path: '/add_picture/batch',
        name: '批量创建图片',
        component: AddPictureBatchView,
        meta: {
          label: '批量创建图片',
          roles: ['admin'],
        },
      },
      {
        path: '/picture/:id',
        name: 'pictureDetail',
        component: PictureDetailView,
        props: true,
        meta: {
          hideInMenu: true,
          label: '图片详情',
          roles: ['user'],
        },
      },
    ],
  },
  {
    path: '/space',
    name: 'space',
    meta: {
      label: '空间管理',
      icon: 'ant-design:cloud-filled',
      roles: ['user'],
    },
    children: [
      {
        path: '/add_space',
        name: 'addSpace',
        component: AddSpaceView,
        meta: {
          label: '创建空间',
          roles: ['user'],
        },
      },
      {
        path: '/space/:id',
        name: 'spaceDetail',
        component: spaceDetailView,
        props: true,
        meta: {
          label: '空间详情',
          hideInMenu: true,
          roles: ['user'],
        },
      },
    ],
  },
]

export default routes
