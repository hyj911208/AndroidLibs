package com.kunpeng.userchat.module.fragment.mine.activity;

import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kunpeng.common.base.ui.BaseActivity;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.constant.RoutConstants;
import com.kunpeng.userchat.databinding.ActSettingBinding;

/**
 * 项目名称：AndroidLibs
 * 类描述：设置页面
 * 创建人：wsk
 * 创建时间：2019-12-26 17:39
 * 修改人：wsk
 * 修改时间：2019-12-26 17:39
 * 修改备注：
 *
 * @author wsk
 */
@Route(path = RoutConstants.Activity.SETTING_ACTIVITY)
public class SettingActivity extends BaseActivity<ActSettingBinding> implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.act_setting;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        getBinding().common.back.setOnClickListener(this);
        getBinding().tvAboutChat.setOnClickListener(this);
        getBinding().tvBlacklist.setOnClickListener(this);
        getBinding().tvClearMessage.setOnClickListener(this);
        getBinding().tvClear.setOnClickListener(this);
        getBinding().tvPrivateChat.setOnClickListener(this);
        getBinding().tvStrategyNumber.setOnClickListener(this);
        getBinding().tvVersionNumber.setOnClickListener(this);
        getBinding().tvExitAccount.setOnClickListener(this);
        getBinding().common.titleText.setText(R.string.set_up);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv_exit_account:
                ARouter.getInstance().build(RoutConstants.Activity.LOGIN_ACTIVITY).navigation();
                break;
            case R.id.tv_private_chat:
                break;
            case R.id.tv_clear:
                break;
            case R.id.tv_clear_message:
                break;
            case R.id.tv_blacklist:
                break;
            case R.id.tv_version_number:
                break;
            case R.id.tv_strategy_number:
                break;
            case R.id.tv_about_chat:
                break;
            default:
                break;
        }
    }
}
