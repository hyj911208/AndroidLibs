package com.kunpeng.common.base.ui.callback;

import android.os.Bundle;

public interface UiCallback {
    int getLayoutId();

    void initData(Bundle savedInstanceState);

    void setListener();

    /**
     * 当前页面启用eventbus,需要
     * *@Subscribe(threadMode = ThreadMode.MAIN)
     * *public void
     *
     * @return
     */
    boolean useEventBus();

}
