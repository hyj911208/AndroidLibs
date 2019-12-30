package com.kunpeng.common.bus;

/**
 * Created by wanglei on 2016/11/28.
 */

public interface IBus {

    void register(Object object);
    void unregister(Object object);
    void post(Object event);
    void postSticky(Object event);
    boolean isRegistered(Object object);



}
