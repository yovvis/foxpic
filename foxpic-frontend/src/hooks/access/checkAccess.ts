import ACCESS_ENUM from '@/hooks/access/accessEnum.ts'

/**
 * 检查权限（判断当前登录用户是否具有某个权限）
 * @param loginUser 当前登录用户
 * @param needAccess 需要有的权限
 * @return boolean 有无权限
 */
const checkAccess = (loginUser: any, needAccess = ACCESS_ENUM.NOT_LOGIN) => {
  // 获取当前登录用户具有的权限（如果没有 loginUser，则表示未登录）
  const loginUserAccess = loginUser?.userRole ?? ACCESS_ENUM.NOT_LOGIN
  if (needAccess === ACCESS_ENUM.NOT_LOGIN) {
    return true
  }
  // 管理员全部放行
  if (loginUserAccess === ACCESS_ENUM.ADMIN) {
    return true
  }
  // 如果用户要登录才能访问
  if (needAccess.includes(ACCESS_ENUM.USER)) {
    // 如果用户没登录，那么表示无权限
    if (loginUserAccess === ACCESS_ENUM.NOT_LOGIN) {
      return false
    }
  }
  // 如果管理员才能访问
  if (needAccess.includes(ACCESS_ENUM.ADMIN)) {
    // 如果不是管理员，表示无权限
    if (loginUserAccess !== ACCESS_ENUM.ADMIN) {
      return false
    }
  }

  // 如果VIP才能访问
  if (needAccess.includes(ACCESS_ENUM.VIP)) {
    // 如果不是管理员，表示无权限
    if (loginUserAccess !== ACCESS_ENUM.VIP) {
      return false
    }
  }
  // 如果SVIP才能访问
  if (needAccess.includes(ACCESS_ENUM.VIP)) {
    // 如果不是管理员，表示无权限
    if (loginUserAccess !== ACCESS_ENUM.VIP) {
      return false
    }
  }
  return true
}

export default checkAccess
