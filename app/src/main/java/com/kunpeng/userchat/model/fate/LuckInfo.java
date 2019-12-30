package com.kunpeng.userchat.model.fate;

import com.kunpeng.userchat.model.GiftInfo;
import com.kunpeng.userchat.model.UserInfo;
import java.util.List;

/**
 * 项目名称：AndroidLibs
 * 类描述：缘分吧数据
 * 创建人：wsk
 * 创建时间：2019-12-24 16:26
 * 修改人：wsk
 * 修改时间：2019-12-24 16:26
 * 修改备注：
 *
 * @author wsk
 */
public class LuckInfo {
    public UserInfo sendUser;
    public UserInfo receiveUser;
    public GiftInfo giftInfo;
    public String callContent;
    public List<UserInfo> userList;
}
