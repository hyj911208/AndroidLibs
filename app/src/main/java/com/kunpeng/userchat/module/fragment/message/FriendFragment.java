package com.kunpeng.userchat.module.fragment.message;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kunpeng.common.base.ui.BaseFragment;
import com.kunpeng.common.view.adapter.MultiItemTypeAdapter;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.constant.RoutConstants;
import com.kunpeng.userchat.databinding.FragmentCommonBinding;
import com.kunpeng.userchat.model.UserInfo;
import com.kunpeng.userchat.module.fragment.user.adapter.NewUserAdapter;
import java.util.ArrayList;
import java.util.List;

public class FriendFragment extends BaseFragment<FragmentCommonBinding> {
    private List<UserInfo> mUserInfos;
    private NewUserAdapter mNewUserAdapter;


    @Override
    public void updateData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_common;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mUserInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.nickname = "美女";
            userInfo.address = "北京";
            userInfo.photo = "http://files.live.paywt.com/file/e95b8421-6db1-471b-ae75-5dcfa6968c81.jpg";
            mUserInfos.add(userInfo);
        }

        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mNewUserAdapter = new NewUserAdapter(mContext, mUserInfos, R.layout.item_nearby);
        getBinding().recyclerView.setAdapter(mNewUserAdapter);
        mNewUserAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ARouter.getInstance().build(RoutConstants.Activity.OTHER_PERSON_PAGEA_CTIVITY)
                       .navigation();
            }


            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }
}
