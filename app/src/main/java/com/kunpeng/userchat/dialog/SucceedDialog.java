package com.kunpeng.userchat.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.kunpeng.common.view.widget.roundtextview.RoundRelativeLayout;
import com.kunpeng.userchat.R;

/**
 * 项目名称：LotChat
 * 类描述：注册成功，修改成功的弹窗
 * 创建人：wsk
 * 创建时间：2019-12-18 14:39
 * 修改人：wsk
 * 修改时间：2019-12-18 14:39
 * 修改备注：
 *
 * @author wsk
 */
public class SucceedDialog extends DialogFragment implements View.OnClickListener {
    private ImageView mImageView;
    private TextView mTitle;
    private TextView mCancelBtn;
    private RoundRelativeLayout mDialog;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AlertDialogStyle);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_success, container, false);
        mDialog=view.findViewById(R.id.dialog);
        mImageView=view.findViewById(R.id.image_view);
        mTitle=view.findViewById(R.id.title);
        mCancelBtn=view.findViewById(R.id.cancel_btn);
        mCancelBtn.setOnClickListener(this);
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        if (type == 1) {
            mTitle.setText(R.string.registration_success);
        } else {
            mTitle.setText(R.string.successfully_modified);
        }
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_btn:
                mClickChargeCallBack.callBack();
                break;
            default:
                break;
        }
    }


    public interface ClickChargeCallBack {
        void callBack();
    }

    private ClickChargeCallBack mClickChargeCallBack;


    public void setNeedChargeCallBack(ClickChargeCallBack clickChargeCallBack) {
        this.mClickChargeCallBack = clickChargeCallBack;
    }
}
