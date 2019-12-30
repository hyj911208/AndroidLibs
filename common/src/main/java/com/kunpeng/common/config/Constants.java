package com.kunpeng.common.config;

/**
 * Created by xuqm on 2018/6/22.
 */

public class Constants {
    public static final String application_id = "APPLICATION_ID";
    public static final String auto_token = "AUTO_TOKEN";
    public static final int size = 50;
    // 核心常量
    private static SystemConfig systemConfig;

    /**
     * 获取 核心常量
     *
     * @return
     */
    public static SystemConfig getSystemConfig() {
        return systemConfig;
    }

    /**
     * 设置 核心常量
     *
     * @param systemConfig
     */
    public static void setSystemConfig(SystemConfig systemConfig) {
        Constants.systemConfig = systemConfig;
    }
}
