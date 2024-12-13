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
import FIcon from '@/components/common/f-icon.vue'
import routes from '@/router/routes.ts'
import checkAccess from '@/hooks/access/checkAccess.ts'
import { useUserStore } from '@/stores/userStore.ts'

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
// 动态生成菜单项
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
const meunItems = computed(() => {
  const visibleRoutes = routes.filter((item) => {
    if (item.meta?.hideInMenu) {
      return false
    }
    // 根据权限过滤菜单
    if (!checkAccess(userStore.loginUser, JSON.stringify(item.meta?.roles))) {
      return false
    }
    return true
  })
  return getMenuItems(visibleRoutes)
})
</script>

<style scoped lang="scss"></style>
