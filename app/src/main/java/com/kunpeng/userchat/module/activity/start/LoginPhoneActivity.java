package com.kunpeng.userchat.module.activity.start;

import android.os.Bundle;

import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kunpeng.common.base.ui.BaseActivity;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.constant.RoutConstants;
import com.kunpeng.userchat.databinding.ActivityPhoneBinding;

/**
 * @Author xuqm
 * @Date 2019/12/23-15:05
 * @Email xuqinmin12@sina.com
 */
@Route(path = RoutConstants.Activity.LOGIN_PHONE_ACTIVITY)
public class LoginPhoneActivity extends BaseActivity<ActivityPhoneBinding> implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.activity_phone;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        getBinding().tvLogin.setOnClickListener(this);
        getBinding().tvNewReg.setOnClickListener(this);
        getBinding().tvForgetPassword.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                ARouter.getInstance().build(RoutConstants.Activity.MAIN_ACTIVITY).navigation();
                break;
            case R.id.tv_new_reg:
                ARouter.getInstance().build(RoutConstants.Activity.NEW_USER_REGISTER_ACTIVITY)
                       .navigation();
                break;
            case R.id.tv_forget_password:
                ARouter.getInstance().build(RoutConstants.Activity.FORGET_PASSWORD_ACTIVITY)
                       .navigation();
                ForgetPassWordActivity.startAct(getApplicationContext());
                break;
            default:
                break;
        }
    }
}
