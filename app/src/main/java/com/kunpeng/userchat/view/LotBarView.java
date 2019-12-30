package com.kunpeng.userchat.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.kunpeng.common.view.widget.imageview.CircleImageView;
import com.kunpeng.userchat.model.fate.LuckInfo;
import com.shehuan.niv.NiceImageView;
import com.kunpeng.common.utils.image.ImageDisplayHelper;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.model.GiftInfo;
import com.kunpeng.userchat.model.UserInfo;

/**
 * 项目名称：AndroidLibs
 * 类描述：
 * 创建人：wsk
 * 创建时间：2019-12-24 16:34
 * 修改人：wsk
 * 修改时间：2019-12-24 16:34
 * 修改备注：
 *
 * @author wsk
 */
public class LotBarView extends LinearLayout {
    private CircleImageView mNiceImageView, mEndHeadImageView;
    private ImageView mVipImageView, mGiftImageView, mPraiseText;
    private TextView mTvOnline, mTvLeftNickName, mTvRightNickName, mGiftCoin, mDescriptionText, mPraiseNum;
    private CircleImageView mHeadImage1, mHeadImage2, mHeadImage3, mHeadImage4, mHeadImage5;


    public LotBarView(Context context) {
        super(context);
    }


    public LotBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public LotBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    {
        View view = inflate(getContext(), R.layout.item_lot_header, this);
        mNiceImageView = view.findViewById(R.id.iv_left);
        mVipImageView = view.findViewById(R.id.iv_vip);
        mGiftImageView = view.findViewById(R.id.gift_image);
        mEndHeadImageView = view.findViewById(R.id.end_head);
        mTvOnline = view.findViewById(R.id.tv_online);
        mTvLeftNickName = view.findViewById(R.id.left_nick_name);
        mTvRightNickName = view.findViewById(R.id.right_nick_name);
        mGiftCoin = view.findViewById(R.id.gift_coin);
        mDescriptionText = view.findViewById(R.id.description_text);
        mHeadImage1 = view.findViewById(R.id.head_image1);
        mHeadImage2 = view.findViewById(R.id.head_image2);
        mHeadImage3 = view.findViewById(R.id.head_image3);
        mHeadImage4 = view.findViewById(R.id.head_image4);
        mHeadImage5 = view.findViewById(R.id.head_image5);
        mPraiseNum = view.findViewById(R.id.praise_num);
        mPraiseText = view.findViewById(R.id.praise_text);
    }


    public void setData(LuckInfo luckInfo) {
        if (null == luckInfo) {
            return;
        }
        UserInfo sendUser = luckInfo.sendUser;
        if (null != sendUser) {
            ImageDisplayHelper.getInstance().display(getContext(), mNiceImageView, sendUser.photo);
            mTvLeftNickName.setText(sendUser.nickname);
        }
        UserInfo receiveUser = luckInfo.receiveUser;
        if (null != receiveUser) {
            ImageDisplayHelper.getInstance()
                              .display(getContext(), mEndHeadImageView, receiveUser.photo);
            mTvRightNickName.setText(receiveUser.nickname);
        }
        GiftInfo giftInfo = luckInfo.giftInfo;
        if (null != giftInfo) {
            ImageDisplayHelper.getInstance()
                              .display(getContext(), mGiftImageView, giftInfo.giftUrl);
            mGiftCoin.setText(String.valueOf(giftInfo.price));
        }
        mDescriptionText.setText(luckInfo.callContent);
        if (luckInfo.userList.size() > 0) {
            for (int i = 0; i < luckInfo.userList.size(); i++) {
                UserInfo userInfo = luckInfo.userList.get(i);
                if (i == 0) {
                    ImageDisplayHelper.getInstance()
                                      .display(getContext(), mHeadImage1, userInfo.photo);
                } else if (i == 1) {
                    ImageDisplayHelper.getInstance()
                                      .display(getContext(), mHeadImage2, userInfo.photo);
                } else if (i == 2) {
                    ImageDisplayHelper.getInstance()
                                      .display(getContext(), mHeadImage3, userInfo.photo);
                } else if (i == 3) {
                    ImageDisplayHelper.getInstance()
                                      .display(getContext(), mHeadImage4, userInfo.photo);
                }
            }
        }
        mPraiseNum.setText(luckInfo.userList.size() + "人送上祝福");

        mPraiseText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
