package com.kunpeng.userchat.module.fragment.fate.adapter;

import android.content.Context;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kunpeng.common.utils.image.ImageDisplayHelper;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.model.GiftInfo;
import com.kunpeng.userchat.model.UserInfo;
import com.kunpeng.userchat.model.fate.LuckInfo;

import java.util.List;

/**
 * 项目名称：AndroidLibs
 * 类描述：缘吧
 * 创建人：wsk
 * 创建时间：2019-12-24 11:55
 * 修改人：wsk
 * 修改时间：2019-12-24 11:55
 * 修改备注：
 *
 * @author wsk
 */
public class LotBarAdapter extends BaseQuickAdapter<LuckInfo, BaseViewHolder> {
    private Context mContext;


    public LotBarAdapter(@Nullable List data, Context context) {
        super(R.layout.item_lot_bar, data);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, LuckInfo luckInfo) {
        UserInfo sendUser = luckInfo.sendUser;
        if (null != sendUser) {
            ImageDisplayHelper.getInstance().display(mContext, (ImageView) helper
                    .getView(R.id.niceImageView), sendUser.photo);
            helper.setText(R.id.left_nick_name, sendUser.nickname);
        }
        UserInfo receiveUser = luckInfo.receiveUser;
        if (null != receiveUser) {
            ImageDisplayHelper.getInstance().display(mContext, (ImageView) helper
                    .getView(R.id.end_head), receiveUser.photo);
            helper.setText(R.id.right_nick_name, receiveUser.nickname);
        }
        GiftInfo giftInfo = luckInfo.giftInfo;
        if (null != giftInfo) {
            ImageDisplayHelper.getInstance().display(mContext, (ImageView) helper
                    .getView(R.id.gift_image), giftInfo.giftUrl);
            helper.setText(R.id.gift_coin, String.valueOf(giftInfo.price));
        }
        helper.setText(R.id.description_text, luckInfo.callContent);
        if (luckInfo.userList.size() > 0) {
            for (int i = 0; i < luckInfo.userList.size(); i++) {
                UserInfo userInfo = luckInfo.userList.get(i);
                if (i == 0) {
                    ImageDisplayHelper.getInstance().display(mContext, (ImageView) helper
                            .getView(R.id.head_image1), userInfo.photo);
                } else if (i == 1) {
                    ImageDisplayHelper.getInstance().display(mContext, (ImageView) helper
                            .getView(R.id.head_image2), userInfo.photo);
                } else if (i == 2) {
                    ImageDisplayHelper.getInstance().display(mContext, (ImageView) helper
                            .getView(R.id.head_image3), userInfo.photo);
                } else if (i == 3) {
                    ImageDisplayHelper.getInstance().display(mContext, (ImageView) helper
                            .getView(R.id.head_image4), userInfo.photo);
                } else if (i == 4) {
                    ImageDisplayHelper.getInstance().display(mContext, (ImageView) helper
                            .getView(R.id.head_image5), userInfo.photo);
                }
            }
        }
        helper.setText(R.id.praise_num, luckInfo.userList.size() + "人送上祝福");
    }
}
