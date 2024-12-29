<template>
  <a-menu
    v-model:selectedKeys="current"
    mode="horizontal"
    :items="meunItems"
    @click="handleMenuClick"
  >
  </a-menu>
</template>
<script setup lang="ts">
// 路由跳转事件
import { h, ref } from 'vue'
import { RouteRecordRaw, useRouter } from 'vue-router'
import { MenuInfo } from 'ant-design-vue/es/menu/src/interface'
import FIcon from '@/components/common/FoxIcon.vue'
import routes from '@/router/routes.ts'
import checkAccess from '@/core/access/checkAccess.ts'
import { useUserStore } from '@/store/userStore.ts'

const router = useRouter()
const current = ref<string[]>([])
router.afterEach((to) => {
  current.value = [to.path]
})
const handleMenuClick = (info: MenuInfo) => {
  const { key } = info as { key: string }
  router.push({
    path: key,
  })
}
const userStore = useUserStore()
/**
 * 生成菜单项
 * @param routes
 */
const getMenuItems: any = (routes: Array<RouteRecordRaw>) => {
  const items = routes.map((route) => {
    // 处理菜单项基本信息
    const menuItem = {
      key: route.path,
      label: route.meta?.label || '',
      title: route.meta?.label || '',
      // 动态图标处理
      icon: h(FIcon, { icon: route.meta?.icon as string }),
      children: route.children ? getMenuItems(route.children) : null,
    }
    return menuItem
  })
  return items
}
/**
 * 过滤路由
 * @param routes
 */
const filterRoutes = (routes: Array<RouteRecordRaw>) => {
  return routes.filter((item) => {
    if (item.meta?.hideInMenu) {
      return false
    }
    // 根据权限过滤菜单
    if (!checkAccess(userStore.loginUser, JSON.stringify(item.meta?.roles))) {
      return false
    }
    // 递归过滤 children
    if (item?.children) {
      item.children = filterRoutes(item.children)
    }
    return true
  })
}
const meunItems = computed(() => {
  const visibleRoutes = filterRoutes(routes)
  return getMenuItems(visibleRoutes)
})
</script>

<style lang="scss" scoped></style>
