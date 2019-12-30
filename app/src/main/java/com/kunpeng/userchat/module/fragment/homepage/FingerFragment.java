package com.kunpeng.userchat.module.fragment.homepage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.gyf.immersionbar.ImmersionBar;
import com.kunpeng.userchat.module.fragment.fate.fragment.PersonalDynamicsFragment;
import com.kunpeng.userchat.module.fragment.fate.fragment.HostPlayerFragment;
import com.kunpeng.userchat.module.fragment.fate.fragment.TyrantListFragment;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.kunpeng.common.base.ui.BaseImmersionFragment;
import com.kunpeng.common.view.widget.ScaleTransitionPagerTitleView;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.databinding.FragmentFriendsBinding;
import com.kunpeng.userchat.module.fragment.user.adapter.UserAdapter;
import java.util.ArrayList;
import java.util.List;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

public class FingerFragment extends BaseImmersionFragment<FragmentFriendsBinding> {
    private MagicIndicator mMagicIndicator;
    private ViewPager mViewPager;
    private List<String> mTitleDataList;


    @Override
    public void updateData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_friends;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mMagicIndicator = getBinding().magicIndicator;
        mViewPager = getBinding().viewPager;
        initFragment();
        initTitle();
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
                        .setSelectedColor(ContextCompat.getColor(context, R.color.color_222222));
                scaleTransitionPagerTitleView.setTextSize(22);
                scaleTransitionPagerTitleView.setText(mTitleDataList.get(index));
                //Drawable drawable = context.getDrawable(R.drawable.icon_collect);
                //drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                //scaleTransitionPagerTitleView.setCompoundDrawables(null, drawable, null, null);
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
                indicator.setColors(ContextCompat.getColor(context, R.color.color_F60A5B));
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
        PersonalDynamicsFragment lotBarFragment = new PersonalDynamicsFragment();
        HostPlayerFragment hostPlayerFragment = new HostPlayerFragment();
        TyrantListFragment tyrantListFragment = new TyrantListFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(lotBarFragment);
        fragments.add(hostPlayerFragment);
        fragments.add(tyrantListFragment);
        UserAdapter userAdapter = new UserAdapter(getChildFragmentManager(), fragments);
        mViewPager.setAdapter(userAdapter);
        mTitleDataList = new ArrayList<>();
        mTitleDataList.add(getString(R.string.personal_dynamics));
        mTitleDataList.add(getString(R.string.album));
        mTitleDataList.add(getString(R.string.short_video));
    }


    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                    .statusBarView(getBinding().viewPager)
                    .statusBarDarkFont(true)
                    .init();
    }
}
