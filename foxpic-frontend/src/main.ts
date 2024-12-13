import { createApp } from 'vue'
import '@/assets/styles/index.scss'
import App from './App.vue'
import router from '@/router'
import { createPinia } from 'pinia'
import '@/hooks/access'

const pinia = createPinia()
const app = createApp(App)
app.use(pinia)
app.use(router)
app.mount('#app')
