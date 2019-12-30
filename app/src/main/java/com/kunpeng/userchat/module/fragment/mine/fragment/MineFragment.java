package com.kunpeng.userchat.module.fragment.mine.fragment;

import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.immersionbar.ImmersionBar;
import com.kunpeng.common.base.ui.BaseImmersionFragment;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.constant.RoutConstants;
import com.kunpeng.userchat.databinding.FragmentMineBinding;

public class MineFragment extends BaseImmersionFragment<FragmentMineBinding> implements View.OnClickListener {
    @Override
    public void updateData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }


    @Override
    public void initData(Bundle savedInstanceState) {

    }


    @Override
    public void setListener() {
        super.setListener();
        getBinding().ivSetting.setOnClickListener(this);
        getBinding().feedback.setOnClickListener(this);
    }


    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).keyboardEnable(false).statusBarDarkFont(true).init();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                ARouter.getInstance().build(RoutConstants.Activity.SETTING_ACTIVITY).navigation();
                break;
            case R.id.feedback:
                ARouter.getInstance().build(RoutConstants.Activity.FEED_BACK_ACTIVITY).navigation();
                break;
            default:
                break;
        }
    }
}
