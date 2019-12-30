package com.kunpeng.userchat;

import com.alibaba.android.arouter.launcher.ARouter;
import com.kunpeng.common.base.BaseApplication;
import com.kunpeng.common.config.Constants;
import com.kunpeng.common.utils.AppUtil;

/**
 * @Author xuqm
 * @Date 2019/12/18-21:39
 * @Email xuqinmin12@sina.com
 */
public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        if (!AppUtil.isMainProcess(this)) {
            return;
        }
        Constants.setSystemConfig(new ConfigS());
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
            ARouter.printStackTrace();
        }
        ARouter.init(this);
    }
}
