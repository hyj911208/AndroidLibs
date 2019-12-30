package com.kunpeng.userchat.module.fragment.fate.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.kunpeng.common.base.ui.BaseFragment;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.databinding.FragmentRanListBinding;
import com.kunpeng.userchat.module.fragment.user.adapter.UserAdapter;
import java.util.ArrayList;
import java.util.List;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

/**
 * 项目名称：AndroidLibs
 * 类描述：主播榜
 * 创建人：wsk
 * 创建时间：2019-12-24 11:16
 * 修改人：wsk
 * 修改时间：2019-12-24 11:16
 * 修改备注：
 *
 * @author wsk
 */
public class HostPlayerFragment extends BaseFragment<FragmentRanListBinding> {
    private List<String> mTitleDataList;


    @Override
    public void updateData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_ran_list;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        initFragment();
        initMagicIndicator();
    }


    private void initMagicIndicator() {
        getBinding().magicIndicator.setBackgroundResource(R.drawable.round_indicator_bg);
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitleDataList == null ? 0 : mTitleDataList.size();
            }


            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(mTitleDataList.get(index));
                clipPagerTitleView
                        .setTextColor(ContextCompat.getColor(getContext(), R.color.color_222222));
                clipPagerTitleView.setClipColor(Color.WHITE);
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getBinding().viewPager.setCurrentItem(index);
                    }
                });
                return clipPagerTitleView;
            }


            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                float navigatorHeight = context.getResources().getDimension(R.dimen.space_34);
                float borderWidth = UIUtil.dip2px(context, 1);
                float lineHeight = navigatorHeight - 2 * borderWidth;
                indicator.setLineHeight(lineHeight);
                indicator.setRoundRadius(lineHeight / 2);
                indicator.setYOffset(borderWidth);
                indicator.setColors(ContextCompat.getColor(getContext(), R.color.color_FDD226));
                return indicator;
            }
        });
        getBinding().magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(getBinding().magicIndicator, getBinding().viewPager);
    }


    private void initFragment() {
        RankDayFragment rankDayFragment = new RankDayFragment();
        RankWeekFragment rankWeekFragment = new RankWeekFragment();
        RankAllFragment allFragment = new RankAllFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(rankDayFragment);
        fragments.add(rankWeekFragment);
        fragments.add(allFragment);
        UserAdapter userAdapter = new UserAdapter(getChildFragmentManager(), fragments);
        getBinding().viewPager.setAdapter(userAdapter);
        mTitleDataList = new ArrayList<>();
        mTitleDataList.add(getString(R.string.daily_list));
        mTitleDataList.add(getString(R.string.weekly_list));
        mTitleDataList.add(getString(R.string.overall_list));
    }
}
