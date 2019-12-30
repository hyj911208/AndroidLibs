package com.kunpeng.common.bus;


import org.greenrobot.eventbus.EventBus;

/**
 * Created by wanglei on 2016/11/28.
 */

public class EventBusImpl implements IBus {

    @Override
    public void register(Object object) {
        if (!EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().register(object);
        }
    }


    @Override
    public void unregister(Object object) {
        if (EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().unregister(object);
        }
    }

    @Override
    public void post(Object event) {
        EventBus.getDefault().post(event);
    }

    @Override
    public void postSticky(Object event) {
        EventBus.getDefault().postSticky(event);
    }

    @Override
    public boolean isRegistered(Object object) {
        return EventBus.getDefault().isRegistered(object);
    }
}
