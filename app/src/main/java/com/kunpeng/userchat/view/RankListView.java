package com.kunpeng.userchat.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;

import com.kunpeng.userchat.module.fragment.fate.adapter.UltraPagerAdapter;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraScaleTransformer;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.model.UserInfo;
import com.kunpeng.userchat.util.UiUtil;
import java.util.List;

/**
 * 项目名称：AndroidLibs
 * 类描述：排行榜单
 * 创建人：wsk
 * 创建时间：2019-12-24 17:48
 * 修改人：wsk
 * 修改时间：2019-12-24 17:48
 * 修改备注：
 *
 * @author wsk
 */
public class RankListView extends LinearLayout {

    private UltraViewPager mUltraViewPager;
    private UltraPagerAdapter mUltraPagerAdapter;


    public RankListView(Context context) {
        super(context);
    }


    public RankListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public RankListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    {
        View view = inflate(getContext(), R.layout.view_rank_list, this);
        mUltraViewPager = view.findViewById(R.id.ultra_viewpager);
        mUltraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        mUltraPagerAdapter = new UltraPagerAdapter(true);
        mUltraViewPager.setAdapter(mUltraPagerAdapter);
        mUltraViewPager.setMultiScreen(0.7f);
        mUltraViewPager.setItemRatio(1.0f);
        mUltraViewPager.setRatio(2.0f);
        mUltraViewPager.setMaxHeight(UiUtil.dip2px(100));
        mUltraViewPager.setAutoMeasureHeight(true);
        UltraViewPager.Orientation horizontal = UltraViewPager.Orientation.HORIZONTAL;
        mUltraViewPager.setPageTransformer(false, new UltraScaleTransformer());
        mUltraViewPager.setCurrentItem(1);
    }

    public void setData(List<UserInfo> userInfoList){


    }
}
