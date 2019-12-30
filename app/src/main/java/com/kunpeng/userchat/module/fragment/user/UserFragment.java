package com.kunpeng.userchat.module.fragment.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.gyf.immersionbar.ImmersionBar;
import com.kunpeng.userchat.databinding.FragmentUserBinding;
import com.kunpeng.userchat.module.activity.message.SearchActivity;
import com.kunpeng.userchat.module.fragment.user.adapter.UserAdapter;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.kunpeng.common.base.ui.BaseImmersionFragment;
import com.kunpeng.common.view.widget.ScaleTransitionPagerTitleView;
import com.kunpeng.userchat.R;
import java.util.ArrayList;
import java.util.List;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

public class UserFragment extends BaseImmersionFragment<FragmentUserBinding> implements View.OnClickListener {
    private MagicIndicator mMagicIndicator;
    private ViewPager mViewPager;
    private List<Fragment> mFragmentList;
    private List<String> mTitleDataList;


    @Override
    public void updateData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_user;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        getBinding().coolTitle.sexSwitch.setOnClickListener(this);
        getBinding().coolTitle.searchButton.setOnClickListener(this);
        getBinding().coolTitle.rllOnline.setOnClickListener(this);
        getBinding().coolTitle.searchButton.setOnClickListener(this);
        mMagicIndicator = getBinding().magicIndicator;
        mViewPager = getBinding().viewPager;
        mFragmentList = new ArrayList<>();
        initFragment();
        initTitle();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sex_switch:
                break;
            case R.id.rll_online:
                break;
            case R.id.search_button:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            default:
                break;
        }
    }


    private void initTitle() {
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }


            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ScaleTransitionPagerTitleView scaleTransitionPagerTitleView = new ScaleTransitionPagerTitleView(context);
                scaleTransitionPagerTitleView
                        .setNormalColor(ContextCompat.getColor(context, R.color.color_222222));
                scaleTransitionPagerTitleView
                        .setSelectedColor(ContextCompat.getColor(context, R.color.color_EF1263));
                scaleTransitionPagerTitleView.setTextSize(22);
                scaleTransitionPagerTitleView.setText(mTitleDataList.get(index));
                scaleTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return scaleTransitionPagerTitleView;
            }


            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setRoundRadius(DensityUtil.dp2px(2));
                indicator.setLineWidth(DensityUtil.dp2px(20));
                indicator.setLineHeight(DensityUtil.dp2px(3));
                indicator.setColors(ContextCompat
                        .getColor(context, R.color.color_F60A5B));
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }


    private void initFragment() {
        NewUserFragment newUserFragment = new NewUserFragment();
        SendGiftFragment sendGiftFragment = new SendGiftFragment();
        AttentionFragment attentionFragment = new AttentionFragment();
        FollowFragment followFragment = new FollowFragment();
        mFragmentList.add(newUserFragment);
        mFragmentList.add(sendGiftFragment);
        mFragmentList.add(attentionFragment);
        mFragmentList.add(followFragment);
        UserAdapter userAdapter = new UserAdapter(getChildFragmentManager(), mFragmentList);
        mViewPager.setAdapter(userAdapter);
        mTitleDataList = new ArrayList<>();
        mTitleDataList.add(getString(R.string.new_user));
        mTitleDataList.add(getString(R.string.giving_gifts));
        mTitleDataList.add(getString(R.string.diamonds));
        mTitleDataList.add(getString(R.string.follow));
    }


    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                    .statusBarView(getBinding().view)
                    .statusBarDarkFont(true)
                    .init();
    }
}
