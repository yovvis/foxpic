package com.ayfox.web.manager.auth;

import cn.dev33.satoken.stp.StpLogic;

/**
 * StpLogic 门面类，管理项目中所有的 StpLogic 账号体系
 */
public class StpKit {

    /**
     * 默认原生会话对象
     */
    public static final StpLogic DEFAULT = new StpLogic("frame");

    /**
     * space
     */
    public static final StpLogic ADMIN = new StpLogic("space");

}
