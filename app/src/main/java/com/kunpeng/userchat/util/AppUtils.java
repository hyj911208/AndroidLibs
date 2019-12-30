package com.kunpeng.userchat.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.core.content.ContextCompat;
import com.kunpeng.userchat.MyApplication;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * Created by huangminzheng on 2018/1/20 下午4:31.
 * Email:ahtchmz@gmail.com
 */

public class AppUtils {

    //private static AbortableFuture<LoginInfo> loginRequest;

    public static String getDateStr(long date) {
        return DateUtil.getInstance().getFormatDate(date);
    }

    private static long[] mHits = new long[2];

    public static boolean clickMore() {
        //每点击一次 实现左移一格数据
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        //给数组的最后赋当前时钟值
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        //当0出的值大于当前时间-500时  证明在500秒内点击了2次
        if (mHits[0] > SystemClock.uptimeMillis() - 1000) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否为数字
     */

    public static boolean isNumber(CharSequence input) {
        if (TextUtils.isEmpty(input)) {
            return false;
        }
        Pattern p = Pattern.compile("^[-\\+\\d]+$");
        return p.matcher(input).matches();
    }

    /**
     * 是否是手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        if (TextUtils.isEmpty(mobiles))
            return false;
        mobiles = mobiles.replaceAll(" ", "");
        Pattern p = Pattern.compile("^1(3|4|5|7|8|6|9)\\d{9}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 获取屏幕宽度
     */
    public static int getScreenW(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenH(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static void setStatusBarColor(Activity activity, int color) {
        //设置 paddingTop
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView().findViewById(android.R.id.content);
        rootView.setPadding(0, getStatusBarHeight(activity), 0, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0 以上直接设置状态栏颜色
            activity.getWindow().setStatusBarColor(color);
        } else {
            //根布局添加占位状态栏
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            View statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
            statusBarView.setBackgroundColor(color);
            decorView.addView(statusBarView, lp);
        }
    }

    /**
     * 利用反射获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = activity.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取外网ip
     *
     * @return
     */
    public static String getNetIp() {
        String ip = "";
        InputStream inStream = null;
        try {
            URL infoUrl = new URL("http://1212.ip138.com/ic.asp");
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "gb2312"));
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    //builder.append(line).append("\n");
                }
                inStream.close();
                int start = builder.indexOf("[");
                int end = builder.indexOf("]");
                ip = builder.substring(start + 1, end);
                return ip;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }


    public static String splitHeadUrl(String url) {
        if (TextUtils.isEmpty(url))
            return "";
        int httpIndx = url.lastIndexOf("http");
        if (httpIndx > 0) {
            url = url.substring(httpIndx, url.length());
        }
        return url;
    }

    /**
     * 获取图片
     */
    public static Drawable getTextDrawable(int id) {
        Drawable drawable = ContextCompat.getDrawable(MyApplication.getInstance(), id);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }


    public static InputFilter getChineseInputFilter() {
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (AppUtils.isChinese(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        return filter;
    }

    /**
     * 是否是中文
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }


//    //DEMO中使用 username 作为 NIM 的account ，md5(password) 作为 token
//    //开发者需要根据自己的实际情况配置自身用户系统和 NIM 用户系统的关系
//    private String tokenFromPassword(String password) {
//        String appKey = readAppKey(this);
//        boolean isDemo = "45c6af3c98409b18a84451215d0bdd6e".equals(appKey)
//                || "fe416640c8e8a72734219e1847ad2547".equals(appKey);
//
//        return isDemo ? MD5.getStringMD5(password) : password;
//    }
//
//    private static String readAppKey(Context context) {
//        try {
//            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
//            if (appInfo != null) {
//                return appInfo.metaData.getString("com.netease.nim.appKey");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    /**
//     * 初始化消息提醒
//     */
//    public static void initNotificationConfig() {
//        // 初始化消息提醒
//        NIMClient.toggleNotification(UserPreferences.getNotificationToggle());
//        // 加载状态栏配置
//        StatusBarNotificationConfig statusBarNotificationConfig = UserPreferences.getStatusConfig();
//        if (statusBarNotificationConfig == null) {
//            statusBarNotificationConfig = DemoCache.getNotificationConfig();
//            statusBarNotificationConfig.hideContent = true;
//            statusBarNotificationConfig.showBadge = true;
//            UserPreferences.setStatusConfig(statusBarNotificationConfig);
//        }
//        statusBarNotificationConfig.notificationFolded = false;
//        // 更新配置
//        NIMClient.updateStatusBarNotificationConfig(statusBarNotificationConfig);
//    }
//
//
//    // 登录云信账号
//    public static void loginYxAccounts(final Context context, final LoginInfo loginInfo) {
//
//        SpManager.reMoveImLimitRespBean(NimApplication.getInstance());//清除上一次的limit
//        if (loginInfo == null) {
//            return;
//        }
//
//        loginRequest = NimUIKit.login(loginInfo, new RequestCallback<LoginInfo>() {
//            @Override
//            public void onSuccess(LoginInfo param) {
//                onLoginDone();
//                DemoCache.setAccount(loginInfo.getAccount());
//                Preferences.saveUserAccount(loginInfo.getAccount());
//                Preferences.saveUserToken(loginInfo.getToken());
//                // 初始化消息提醒配置
//                initNotificationConfig();
//                // 进入主界面
//                MainActivity.start(context, null);
//                ((Activity) context).finish();
//            }
//
//            @Override
//            public void onFailed(int code) {
//                onLoginDone();
//                if (code == 302 || code == 404) {
//                    ToastUtil.show(context, R.string.login_failed);
//                } else if (code == 422) {
//                    ToastUtil.show(context, "账号被禁用");
//                } else {
//                    ToastUtil.show(context, "登录失败: " + code);
//                }
//                LoginActivity.start(context);
////                MainActivity.start(context, null);
//                ((Activity) context).finish();
//            }
//
//            @Override
//            public void onException(Throwable exception) {
//                onLoginDone();
//                ToastUtil.show(context, R.string.login_exception);
//            }
//        });
//    }

    private static void onLoginDone() {
        //loginRequest = null;
    }


//    public static void fixInputMethodManagerLeak(Context destContext) {
//        if (destContext == null) {
//            return;
//        }
//
//        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (imm == null) {
//            return;
//        }
//
//        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
//        Field f = null;
//        Object obj_get = null;
//        for (int i = 0; i < arr.length; i++) {
//            String param = arr[i];
//            try {
//                f = imm.getClass().getDeclaredField(param);
//                if (f.isAccessible() == false) {
//                    f.setAccessible(true);
//                } // author: sodino mail:sodino@qq.com
//                obj_get = f.get(imm);
//                if (obj_get != null && obj_get instanceof View) {
//                    View v_get = (View) obj_get;
//                    if (v_get.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
//                        f.set(imm, null); // 置空，破坏掉path to gc节点
//                    } else {
//                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
////                        if (QLog.isColorLevel()) {
////                            QLog.d(ReflecterHelper.class.getSimpleName(), QLog.CLR, "fixInputMethodManagerLeak break, context is not suitable, get_context=" + v_get.getContext()+" dest_context=" + destContext);
////                        }
//                        break;
//                    }
//                }
//            } catch (Throwable t) {
//                t.printStackTrace();
//            }
//        }
//    }

    /**
     * @return 是否是无效订单
     */
    public static boolean isValiadeOrder() {
        //long lastTime = (long) SPUtils.getInstance().get(NimApplication.getInstance(), SPManager.getUserId() + "time", 0L);
        //if (lastTime != 0) {
        //    if (System.currentTimeMillis() - lastTime < 2 * 60 * 1000) {
        //        return false;
        //    } else {
        //        SPUtils.getInstance().remove(NimApplication.getInstance(), SPManager.getUserId() + "time");
        //        return true;
        //    }
        //}
        return true;
    }

    public static int getLevelBg(int level, boolean isAnchor) {
        int color = Color.parseColor("#ffffff");
        if (isAnchor) {
            if (level < 6) {
                color = Color.parseColor("#ffd06d");
            } else if (level < 19) {
                color = Color.parseColor("#ffb00b");
            } else if (level < 28) {
                color = Color.parseColor("#fb8a10");
            } else if (level < 37) {
                color = Color.parseColor("#fc610b");
            } else {
                color = Color.parseColor("#ff3e0b");
            }
        } else {
            if (level < 10) {
                color = Color.parseColor("#a6d8f4");
            } else if (level < 20) {
                color = Color.parseColor("#88cbf1");
            } else if (level < 29) {
                color = Color.parseColor("#3186e2");
            } else if (level < 34) {
                color = Color.parseColor("#345ee4");
            } else {
                color = Color.parseColor("#001ff6");
            }
        }
        return color;
    }

    /**
     * 判断是否包含SIM卡
     *
     * @return 状态
     */
    public static boolean hasSimCard(Context context) {
        TelephonyManager telMgr = (TelephonyManager)
                context.getSystemService(TELEPHONY_SERVICE);
        int simState = telMgr.getSimState();
        boolean result = true;
        switch (simState) {
            case TelephonyManager.SIM_STATE_ABSENT:
                result = false; // 没有SIM卡
                break;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                result = false;
                break;
        }
        return result;
    }

    /**
     * 验证输入的身份证号是否合法
     */
    private static String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|"
            + "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

    public static boolean isLegalId(String idCard) {
        if (idCard.toUpperCase().matches(regularExpression)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAppAlive(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(context.getPackageName()) && info.baseActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static List<ActivityInfo> getAllActivities(Context context, String packageName) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    packageName, PackageManager.GET_ACTIVITIES);

            return new ArrayList<>(Arrays.asList(pi.activities));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static float sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (spValue * fontScale + 0.5f);
    }

    //public static void translucentStatusBar(Activity activity) {
    //    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    //        setTranslucentStatus(activity, true);
    //    }
    //    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    //        Window window = activity.getWindow();
    //        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
    //                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    //        window.getDecorView().setSystemUiVisibility(
    //                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    //                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    //                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    //        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    //        window.setStatusBarColor(Color.TRANSPARENT);
    //        window.setNavigationBarColor(Color.TRANSPARENT);
    //
    //    } else {
    //        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
    //        tintManager.setStatusBarTintEnabled(true);
    //        tintManager.setStatusBarAlpha(0);
    //    }
    //}

    private static void setTranslucentStatus(Activity activity, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);

    }

    public static CharSequence subStrWithEllipsis(CharSequence s, int index) {
        if (s.length() <= index) {
            return s;
        }
        s = s.subSequence(0, index);
        return s + "...";
    }


    /**
     * 是否能提现
     *
     * @return 公会主播不能提现
     */
    //public static boolean canWidthDraw() {
    //    return !(SPManager.getLoginRespBean().getUnionist() == 1);
    //}

    public static boolean checkObsIsNotNull(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return false;
            }
        }
        return true;
    }

    public static void hideSoftKeyboard(Context context, EditText input) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(input.getWindowToken(), 0);
    }

    public static void showSoftKeyboard(Context context, EditText input) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager == null) {
            return;
        }
        inputMethodManager.showSoftInput(input, 0);
    }


    //public static String getAndroidID(Context context) {
    //    String id = Settings.Secure.getString(
    //            context.getContentResolver(),
    //            Settings.Secure.ANDROID_ID
    //    );
    //    return id == null ? "" : MD5.getStringMD5(id);
    //}


    /**
     * 获取MAC地址
     *
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {
        String mac = "02:00:00:00:00:00";
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mac = getMacDefault(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mac = getMacAddress();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mac = getMacFromHardware();
        }
        return mac;
    }

    /**
     * Android  6.0 之前（不包括6.0）
     * 必须的权限  <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
     *
     * @param context
     * @return
     */
    private static String getMacDefault(Context context) {
        String mac = "02:00:00:00:00:00";
        if (context == null) {
            return mac;
        }

        WifiManager wifi = (WifiManager) context.getApplicationContext()
                                                .getSystemService(Context.WIFI_SERVICE);
        if (wifi == null) {
            return mac;
        }
        WifiInfo info = null;
        try {
            info = wifi.getConnectionInfo();
        } catch (Exception e) {
        }
        if (info == null) {
            return null;
        }
        mac = info.getMacAddress();
        if (!TextUtils.isEmpty(mac)) {
            mac = mac.toUpperCase(Locale.ENGLISH);
        }
        return mac;
    }

    /**
     * Android 6.0（包括） - Android 7.0（不包括）
     *
     * @return
     */
    private static String getMacAddress() {
        String WifiAddress = "02:00:00:00:00:00";
        try {
            WifiAddress = new BufferedReader(new FileReader(new File("/sys/class/net/wlan0/address"))).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return WifiAddress;
    }

    /**
     * 遍历循环所有的网络接口，找到接口是 wlan0
     * 必须的权限 <uses-permission android:name="android.permission.INTERNET" />
     *
     * @return
     */
    private static String getMacFromHardware() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "02:00:00:00:00:00";
    }

    public static String getIMEI(Context context) {
        String imei = "";
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            Method method = tm.getClass().getMethod("getImei");
            imei = (String) method.invoke(tm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei;
    }


    /**
     * 根据wifi信息获取本地mac
     *
     * @param context
     * @return
     */
    //public static String getLocalMacAddressFromWifiInfo(Context context) {
    //    WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    //    WifiInfo winfo = wifi.getConnectionInfo();
    //    String mac = winfo.getMacAddress();
    //    return mac;
    //}
    //
    //
    //private static String getDeviceUUid(Context context) {
    //    String androidId = getAndroidID(context);
    //    UUID deviceUuid = new UUID(androidId.hashCode(), ((long) androidId.hashCode() << 32));
    //    return deviceUuid.toString();
    //}
    //
    //private static String getAppUUid(Context context) {
    //    String uuid = (String) SPUtils.getInstance().get(context, "unicodeId", "");
    //    if (TextUtils.isEmpty(uuid)) {
    //        uuid = UUID.randomUUID().toString();
    //        SPUtils.getInstance().saveObject(context, "unicodeId", uuid);
    //    }
    //    return uuid;
    //}
    //
    //
    //public static String getUUID(Context context) {
    //    String uuid = getDeviceUUid(context);
    //    if (TextUtils.isEmpty(uuid)) {
    //        uuid = getAppUUid(context);
    //    }
    //    return uuid;
    //}
    //
    //public static int getMetaBgData(Context context) {
    //    ApplicationInfo info = null;
    //    try {
    //        info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
    //        int welcomePath = info.metaData.getInt("welcome_bg");
    //        if (1 == welcomePath) {
    //            return R.mipmap.welcome_ychat_bg;
    //        } else {
    //            return R.mipmap.welcome_live_bg;
    //        }
    //
    //    } catch (PackageManager.NameNotFoundException e) {
    //        e.printStackTrace();
    //        return R.mipmap.welcome_ychat_bg;
    //    }
    //}

    public static String getMetaAppModel(Context context) {
        ApplicationInfo info = null;
        try {
            info = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            String appmodel = info.metaData.getString("app_model");
            return appmodel;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "ychat";
        }
    }

}
