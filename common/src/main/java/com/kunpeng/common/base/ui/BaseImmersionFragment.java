package com.kunpeng.common.base.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.immersionbar.components.ImmersionFragment;
import com.kunpeng.common.base.ui.callback.UiCallback;
import com.kunpeng.common.bus.BusFactory;

/**
 * ImmersionFragment 继承
 * 实现沉浸式
 * immersionBarEnabled  true 支持沉浸式 false 不支持
 */
public abstract class BaseImmersionFragment<V extends ViewDataBinding> extends ImmersionFragment implements UiCallback {
    private View rootView;
    protected Activity mContext;
    private V binding;
    protected String TAG = this.getClass().getSimpleName();

    //UI是否初始化
    private boolean isViewInitiated = false;
    //是否可见
    private boolean isUserVisible = true;
    //是否执行过更新数据接口
    private boolean isDataInitiated = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null);
            bindUI(rootView);
        } else {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }
        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
    }


    protected V getBinding() {
        return binding;
    }


    private void bindUI(View rootView) {
        binding = DataBindingUtil.bind(rootView);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (useEventBus()) {
            BusFactory.getBus().register(this);
        }
        mContext = this.getActivity();
        initData(savedInstanceState);
        isViewInitiated = true;
        lazyLoad();
        setListener();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isUserVisible = !hidden;
        lazyLoad();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }


    public abstract void updateData();


    /**
     * 是否每次页面可见，都刷新数据
     *
     * @return 是否刷新
     */
    public boolean useUpdate() {
        return false;
    }


    //
    private void lazyLoad() {
        if (isUserVisible && getUserVisibleHint() && isViewInitiated &&
                (useUpdate() || !isDataInitiated)) {
            updateData();
            isDataInitiated = true;
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }


    @Override
    public boolean useEventBus() {
        return false;
    }


    @Override
    public void setListener() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (useEventBus()) {
            BusFactory.getBus().unregister(this);
        }
    }
}
