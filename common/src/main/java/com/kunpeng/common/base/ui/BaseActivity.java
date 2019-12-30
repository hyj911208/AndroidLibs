package com.kunpeng.common.base.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.immersionbar.ImmersionBar;
import com.kunpeng.common.base.ui.callback.UiCallback;
import com.kunpeng.common.bus.BusFactory;
import com.kunpeng.common.manager.ActivityStackManager;
import com.kunpeng.common.utils.ToolsHelper;

/**
 * @Author xuqm
 * @Date 2019/12/18-21:59
 * @Email xuqinmin12@sina.com
 */
public abstract class BaseActivity<V extends ViewDataBinding> extends AppCompatActivity implements UiCallback {
    protected String TAG = this.getClass().getSimpleName();
    protected Activity mContext;
    private V binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        //act统一管理
        ActivityStackManager.getInstance().pushOneActivity(this);
        //统一的context管理
        this.mContext = this;
        //layout加载
        if (getLayoutId() > 0) {
            bindUI(getLayoutId());
        }
        //子类重写进行页面初始化
        initData(savedInstanceState);
        // 沉浸式状态栏
        ImmersionBar.with(this).keyboardEnable(true).statusBarDarkFont(isDark()).init();
        //各种点击事件监听的初始化
        setListener();
    }
    //    状态栏字体颜色 --- true 黑色
    protected boolean isDark(){
        return false;
    }


    protected V getBinding() {
        return binding;
    }


    public void bindUI(@LayoutRes int layoutResID) {
        binding = DataBindingUtil.setContentView(mContext, layoutResID);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (useEventBus()) {
            BusFactory.getBus().register(this);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().popOneActivity(this);
        if (useEventBus()) {
            BusFactory.getBus().unregister(this);
        }
        // 必须调用该方法，防止内存泄漏
        //ImmersionBar.with(this).destroy();
    }


    /**
     * 当前页面启用eventbus,需要
     * *@Subscribe(threadMode = ThreadMode.MAIN)
     * *public void...
     */
    @Override
    public boolean useEventBus() {
        return false;
    }


    @Override
    public void setListener() {

    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //非默认值
        if (newConfig.fontScale != 1) {
            getResources();
        }
        // 如果你的app可以横竖屏切换，并且适配4.4或者emui3手机请务必在onConfigurationChanged方法里添加这句话
        ImmersionBar.with(this).init();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                hideInput(v);
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }


    public void hideInput(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


    public boolean isShouldHideInput(View v, MotionEvent event) {
        if ((v instanceof EditText)) {
            int[] leftTop = { 0, 0 };
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            // 点击的是输入框区域，保留点击EditText的事件
            return !(event.getX() > left) || !(event.getX() < right) || !(event.getY() > top) ||
                    !(event.getY() < bottom);
        }
        return false;
    }


    private long exitTime = 0;// 等待时间


    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToolsHelper.showInfo("再按一次退出该页面");
            exitTime = System.currentTimeMillis();
        } else {
            ActivityStackManager.getInstance().finishAllActivity();
        }
    }
}
