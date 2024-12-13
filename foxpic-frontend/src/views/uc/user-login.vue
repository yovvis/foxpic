<template>
  <div id="userLogin">
    <!--    <h2 class="title">用户登录</h2>-->
    <a-form
      style="max-width: 480px; margin: 0 auto"
      label-align="left"
      :label-col="{ span: 4 }"
      :wrapper-col="{ span: 20 }"
      :model="form"
      @finish="handleSubmit"
    >
      <a-form-item
        name="userAccount"
        label="账号"
        :rules="[{ required: true, message: '请输入账号' }]"
      >
        <a-input v-model:value="form.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <a-form-item
        name="userPassword"
        label="密码"
        :rules="[
          { required: true, message: '请输入密码' },
          { min: 5, message: '密码不少于 5 位' },
        ]"
      >
        <a-input-password v-model:value="form.userPassword" placeholder="请输入密码" />
      </a-form-item>
      <a-form-item :wrapper-col="{ offset: 4, span: 20 }">
        <a-button type="primary" html-type="submit">登录</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>
<script setup lang="ts">
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/userStore.ts'
import { message } from 'ant-design-vue'
import { userLoginUsingPost } from '@/api/userController.ts'

/**
 * 表单信息
 */
const form: API.UserLoginRequest = reactive({
  userAccount: '',
  userPassword: '',
})
const router = useRouter()
const userStore = useUserStore()

/**
 * 提交表单
 * @param data
 */
const handleSubmit = async () => {
  const res = await userLoginUsingPost(form)
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
<style scoped></style>
