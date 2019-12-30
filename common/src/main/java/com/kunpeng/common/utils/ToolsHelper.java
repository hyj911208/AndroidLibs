package com.kunpeng.common.utils;

import android.widget.Toast;

import com.kunpeng.common.manager.ActivityStackManager;


public class ToolsHelper {
    public static void showInfo(String info) {
        Toast.makeText(ActivityStackManager.getInstance().getActivity(), info, Toast.LENGTH_SHORT).show();
    }


    public static boolean isNull(String str) {
        return null == str
                || "".equals(str.replaceAll(" ", ""))
                || "null".equalsIgnoreCase(str);
    }

    public static String toString(Object obj) {
        return obj == null ? "" : (isNull(obj.toString()) ? "" : String.valueOf(obj));
    }
}
