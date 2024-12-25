<template>
  <div id="userLoginView">
    <h2 class="title">亿智狐云图库 - 用户登录</h2>
    <div class="desc">企业级智能协同云图库</div>
    <a-form :model="formState" name="basic" autocomplete="off" @finish="handleSubmit">
      <a-form-item name="userAccount" :rules="[{ required: true, message: '请输入账号' }]">
        <a-input v-model:value="formState.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <a-form-item
        name="userPassword"
        :rules="[
          { required: true, message: '请输入密码' },
          { min: 5, message: '密码长度不能小于 5 位' },
        ]"
      >
        <a-input-password v-model:value="formState.userPassword" placeholder="请输入密码" />
      </a-form-item>
      <div class="tips">
        没有账号？
        <RouterLink to="/uc/register">去注册</RouterLink>
      </div>
      <a-form-item>
        <a-button type="primary" html-type="submit" style="width: 100%">登录</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>
<script setup lang="ts">
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/userStore.ts'
import { message } from 'ant-design-vue'
import { userLogin } from '@/api/userController.ts'

/**
 * 表单信息
 */
const formState: API.UserLoginRequest = reactive({
  userAccount: '',
  userPassword: '',
})
const router = useRouter()
const userStore = useUserStore()

/**
 * 提交表单
 * @param data
 */
const handleSubmit = async (values: any) => {
  const res = await userLogin(values)
  // 登录成功，跳转到主页
  if (res.data.code === 0 && res.data.data) {
    await userStore.fetchLoginUser()
    message.success('登录成功')
    router.push({
      path: '/',
      replace: true,
    })
  } else {
    message.error('登录失败')
  }
}
</script>
<style scoped>
#userLoginView {
  max-width: 360px;
  margin: 0 auto;

  .title {
    text-align: center;
    margin-bottom: 16px;
  }

  .desc {
    text-align: center;
    color: #bbb;
    margin-bottom: 16px;
  }

  .tips {
    color: #bbb;
    text-align: right;
    font-size: 13px;
    margin-bottom: 16px;
  }
}
</style>
