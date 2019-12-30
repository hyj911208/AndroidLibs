package com.kunpeng.userchat.module.activity.start;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kunpeng.common.base.ui.BaseActivity;
import com.kunpeng.common.utils.ToolsHelper;
import com.kunpeng.common.view.widget.roundtextview.CustomTimeDeadTextView;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.constant.RoutConstants;
import com.kunpeng.userchat.databinding.ActivityNewRegisterBinding;
import com.kunpeng.userchat.dialog.SucceedDialog;
import com.kunpeng.userchat.util.AppUtils;

/**
 * 项目名称：AndroidLibs
 * 类描述：
 * 创建人：wsk
 * 创建时间：2019-12-23 17:01
 * 修改人：wsk
 * 修改时间：2019-12-23 17:01
 * 修改备注：
 *
 * @author wsk
 */
@Route(path = RoutConstants.Activity.NEW_USER_REGISTER_ACTIVITY)
public class NewUserRegisterActivity extends BaseActivity<ActivityNewRegisterBinding> implements View.OnClickListener {
    private SucceedDialog mSucceedDialog;


    @Override
    public int getLayoutId() {
        return R.layout.activity_new_register;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        getBinding().etPassword.setFilters(new InputFilter[] { AppUtils.getChineseInputFilter() });
        getBinding().etPassword
                .setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        getBinding().tvGetVerify.setCallback(new CustomTimeDeadTextView.Callback() {
            @Override
            public void done() {
                findViewById(R.id.tv_get_verify).setEnabled(true);
            }
        });
        getBinding().tvSign.setOnClickListener(this);
        getBinding().tvGetVerify.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sign:
                if (!checkRegisterContentValid()) {
                    return;
                }
                if (null == mSucceedDialog) {
                    mSucceedDialog = new SucceedDialog();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                mSucceedDialog.setArguments(bundle);
                mSucceedDialog.setNeedChargeCallBack(new SucceedDialog.ClickChargeCallBack() {
                    @Override
                    public void callBack() {
                        mSucceedDialog.dismissAllowingStateLoss();
                        ARouter.getInstance().build(RoutConstants.Activity.MAIN_ACTIVITY)
                               .navigation();
                        finish();
                    }
                });
                mSucceedDialog.show(getSupportFragmentManager(), "succeedDialog");
                break;
            default:
                break;
        }
    }


    private boolean checkRegisterContentValid() {
        // 帐号检查
        String account = getBinding().etNumber.getText().toString().trim();
        if (!AppUtils.isMobileNO(account)) {
            ToolsHelper.showInfo("手机号错误");
            return false;
        }

        // 密码检查
        String password = getBinding().etPassword.getText().toString().trim();
        if (password.length() < 6 || password.length() > 20) {
            ToolsHelper.showInfo(getString(R.string.register_password_tip));
            return false;
        }
        if (TextUtils.isEmpty(getBinding().llSex.getsContent())) {
            return false;
        }
        if (TextUtils.isEmpty(getBinding().llBirth.getsContent())) {
            return false;
        }
        if (TextUtils.isEmpty(getBinding().llCity.getsContent())) {
            return false;
        }
        return true;
    }
}
