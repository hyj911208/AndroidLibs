package com.kunpeng.userchat;

import com.kunpeng.common.config.SystemConfig;

/**
 * @Author xuqm
 * @Date 2019/12/18-22:16
 * @Email xuqinmin12@sina.com
 */
public class ConfigS implements SystemConfig {
    @Override
    public String getServerHost() {
        return BuildConfig.SERVER_HOST;
    }

    @Override
    public String getAppId() {
        return BuildConfig.APP_ID;
    }

    @Override
    public String getAppName() {
        return BuildConfig.APP_NAME;
    }

    @Override
    public int getAppIcon() {
        return 0;
    }

    @Override
    public int getVersionCode() {
        return BuildConfig.VERSION_CODE;
    }

    @Override
    public String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }
}
