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
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { MenuInfo } from 'ant-design-vue/es/menu/src/interface'
import routes from '@/router/routes.ts'
import { filterRoutes, getMenuItems } from '@/core/generateMenu.ts'
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

const meunItems = computed(() => {
  const visibleRoutes = filterRoutes(routes, userStore.loginUser)
  return getMenuItems(visibleRoutes)
})
</script>

<style lang="scss" scoped></style>
