<template>
  <Icon
    v-if="isFullScreen"
    icon="ant-design:fullscreen-exit-outlined"
    @click="toggleFullScreen"
  ></Icon>
  <Icon v-else icon="ant-design:fullscreen-outlined" @click="toggleFullScreen"></Icon>
</template>

<script setup lang="ts">
import { Icon } from '@iconify/vue'

const isFullScreen = ref(false)

// 扩展 HTMLElement 类型，添加非标准的全屏方法
interface FullscreenElement extends HTMLElement {
  mozRequestFullScreen?: () => Promise<void>
  webkitRequestFullscreen?: () => Promise<void>
  msRequestFullscreen?: () => Promise<void>
  webkitRequestFullScreen?: () => Promise<void>
}

// 扩展 Document 类型，添加非标准的全屏方法
interface FullscreenDocument extends Document {
  mozCancelFullScreen?: () => Promise<void>
  webkitExitFullscreen?: () => Promise<void>
  msExitFullscreen?: () => Promise<void>
  webkitCancelFullScreen?: () => Promise<void>
}

// 全屏
const toggleFullScreen = () => {
  if (!document.fullscreenElement) {
    isFullScreen.value = false
    const docElm = document.documentElement as FullscreenElement
    /*W3C*/
    if (docElm.requestFullscreen) {
      docElm.requestFullscreen()
    } /*FireFox */ else if (docElm.mozRequestFullScreen) {
      docElm.mozRequestFullScreen()
    } /*Chrome等 */ else if (docElm.webkitRequestFullScreen) {
      docElm.webkitRequestFullScreen()
    } /*IE11*/ else if (docElm.msRequestFullscreen) {
      docElm.msRequestFullscreen()
    }
  } else {
    isFullScreen.value = true
    document.exitFullscreen()
  }
}
// F11 和 ESC
const handleKeyDown = (event: KeyboardEvent) => {
  if (event.key === 'F11') {
    event.preventDefault() // 阻止默认的 F11 全屏行为
    toggleFullScreen()
  } else if (event.key === 'Escape') {
    const doc = document as FullscreenDocument
    if (doc.fullscreenElement) {
      isFullScreen.value = true
      if (document.exitFullscreen) {
        document.exitFullscreen()
      } else if (doc.mozCancelFullScreen) {
        doc.mozCancelFullScreen()
      } else if (doc.webkitCancelFullScreen) {
        doc.webkitCancelFullScreen()
      } else if (doc.msExitFullscreen) {
        doc.msExitFullscreen()
      }
    }
  }
}
// 处理全屏
const handleFullScreenChange = () => {
  isFullScreen.value = !!document.fullscreenElement
}

onMounted(() => {
  document.addEventListener('keydown', handleKeyDown)
  document.addEventListener('fullscreenchange', handleFullScreenChange)
})

onBeforeMount(() => {
  document.removeEventListener('keydown', handleKeyDown)
  document.removeEventListener('fullscreenchange', handleFullScreenChange)
})
</script>

<style scoped lang="scss"></style>
