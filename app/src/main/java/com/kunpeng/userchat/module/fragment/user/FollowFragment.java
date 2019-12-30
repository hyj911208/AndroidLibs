package com.kunpeng.userchat.module.fragment.user;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kunpeng.common.base.ui.BaseFragment;
import com.kunpeng.common.view.adapter.RecycleViewDivider;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.databinding.FragmentCommonBinding;
import com.kunpeng.userchat.databinding.FragmentFollowBinding;
import com.kunpeng.userchat.model.UserInfo;
import com.kunpeng.userchat.module.fragment.user.adapter.NewUserAdapter;
import com.kunpeng.userchat.util.UiUtil;

import java.util.ArrayList;
import java.util.List;

public class FollowFragment extends BaseFragment<FragmentFollowBinding> {
    private List<UserInfo> mUserInfos;
    private NewUserAdapter mNewUserAdapter;


    @Override
    public void updateData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_follow;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mUserInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.nickname = "土豪";
            userInfo.address = "北京";
            userInfo.photo = "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3433032806,1065318274&fm=26&gp=0.jpg";
            mUserInfos.add(userInfo);
        }

        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        getBinding().recyclerView
                .addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL, UiUtil.dip2px(1), ContextCompat.getColor(mContext,R.color.color_EEEEEE)));
        mNewUserAdapter = new NewUserAdapter(mContext, mUserInfos, R.layout.item_nearby);
        getBinding().recyclerView.setAdapter(mNewUserAdapter);
    }
}
