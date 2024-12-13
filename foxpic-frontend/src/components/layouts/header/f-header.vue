<template>
  <div class="basic-header">
    <a-row :wrap="false">
      <a-col flex="150px">
        <div class="title-bar">
          <img class="logo" src="../../../assets/logo.png" alt="logo" />
          <div class="title">初始化UI</div>
        </div>
      </a-col>
      <a-col flex="auto">
        <f-menu></f-menu>
      </a-col>
      <a-col flex="80px">
        <div class="right">
          <div v-if="userStore.loginUser.id">
            <a-dropdown placement="bottom">
              <a-avatar :src="userStore.loginUser?.userAvatar ?? notLoginUser"></a-avatar>
              <template #overlay>
                <a-menu>
                  <a-menu-item @click="doDropItemClick('profile')">
                    <span>用户中心 </span>
                  </a-menu-item>
                  <a-menu-item @click="doDropItemClick('logout')">
                    <span>退出登录 </span></a-menu-item
                  >
                </a-menu>
              </template>
            </a-dropdown>
          </div>
          <div v-else>
            <a-button type="primary" href="/uc/login">登录</a-button>
          </div>
        </div>
      </a-col>
    </a-row>
  </div>
</template>
<script lang="ts" setup>
import { useUserStore } from '@/stores/userStore.ts'
import notLoginUser from '@/assets/notLogin.png'
import router from '@/router'

const userStore = useUserStore()

const doDropItemClick = (key: string) => {
  if (key === 'logout') {
    userStore.doLogout()
    router.push('/uc/login')
  }
  if (key === 'profile') {
    router.push('/uc/profile')
  }
}
</script>
<style scoped lang="scss">
.basic-header {
  * {
    font-family:
      PingFang SC,
      sans-serif;
  }
}

.title-bar {
  display: flex;
  align-items: center;

  .title {
    color: black;
    font-size: 18px;
    margin-left: 16px;
  }

  .logo {
    height: 48px;
  }
}
</style>
