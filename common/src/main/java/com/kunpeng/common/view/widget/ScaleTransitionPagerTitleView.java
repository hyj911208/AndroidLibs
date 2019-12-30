package com.kunpeng.common.view.widget;

import android.content.Context;
import android.graphics.Typeface;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

/**
 * 项目名称：LotChat
 * 类描述：
 * 创建人：wsk
 * 创建时间：2019-12-20 15:18
 * 修改人：wsk
 * 修改时间：2019-12-20 15:18
 * 修改备注：
 *
 * @author wsk
 */
public class ScaleTransitionPagerTitleView extends ColorTransitionPagerTitleView {
    private float mMinScale = 0.75f;


    public ScaleTransitionPagerTitleView(Context context) {
        super(context);
    }


    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        super.onEnter(index, totalCount, enterPercent, leftToRight);    // 实现颜色渐变
        setScaleX(mMinScale + (1.0f - mMinScale) * enterPercent);
        setScaleY(mMinScale + (1.0f - mMinScale) * enterPercent);
    }


    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        super.onLeave(index, totalCount, leavePercent, leftToRight);    // 实现颜色渐变
        setScaleX(1.0f + (mMinScale - 1.0f) * leavePercent);
        setScaleY(1.0f + (mMinScale - 1.0f) * leavePercent);
    }


    @Override
    public void onSelected(int index, int totalCount) {
        super.onSelected(index, totalCount);
        this.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
    }


    @Override
    public void onDeselected(int index, int totalCount) {
        super.onDeselected(index, totalCount);
        this.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
    }


    public float getMinScale() {
        return mMinScale;
    }


    public void setMinScale(float minScale) {
        mMinScale = minScale;
    }
}
