//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kunpeng.common.config;


public interface SystemConfig {

    /**
     * 环境
     *
     * @return
     */
    public String getServerHost();

    /**
     * appid
     *
     * @return
     */
    public String getAppId();

    /**
     * appName
     *
     * @return
     */
    public String getAppName();

    /**
     * appIcon
     *
     * @return
     */
    public int getAppIcon();

    /**
     * 编译版本
     *
     * @return
     */
    public int getVersionCode();

    /**
     * 当前版本号
     *
     * @return
     */
    public String getVersionName();


}
