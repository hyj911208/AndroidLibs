package com.kunpeng.userchat.module.fragment.mine.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.kunpeng.userchat.module.fragment.mine.fragment.DataInfoFragment;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.kunpeng.common.base.ui.BaseActivity;
import com.kunpeng.common.utils.image.ImageDisplayHelper;
import com.kunpeng.common.view.widget.ScaleTransitionPagerTitleView;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.constant.RoutConstants;
import com.kunpeng.userchat.databinding.ActOtherPersonBinding;
import com.kunpeng.userchat.module.fragment.find.DynamicFragment;
import com.kunpeng.userchat.module.fragment.user.adapter.UserAdapter;
import com.kunpeng.userchat.util.BannerImageLoader;
import com.kunpeng.userchat.util.UiUtil;
import com.youth.banner.BannerConfig;
import java.util.ArrayList;
import java.util.List;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

/**
 * 项目名称：AndroidLibs
 * 类描述：他人主页
 * 创建人：wsk
 * 创建时间：2019-12-25 18:13
 * 修改人：wsk
 * 修改时间：2019-12-25 18:13
 * 修改备注：
 *
 * @author wsk
 */
@Route(path = RoutConstants.Activity.OTHER_PERSON_PAGEA_CTIVITY)
public class OtherPersonPageActivity extends BaseActivity<ActOtherPersonBinding> {
    private BannerImageLoader mBannerImageLoader;
    private List<String> mTitleDataList;


    @Override
    public int getLayoutId() {
        return R.layout.act_other_person;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        getBinding().nickName.setText("我是谁");
        getBinding().tvId.setText("ID:0001");
        getBinding().tvDiamond.setText("888");
        getBinding().tvRank.setText("3333");
        getBinding().tvOnlineStatus.setText("空闲");
        ImageDisplayHelper.getInstance()
                          .display(this, getBinding().endHead, "http://files.live.paywt.com/file/1873d1f6-f822-4497-8333-7fb0cb501df0.jpg");
        mBannerImageLoader = new BannerImageLoader(UiUtil.getDisplayWidth(), UiUtil.dip2px(240));
        getBinding().banner.setImageLoader(mBannerImageLoader);
        getBinding().banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        getBinding().banner.setIndicatorGravity(BannerConfig.CENTER);
        List<String> murl = new ArrayList<>();
        murl.add("http://files.live.paywt.com/file/1873d1f6-f822-4497-8333-7fb0cb501df0.jpg");
        murl.add("http://files.live.paywt.com/file/1873d1f6-f822-4497-8333-7fb0cb501df0.jpg");
        getBinding().banner.setImages(murl);
        getBinding().banner.isAutoPlay(false);
        //getBinding().banner.setOnBannerListener(position -> ShowImageDialogFragment
        //        .showImage(OtherPersonActivity.this, imageInfos, position, 0));
        getBinding().banner.start();
        initFragment();
        initTitle();
    }


    private void initTitle() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
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
                scaleTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getBinding().viewPager.setCurrentItem(index);
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
        getBinding().magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(getBinding().magicIndicator, getBinding().viewPager);
    }


    private void initFragment() {
        DynamicFragment dynamicFragment = new DynamicFragment();
        DataInfoFragment dataInfoFragment = new DataInfoFragment();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(dataInfoFragment);
        fragments.add(dynamicFragment);
        UserAdapter userAdapter = new UserAdapter(getSupportFragmentManager(), fragments);
        getBinding().viewPager.setAdapter(userAdapter);
        mTitleDataList = new ArrayList<>();
        mTitleDataList.add(getString(R.string.data));
        mTitleDataList.add(getString(R.string.dynamic));
    }
}
