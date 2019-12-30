package com.kunpeng.userchat.module.activity.start;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kunpeng.common.base.ui.BaseActivity;
import com.kunpeng.common.view.widget.roundtextview.CustomTimeDeadTextView;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.constant.RoutConstants;
import com.kunpeng.userchat.databinding.ActivityNewUserRegisterBinding;
import com.kunpeng.userchat.dialog.SucceedDialog;
import com.kunpeng.userchat.util.AppUtils;

/**
 * 项目名称：AndroidLibs
 * 类描述：忘记密码
 * 创建人：wsk
 * 创建时间：2019-12-23 16:18
 * 修改人：wsk
 * 修改时间：2019-12-23 16:18
 * 修改备注：
 *
 * @author wsk
 */
@Route(path = RoutConstants.Activity.FORGET_PASSWORD_ACTIVITY)
public class ForgetPassWordActivity extends BaseActivity<ActivityNewUserRegisterBinding> implements View.OnClickListener {
    private SucceedDialog mSucceedDialog;


    @Override
    public int getLayoutId() {
        return R.layout.activity_new_user_register;
    }


    public static void startAct(Context context) {
        Intent intent = new Intent(context, ForgetPassWordActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        getBinding().tvLogin.setOnClickListener(this);
        getBinding().tvGetVerify.setOnClickListener(this);
        getBinding().common.back.setOnClickListener(this);
        TextView textView = getBinding().common.titleText;
        textView.setText("忘记密码");

        getBinding().etPassword.setFilters(new InputFilter[] { AppUtils.getChineseInputFilter() });
        getBinding().etPassword
                .setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        getBinding().tvGetVerify.setCallback(new CustomTimeDeadTextView.Callback() {
            @Override
            public void done() {
                findViewById(R.id.tv_get_verify).setEnabled(true);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                if (!AppUtils.isMobileNO(getBinding().etPassword.getEditableText().toString())) {
                    Toast.makeText(getApplicationContext(), "手机号错误", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 密码检查
                String password = getBinding().etPassword.getText().toString().trim();
                if (password.length() < 6 || password.length() > 20) {
                    Toast.makeText(getApplicationContext(), R.string.register_password_tip, Toast.LENGTH_SHORT)
                         .show();
                    return;
                }
                String account = getBinding().etNumber.getText().toString();
                String code = getBinding().etVerifyCode.getText().toString();
                mSucceedDialog = new SucceedDialog();
                Bundle bundle = new Bundle();
                bundle.putInt("type", 2);
                mSucceedDialog.setArguments(bundle);

                mSucceedDialog.show(getSupportFragmentManager(), "succeedDialog");
                mSucceedDialog.setNeedChargeCallBack(new SucceedDialog.ClickChargeCallBack() {
                    @Override
                    public void callBack() {
                        mSucceedDialog.dismissAllowingStateLoss();
                        Intent intent = new Intent(ForgetPassWordActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case R.id.tv_get_verify:

                break;
            case R.id.back:
                finish();
                break;
            default:
                break;
        }
    }
}
