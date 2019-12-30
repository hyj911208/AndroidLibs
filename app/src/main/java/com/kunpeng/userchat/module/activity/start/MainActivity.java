package com.kunpeng.userchat.module.activity.start;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.kunpeng.common.base.ui.BaseActivity;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.constant.RoutConstants;
import com.kunpeng.userchat.databinding.ActivityMainBinding;
import com.kunpeng.userchat.module.fragment.message.ConversationFragment;
import com.kunpeng.userchat.module.fragment.fate.fragment.FateFragment;
import com.kunpeng.userchat.module.fragment.homepage.FingerFragment;
import com.kunpeng.userchat.module.fragment.user.adapter.UserAdapter;
import com.kunpeng.userchat.module.fragment.user.UserFragment;
import com.kunpeng.userchat.module.fragment.user.TabEntity;
import com.kunpeng.userchat.module.fragment.mine.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = RoutConstants.Activity.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private ViewPager mViewPager;
    private int[] mIconUnselectIds = { R.drawable.ic_nav_home, R.drawable.ic_nav_action,
            R.drawable.ic_nav_friend, R.drawable.ic_nav_msg, R.drawable.ic_nav_me };
    private int[] mIconSelectIds = { R.drawable.ic_nav_home_checked,
            R.drawable.ic_nav_action_checked, R.drawable.ic_nav_friend_checked,
            R.drawable.ic_nav_msg_checked, R.drawable.ic_nav_me_checked };
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<Fragment> mFragmentList;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        String[] mTitles = { getString(R.string.home), getString(R.string.find),
                getString(R.string.inter_finger), getString(R.string.message),
                getString(R.string.mine) };
        mViewPager = getBinding().viewPager;
        mFragmentList = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        ConversationFragment conversationFragment = new ConversationFragment();
        UserFragment userFragment = new UserFragment();
        FingerFragment fingerFragment = new FingerFragment();
        FateFragment fateFragment = new FateFragment();
        MineFragment mineFragment = new MineFragment();
        mFragmentList.add(conversationFragment);
        mFragmentList.add(userFragment);
        mFragmentList.add(fingerFragment);
        mFragmentList.add(fateFragment);
        mFragmentList.add(mineFragment);
        UserAdapter userAdapter = new UserAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(userAdapter);
        getBinding().tabLayout.setTabData(mTabEntities);
        getBinding().tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }


            @Override
            public void onTabReselect(int position) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }


            @Override
            public void onPageSelected(int position) {
                getBinding().tabLayout.setCurrentTab(position);
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(3);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        exit();
    }
}
