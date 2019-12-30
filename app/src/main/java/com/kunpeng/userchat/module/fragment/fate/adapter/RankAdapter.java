package com.kunpeng.userchat.module.fragment.fate.adapter;

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
 * 类描述：榜单列表
 * 创建人：wsk
 * 创建时间：2019-12-25 10:53
 * 修改人：wsk
 * 修改时间：2019-12-25 10:53
 * 修改备注：
 *
 * @author wsk
 */
public class RankAdapter extends BaseQuickAdapter<UserInfo, BaseViewHolder> {
    public RankAdapter(@Nullable List<UserInfo> data) {
        super(R.layout.item_rank_list, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, UserInfo item) {
        int offset = helper.getLayoutPosition();
        if (offset == 0) {
            helper.setImageResource(R.id.iv_mark, R.drawable.icon_rank_one);
        } else if (offset == 1) {
            helper.setImageResource(R.id.iv_mark, R.drawable.icon_rank_two);
        } else if (offset == 2) {
            helper.setImageResource(R.id.iv_mark, R.drawable.icon_rank_three);
        } else {
            helper.setText(R.id.tv_number, offset + "");
        }
        helper.setText(R.id.tv_name, item.nickname);
        helper.setText(R.id.tv_diamond, "1.5W");
        ImageDisplayHelper.getInstance()
                          .display(mContext, (ImageView) helper.getView(R.id.end_head), item.photo);
    }
}
