import { fileURLToPath, URL } from 'node:url'
import path from 'node:path'
import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import Icons from 'unplugin-icons/vite'
import { AntDesignVueResolver } from 'unplugin-vue-components/resolvers'
import IconsResolver from 'unplugin-icons/resolver'
import Inspect from 'vite-plugin-inspect'

const srcPath = fileURLToPath(new URL('./src', import.meta.url))
export default defineConfig(({ command, mode }) => {
  const env = loadEnv(mode, process.cwd(), '')
  console.log(command, '---command')
  console.log(mode, '---mode')
  return {
    server: {
      port: parseInt(env.VITE_APP_PORT),
      host: '0.0.0.0',
      open: false,
      hmr: true,
    },
    plugins: [
      vue(),
      AutoImport({
        // 自动导入 Vue 相关函数，如：ref, reactive, toRef 等
        imports: ['vue'],
        resolvers: [
          // 自动导入图标组件
          IconsResolver({
            prefix: 'Icon',
            enabledCollections: ['ant-design'],
          }),
        ],
        dts: path.resolve(srcPath, 'auto-imports.d.ts'),
      }),
      Components({
        resolvers: [
          // Auto register icon components
          // 自动注册图标组件
          IconsResolver({
            enabledCollections: ['ant-design'],
          }),
          // Auto register Ant Design Vue components
          AntDesignVueResolver({
            importStyle: false, // css in js
          }),
        ],
        dts: path.resolve(srcPath, 'components.d.ts'),
      }),
      Icons({
        autoInstall: true,
      }),
      Inspect(),
    ],
    resolve: {
      alias: {
        '@': srcPath,
      },
    },
    css: {
      preprocessorOptions: {
        scss: {
          api: 'modern-compiler',
          javascriptEnabled: true,
          additionalData: `@use "@/assets/styles/variables.scss" as *;`,
        },
      },
    },
  }
})
