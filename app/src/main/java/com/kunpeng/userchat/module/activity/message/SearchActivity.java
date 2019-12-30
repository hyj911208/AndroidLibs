package com.kunpeng.userchat.module.activity.message;


import android.os.Bundle;
import android.view.View;

import com.kunpeng.common.base.ui.BaseActivity;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.databinding.ActivitySearchBinding;

public class SearchActivity extends BaseActivity<ActivitySearchBinding> implements View.OnClickListener {


    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
     getBinding().ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
