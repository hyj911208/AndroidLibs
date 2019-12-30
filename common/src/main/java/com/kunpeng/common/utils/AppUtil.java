package com.kunpeng.common.utils;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import java.util.List;

/**
 * 项目名称：heixiu
 * 类描述：
 * 创建人：wsk
 * 创建时间：16/4/15 19:42
 * 修改人：wsk
 * 修改时间：16/4/15 19:42
 * 修改备注：
 */
public class AppUtil {

    public static boolean isMainProcess(Context context) {
        String curProcessName = getCurProcessName(context);
        String packageName = context.getPackageName();
        //LogUtils.i("isMainProcess PackageName :%s  --   curProcessName :%s", packageName, curProcessName);
        return packageName.equals(curProcessName);
    }


    /**
     * 获取当前的进程名
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processes;
        if (mActivityManager == null ||
                (processes = mActivityManager.getRunningAppProcesses()) == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : processes) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


    /**
     * 判断App是否处于前台
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.GET_TASKS"/>}</p>
     * <p>并且必须是系统应用该方法才有效</p>
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isAppForeground(Context context) {
        return isAppForeground(context, context.getPackageName());
    }


    /**
     * 判断App是否处于前台
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.GET_TASKS"/>}</p>
     * <p>并且必须是系统应用该方法才有效</p>
     *
     * @param context 上下文
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isAppForeground(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("deprecation") List<ActivityManager.RunningTaskInfo> tasks = am
                .getRunningTasks(1);
        return tasks != null && !tasks.isEmpty() &&
                tasks.get(0).topActivity.getPackageName().equals(packageName);
    }

    /**
     * 获取设备唯一标示
     */
    //public static String getDeviceId() {
    //    return XGPushConfig.getToken(HXApp.getInstance());
    //}


    /**
     * 判断当前是否运行在对应的service进程内
     *
     * @param serviceClass Service的Class
     */
    public static boolean isInServiceProcess(Context context, Class<? extends Service> serviceClass) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        try {
            packageInfo = packageManager
                    .getPackageInfo(context.getPackageName(), PackageManager.GET_SERVICES);
        } catch (Exception e) {

            LogHelper.d("", e);
            return false;
        }
        String mainProcess = packageInfo.applicationInfo.processName;

        ComponentName component = new ComponentName(context, serviceClass);
        ServiceInfo serviceInfo;
        try {
            serviceInfo = packageManager.getServiceInfo(component, 0);
        } catch (PackageManager.NameNotFoundException ignored) {
            // Service is disabled.
            return false;
        }

        if (serviceInfo.processName.equals(mainProcess)) {
            // Technically we are in the service process, but we're not in the service dedicated process.
            return false;
        }

        int myPid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningAppProcessInfo myProcess = null;
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = activityManager
                .getRunningAppProcesses();
        if (runningProcesses != null) {
            for (ActivityManager.RunningAppProcessInfo process : runningProcesses) {
                if (process.pid == myPid) {
                    myProcess = process;
                    break;
                }
            }
        }
        if (myProcess == null) {
            return false;
        }

        return myProcess.processName.equals(serviceInfo.processName);
    }
}
