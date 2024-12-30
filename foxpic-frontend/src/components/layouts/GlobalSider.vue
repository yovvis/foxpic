<template>
  <div id="globalSider">
    <a-layout-sider v-if="userStore.loginUser.id" width="200" breakpoint="lg" collapsed-width="0">
      <a-menu
        v-model:selectedKeys="current"
        mode="inline"
        :items="menuItems"
        @click="doMenuClick"
      />
    </a-layout-sider>
  </div>
</template>
<script lang="ts" setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/userStore.ts'
import { filterRoutes, getMenuItems } from '@/core/generateMenu.ts'
import routes from '@/router/routes.ts'

const userStore = useUserStore()

// 菜单项
const menuItems = computed(() => {
  const visibleRoutes = filterRoutes(routes, userStore.loginUser)
  return getMenuItems(visibleRoutes)
})

const router = useRouter()
// 当前要高亮的菜单项
const current = ref<string[]>([])
// 监听路由变化，更新高亮菜单项
router.afterEach((to, from, next) => {
  current.value = [to.path]
})

// 路由跳转事件
const doMenuClick = ({ key }) => {
  router.push({
    path: key,
  })
}
</script>

<style lang="scss" scoped>
#globalSider {
  .ant-layout-sider {
    background: none;
  }
}
</style>
