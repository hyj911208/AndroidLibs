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
public class PersonalDynamicsInfo {
    public String sendUser;
    public String receiveUser;
    public String giftInfo;
    public String callContent;
    public List<PersonalDynamicsImage> userList;

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public String getGiftInfo() {
        return giftInfo;
    }

    public void setGiftInfo(String giftInfo) {
        this.giftInfo = giftInfo;
    }

    public String getCallContent() {
        return callContent;
    }

    public void setCallContent(String callContent) {
        this.callContent = callContent;
    }

    public List<PersonalDynamicsImage> getUserList() {
        return userList;
    }

    public void setUserList(List<PersonalDynamicsImage> userList) {
        this.userList = userList;
    }

    public static class PersonalDynamicsImage {
        public String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
