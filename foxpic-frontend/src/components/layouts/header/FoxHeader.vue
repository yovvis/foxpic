<template>
  <div id="basic-header">
    <a-row :wrap="false">
      <a-col flex="180px">
        <router-link to="/">
          <div class="title-bar">
            <img class="logo" src="../../../assets/vue.svg" alt="logo" />
            <div class="title">亿智狐云图库</div>
          </div>
        </router-link>
      </a-col>
      <a-col flex="auto">
        <FoxMenu></FoxMenu>
      </a-col>
      <a-col flex="60px">
        <div class="right">
          <div v-if="userStore.loginUser.id">
            <a-dropdown placement="bottom">
              <a-avatar
                :src="userStore.loginUser?.userAvatar ?? notLoginUser"
                size="large"
              ></a-avatar>
              <template #overlay>
                <a-menu>
                  <a-menu-item>
                    <router-link to="/account/profile"> 用户中心 </router-link>
                  </a-menu-item>
                  <a-menu-item>
                    <router-link to="/account/my_space"> 我的空间 </router-link>
                  </a-menu-item>
                  <a-menu-item @click="doDropItemClick('logout')">
                    <span>退出登录 </span></a-menu-item
                  >
                </a-menu>
              </template>
            </a-dropdown>
          </div>
          <div v-else>
            <a-button type="primary" href="/user/login">登录</a-button>
          </div>
        </div>
      </a-col>
    </a-row>
  </div>
</template>
<script lang="ts" setup>
import { useUserStore } from '@/store/userStore.ts'
import notLoginUser from '@/assets/notLogin.png'
import router from '@/router'
import { userLogout } from '@/api/userController.ts'
import { message } from 'ant-design-vue'

const userStore = useUserStore()

const doDropItemClick = async (key: string) => {
  if (key === 'logout') {
    const res = await userLogout()
    if (res.data.code === 0) {
      userStore.setLoginUser({
        userName: '未登录',
      })
      message.success('退出登录成功')
      await router.push('/user/login')
    } else {
      message.error('退出登录失败，' + res.data.message)
    }
  }
}
</script>
<style scoped lang="scss">
#basic-header {
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
