package com.kunpeng.userchat.module.fragment.user;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kunpeng.common.base.ui.BaseFragment;
import com.kunpeng.common.view.adapter.RecycleViewDivider;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.databinding.FragmentCommonBinding;
import com.kunpeng.userchat.databinding.FragmentSendGiftBinding;
import com.kunpeng.userchat.model.UserInfo;
import com.kunpeng.userchat.module.fragment.user.adapter.NewUserAdapter;
import com.kunpeng.userchat.util.UiUtil;

import java.util.ArrayList;
import java.util.List;

public class SendGiftFragment extends BaseFragment<FragmentSendGiftBinding> {
    private List<UserInfo> mUserInfos;
    private NewUserAdapter mNewUserAdapter;


    @Override
    public void updateData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_send_gift;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mUserInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.nickname = "美女";
            userInfo.address = "北京";
            userInfo.photo = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577687604548&" +
                    "di=1520b769c19572adef154a5e8edd364b&imgtype=0&src=http%3A%2F%2Fhimg2.huanqiu.com%2Fattachment2010%2F2016%2F0330%2F20160330011831204.png";
            mUserInfos.add(userInfo);
        }

        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        getBinding().recyclerView
                .addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL, UiUtil.dip2px(1), ContextCompat.getColor(mContext,R.color.color_EEEEEE)));
        mNewUserAdapter = new NewUserAdapter(mContext, mUserInfos, R.layout.item_nearby);
        getBinding().recyclerView.setAdapter(mNewUserAdapter);
    }
}
