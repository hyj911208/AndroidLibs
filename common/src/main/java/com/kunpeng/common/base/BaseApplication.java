package com.kunpeng.common.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.kunpeng.common.CrashHandler;
import com.kunpeng.common.utils.TimeHelper;

/**
 * @Author xuqm
 * @Date 2019/12/18-21:38
 * @Email xuqinmin12@sina.com
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    // 主线程
    private final Handler handler;


    public synchronized static BaseApplication getInstance() {
        if (instance == null) {
            new BaseApplication();
        }
        return instance;
    }


    public BaseApplication() {
        instance = this;
        handler = new Handler();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(getApplicationContext());

        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsHeader(context).setAccentColor(0xffffffff)
                        .setTextSizeTitle(13)
                        .setTextSizeTime(11)
                        .setTimeFormat(TimeHelper.getDateFormat(TimeHelper.FORMAT_REFRESH));
            }
        });
    }

    /**
     * 提交主线程处理
     */
    public void runOnUiThread(final Runnable runnable) {
        handler.post(runnable);
    }

    /**
     * 提交主线程，延迟后处理
     */
    public void runOnUiThreadDelay(final Runnable runnable, long delayMillis) {
        handler.postDelayed(runnable, delayMillis);
    }
}
