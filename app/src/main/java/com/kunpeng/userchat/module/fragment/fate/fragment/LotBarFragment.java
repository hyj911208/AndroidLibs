package com.kunpeng.userchat.module.fragment.fate.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kunpeng.userchat.model.fate.LuckInfo;
import com.kunpeng.userchat.module.fragment.fate.adapter.LotBarAdapter;
import com.kunpeng.userchat.view.LotBarView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.kunpeng.common.base.ui.BaseFragment;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.databinding.FragmentCommonBinding;
import com.kunpeng.userchat.model.GiftInfo;
import com.kunpeng.userchat.model.UserInfo;
import java.util.ArrayList;
import java.util.List;

public class LotBarFragment extends BaseFragment<FragmentCommonBinding> {
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;
    private List<LuckInfo> mLuckInfoList;
    private List<UserInfo> mUserInfoList;
    private LotBarView mLotBarView;


    @Override
    public void updateData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_common;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mRecyclerView = getBinding().recyclerView;
        mSmartRefreshLayout = getBinding().smartRefreshLayout;
        getBinding().lotbarBannerTv.setSelected(true);
        mLuckInfoList = new ArrayList<>();
        mUserInfoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.nickname = "超级大土豪";
            userInfo.address = "北京";
            userInfo.photo = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1" +
                    "577696526942&di=7a1d8c00ecfdb807bd1671912ade2f5c&imgtype=0&src=http%3A%2F%2Fi-1.okemu.com%2F2018%2F12%2F14%2Fbe10f4a3-8591-4101-b86e-fbfec7647fd3.gif";
            mUserInfoList.add(userInfo);
        }
        for (int i = 0; i < 10; i++) {
            LuckInfo luckInfo = new LuckInfo();
            UserInfo userInfo = new UserInfo();
            userInfo.nickname = "土豪";
            userInfo.address = "北京";
            userInfo.photo = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577696526941&di=bdffd31d181fe0fce" +
                    "29ec90b7f92c28b&imgtype=0&src=http%3A%2F%2Fimg3.qianzhan.com%2Fnews%2F201404%2F15%2F20140415-c547b2aa266c74da.jpg";
            luckInfo.sendUser = userInfo;
            UserInfo userInfoBack = new UserInfo();
            userInfoBack.nickname = "美女";
            userInfoBack.address = "北京";
            userInfoBack.photo = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577" +
                    "696601492&di=507a303219c9abd67e18418364224bee&imgtype=0&src=http%3A%2F%2Fimg.lookmw.cn%2F170626%2F1498128138.png";
            luckInfo.receiveUser = userInfoBack;
            luckInfo.callContent = "并对他说：你的美颜相机真棒";
            GiftInfo giftInfo = new GiftInfo();
            giftInfo.giftUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577696654306&di=33fc299898406" +
                    "6a025010cd7632a5aeb&imgtype=0&src=http%3A%2F%2Fpic.51yuansu.com%2Fpic3%2Fcover%2F02%2F71%2F65%2F5a188ace6e30c_610.jpg";
            giftInfo.price = 1000;
            giftInfo.giftName = "一生一世";
            luckInfo.giftInfo = giftInfo;
            luckInfo.userList = mUserInfoList;
            mLuckInfoList.add(luckInfo);
        }
        List<LuckInfo> luckInfoList = null;
        LuckInfo luckInfo = null;
        if (mLuckInfoList.size() > 1) {
            luckInfo = mLuckInfoList.get(0);
            luckInfoList = mLuckInfoList.subList(1, mLuckInfoList.size());
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LotBarAdapter lotBarAdapter = new LotBarAdapter(luckInfoList, getContext());
        lotBarAdapter.addHeaderView(getHeadView());
        mLotBarView.setData(luckInfo);
        mRecyclerView.setAdapter(lotBarAdapter);
    }


    private View getHeadView() {
        mLotBarView = new LotBarView(getContext());
        return mLotBarView;
    }
}
