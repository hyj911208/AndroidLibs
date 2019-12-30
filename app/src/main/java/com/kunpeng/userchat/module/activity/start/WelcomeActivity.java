package com.kunpeng.userchat.module.activity.start;

import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kunpeng.common.base.ui.BaseActivity;
import com.kunpeng.userchat.MyApplication;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.constant.RoutConstants;
import com.kunpeng.userchat.databinding.ActivityWelcomeBinding;

/**
 * @Author xuqm
 * @Date 2019/12/23-14:03
 * @Email xuqinmin12@sina.com
 */
@Route(path = RoutConstants.Activity.WELCOME_ACTIVITY)
public class WelcomeActivity extends BaseActivity<ActivityWelcomeBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }


    @Override
    public void initData(Bundle savedInstanceState) {

        MyApplication.getInstance().runOnUiThreadDelay(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance().build(RoutConstants.Activity.LOGIN_ACTIVITY).navigation();
                finish();
            }
        }, 500);
    }
}
