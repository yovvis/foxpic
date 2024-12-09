<template>
  <template v-if="props.icon">
    <Icon :icon="props.icon"></Icon>
  </template>
</template>

<script setup lang="ts">
import { Icon, IconifyIcon } from '@iconify/vue'

interface Props {
  icon?: string | IconifyIcon
}

const props = defineProps<Props>()
</script>

<style scoped lang="scss"></style>
