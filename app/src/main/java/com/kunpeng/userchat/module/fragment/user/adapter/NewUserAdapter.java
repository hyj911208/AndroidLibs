package com.kunpeng.userchat.module.fragment.user.adapter;

import android.content.Context;
import android.widget.ImageView;
import com.kunpeng.common.utils.image.ImageDisplayHelper;
import com.kunpeng.common.view.adapter.CommonAdapter;
import com.kunpeng.common.view.adapter.ViewHolder;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.model.UserInfo;
import java.util.List;

/**
 * 项目名称：AndroidLibs
 * 类描述：
 * 创建人：wsk
 * 创建时间：2019-12-23 19:42
 * 修改人：wsk
 * 修改时间：2019-12-23 19:42
 * 修改备注：
 *
 * @author wsk
 */
public class NewUserAdapter extends CommonAdapter<UserInfo> {
    public NewUserAdapter(Context context, List<UserInfo> datas, int layoutId) {
        super(context, datas, layoutId);
    }


    @Override
    protected void convert(ViewHolder holder, UserInfo userInfo, int position) {
        ImageDisplayHelper.getInstance().display(mContext, (ImageView) holder
                .getView(R.id.head_image), userInfo.photo);
        holder.setText(R.id.nick_name, userInfo.nickname);
        holder.setText(R.id.address, userInfo.address);
    }
}
