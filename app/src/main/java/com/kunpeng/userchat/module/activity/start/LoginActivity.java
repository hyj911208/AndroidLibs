package com.kunpeng.userchat.module.activity.start;

import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kunpeng.common.base.ui.BaseActivity;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.constant.RoutConstants;
import com.kunpeng.userchat.databinding.ActivityLoginBinding;

/**
 * @Author xuqm
 * @Date 2019/12/23-14:15
 * @Email xuqinmin12@sina.com
 */
@Route(path = RoutConstants.Activity.LOGIN_ACTIVITY)
public class LoginActivity extends BaseActivity<ActivityLoginBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        getBinding().panningView.startPanning();
    }


    @Override
    public void setListener() {
        super.setListener();
        getBinding().loginOnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RoutConstants.Activity.MAIN_ACTIVITY).navigation();
                finish();
            }
        });
        getBinding().loginPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RoutConstants.Activity.LOGIN_PHONE_ACTIVITY)
                       .navigation();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getBinding().panningView.stopPanning();
    }
}
