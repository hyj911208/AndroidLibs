package com.kunpeng.userchat.module.fragment.message;

import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kunpeng.common.utils.image.ImageDisplayHelper;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.model.UserInfo;
import java.util.List;

/**
 * 项目名称：AndroidLibs
 * 类描述：会话item
 * 创建人：wsk
 * 创建时间：2019-12-25 14:49
 * 修改人：wsk
 * 修改时间：2019-12-25 14:49
 * 修改备注：
 *
 * @author wsk
 */
public class ConversationAdapter extends BaseQuickAdapter<UserInfo, BaseViewHolder> {
    public ConversationAdapter(@Nullable List data) {
        super(R.layout.item_message_list, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, UserInfo userInfo) {
        helper.setText(R.id.tv_name, userInfo.nickname);
        helper.setText(R.id.tv_content, userInfo.address);
        ImageDisplayHelper.getInstance().display(mContext, (ImageView) helper
                .getView(R.id.iv_head), userInfo.photo);
        helper.setText(R.id.tv_time, "15:30");
        helper.setText(R.id.tv_unread, "9");
    }
}
