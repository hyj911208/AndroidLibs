package com.kunpeng.userchat.module.fragment.fate.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import com.kunpeng.userchat.R;

/**
 * 项目名称：AndroidLibs
 * 类描述：
 * 创建人：wsk
 * 创建时间：2019-12-24 19:57
 * 修改人：wsk
 * 修改时间：2019-12-24 19:57
 * 修改备注：
 *
 * @author wsk
 */
public class UltraPagerAdapter extends PagerAdapter {
    private boolean isMultiScr;


    public UltraPagerAdapter(boolean isMultiScr) {
        this.isMultiScr = isMultiScr;
    }


    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(container.getContext())
                                                                 .inflate(R.layout.pager_child, null);
        TextView textView = (TextView) linearLayout.findViewById(R.id.tv_name);
        textView.setText(position + "名字");
        container.addView(linearLayout);
        //        linearLayout.getLayoutParams().width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180, container.getContext().getResources().getDisplayMetrics());
        //        linearLayout.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 400, container.getContext().getResources().getDisplayMetrics());
        return linearLayout;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        LinearLayout view = (LinearLayout) object;
        container.removeView(view);
    }
}
