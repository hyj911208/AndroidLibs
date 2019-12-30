package com.kunpeng.userchat.module.fragment.mine.activity;

import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kunpeng.common.base.ui.BaseActivity;
import com.kunpeng.common.utils.ToolsHelper;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.constant.RoutConstants;
import com.kunpeng.userchat.databinding.ActFeedbackBinding;

/**
 * 项目名称：AndroidLibs
 * 类描述：反馈意见
 * 创建人：wsk
 * 创建时间：2019-12-26 18:49
 * 修改人：wsk
 * 修改时间：2019-12-26 18:49
 * 修改备注：
 *
 * @author wsk
 */
@Route(path = RoutConstants.Activity.FEED_BACK_ACTIVITY)
public class FeedbackActivity extends BaseActivity<ActFeedbackBinding> implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.act_feedback;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        getBinding().common.titleText.setText(R.string.feedback);
    }


    @Override
    public void setListener() {
        super.setListener();
        getBinding().tvSubmit.setOnClickListener(this);
        getBinding().common.back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                ToolsHelper.showInfo("提交成功");
                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
