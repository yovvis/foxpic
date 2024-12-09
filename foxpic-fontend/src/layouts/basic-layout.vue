<template>
  <div id="basic-layout">
    <a-layout style="min-height: 100vh">
      <a-layout-header>
        <basic-header></basic-header>
      </a-layout-header>
      <a-layout-content class="content">
        <router-view></router-view>
      </a-layout-content>
      <a-layout-footer class="footer">
        <a href="https://tenyon.cn" target="_blank"></a>
        UI BY Yovvis
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script lang="ts" setup>
import BasicHeader from '@/components/layouts/header/f-header.vue'
</script>

<style scoped lang="scss">
#basic-layout {
  header {
    background-color: white;
    margin-bottom: 16px;
    color: unset;
    padding-inline: 20px;
  }

  .content {
    padding: 20px;
    margin-bottom: 20px;
    background: linear-gradient(to right, #fefefe, #fff);
  }

  .footer {
    background-color: #efefef;
    text-align: center;
    position: fixed;
    bottom: 0;
    left: 0;
    right: 0;
    padding: 16px;
  }
}
</style>
