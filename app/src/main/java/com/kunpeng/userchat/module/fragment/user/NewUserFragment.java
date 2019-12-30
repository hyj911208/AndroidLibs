package com.kunpeng.userchat.module.fragment.user;

import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.kunpeng.common.base.ui.BaseFragment;
import com.kunpeng.common.view.adapter.RecycleViewDivider;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.databinding.FragmentNuserBinding;
import com.kunpeng.userchat.model.UserInfo;
import com.kunpeng.userchat.module.fragment.user.adapter.NewUserAdapter;
import com.kunpeng.userchat.util.UiUtil;
import java.util.ArrayList;
import java.util.List;

public class NewUserFragment extends BaseFragment<FragmentNuserBinding> {
    private List<UserInfo> mUserInfos;
    private NewUserAdapter mNewUserAdapter;


    @Override
    public void updateData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_nuser;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mUserInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.nickname = "土豪";
            userInfo.address = "北京";
            userInfo.photo = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577687463" +
                    "992&di=e807b57409766d62d43f94b8a107fb62&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn%2Fw640h426%2F20180201%2F081d-fyrcsrw4665955.jpg";
            mUserInfos.add(userInfo);
        }

        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        getBinding().recyclerView
                .addItemDecoration(new RecycleViewDivider(mContext, LinearLayoutManager.VERTICAL, UiUtil.dip2px(1), ContextCompat.getColor(mContext,R.color.color_EEEEEE)));
        mNewUserAdapter = new NewUserAdapter(mContext, mUserInfos, R.layout.item_nearby);
        getBinding().recyclerView.setAdapter(mNewUserAdapter);
    }
}
