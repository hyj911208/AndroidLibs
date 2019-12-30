package com.kunpeng.common.manager;

import android.app.Activity;

import java.util.Stack;

public class ActivityStackManager {
    private Stack<Activity> activityStack;//activity栈


    public static ActivityStackManager getInstance() {
        return ActivityStackHolder.instance;
    }

    /**
     * 添加一个act到堆栈里面
     *
     * @param activity
     */
    public void pushOneActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);

    }

    /**
     * 移除一个act
     *
     * @param activity
     */
    public void popOneActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
                activity = null;
            }

        }
    }

    /**
     * 获取最后一个act
     *
     * @return
     */
    public Activity getActivity() {
        return activityStack.lastElement();
    }


    public int getSize() {
        return activityStack.size();
    }

    /**
     * 退出所有act
     */
    public void finishAllActivity() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                Activity activity = getActivity();
                if (activity == null) break;
                popOneActivity(activity);
            }
        }
    }

    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     *
     * @author xuqm
     */
    private static class ActivityStackHolder {
        public static ActivityStackManager instance = new ActivityStackManager();
    }
}
