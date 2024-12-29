import { RouteRecordRaw } from 'vue-router'
import checkAccess from '@/core/access/checkAccess.ts'
import FoxIcon from '@/components/common/FoxIcon.vue'

/**
 * 生成菜单项
 * @param routes
 */
export const getMenuItems: any = (routes: Array<RouteRecordRaw>) => {
  const items = routes.map((route) => {
    // 处理菜单项基本信息
    const menuItem = {
      key: route.path,
      label: route.meta?.label || '',
      title: route.meta?.label || '',
      // 动态图标处理
      icon: h(FoxIcon, { icon: route.meta?.icon as string }),
      children: route.children ? getMenuItems(route.children) : null,
    }
    return menuItem
  })
  return items
}
/**
 * 过滤路由
 * @param routes
 */
export const filterRoutes = (routes: Array<RouteRecordRaw>, loginUser: API.LoginUserVO) => {
  return routes.filter((item) => {
    if (item.meta?.hideInMenu) {
      return false
    }
    // 根据权限过滤菜单
    if (!checkAccess(loginUser, JSON.stringify(item.meta?.roles))) {
      return false
    }
    // 递归过滤 children
    if (item?.children) {
      item.children = filterRoutes(item.children, loginUser)
    }
    return true
  })
}
