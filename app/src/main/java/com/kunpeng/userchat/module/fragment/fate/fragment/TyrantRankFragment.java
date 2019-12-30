package com.kunpeng.userchat.module.fragment.fate.fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.kunpeng.common.base.ui.BaseFragment;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.databinding.FragmentRankBinding;
import com.kunpeng.userchat.model.UserInfo;
import com.kunpeng.userchat.module.fragment.fate.adapter.RankAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：AndroidLibs
 * 类描述：
 * 创建人：wsk
 * 创建时间：2019-12-25 12:26
 * 修改人：wsk
 * 修改时间：2019-12-25 12:26
 * 修改备注：
 *
 * @author wsk
 */
public class TyrantRankFragment extends BaseFragment<FragmentRankBinding> {
    private List<UserInfo> mUserInfoList;


    @Override
    public void updateData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_rank;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mUserInfoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.nickname = "美女";
            userInfo.address = "北京";
            userInfo.photo = "http://files.live.paywt.com/file/e95b8421-6db1-471b-ae75-5dcfa6968c81.jpg";
            mUserInfoList.add(userInfo);
        }
        getBinding().rankList.setData(mUserInfoList.subList(0, 3));
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        RankAdapter rankAdapter = new RankAdapter(mUserInfoList);
        getBinding().recyclerView.setAdapter(rankAdapter);
    }
}
