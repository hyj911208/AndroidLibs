package com.kunpeng.userchat.module.fragment.message;

import android.os.Bundle;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kunpeng.common.base.ui.BaseActivity;
import com.kunpeng.common.utils.ToolsHelper;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.constant.RoutConstants;
import com.kunpeng.userchat.databinding.ActComplaintAdviceBinding;

/**
 * 项目名称：AndroidLibs
 * 类描述：投诉建议页面
 * 创建人：wsk
 * 创建时间：2019-12-25 16:20
 * 修改人：wsk
 * 修改时间：2019-12-25 16:20
 * 修改备注：
 *
 * @author wsk
 */
@Route(path = RoutConstants.Activity.COMPLAINT_ADVICE_ACTIVITY)
public class ComplaintAdviceActivity extends BaseActivity<ActComplaintAdviceBinding> implements View.OnClickListener {
    @Override
    public int getLayoutId() {
        return R.layout.act_complaint_advice;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        getBinding().common.titleText.setText(R.string.suggestions);
    }


    @Override
    public void setListener() {
        super.setListener();
        getBinding().common.back.setOnClickListener(this);
        getBinding().rlReasonComplaint.setOnClickListener(this);
        getBinding().tvSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.rl_reason_complaint:
                ToolsHelper.showInfo("选择原因");
                break;
            case R.id.tv_submit:
                ToolsHelper.showInfo("提交成功");
                break;
            default:
                break;
        }
    }
}
