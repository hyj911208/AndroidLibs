package com.kunpeng.userchat.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class RecyclerGridView  extends GridView {
    public RecyclerGridView(Context context) {
        super(context);
    }

    public RecyclerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RecyclerGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //makeMeasureSpec根据提供的大小值和模式创建一个测量值(格式)
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}