package com.kunpeng.userchat.module.fragment.message;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.kunpeng.userchat.R;

/**
 * 项目名称：AndroidLibs
 * 类描述：消息页面头部
 * 创建人：wsk
 * 创建时间：2019-12-25 13:49
 * 修改人：wsk
 * 修改时间：2019-12-25 13:49
 * 修改备注：
 *
 * @author wsk
 */
public class ConversationHeadView extends LinearLayout implements View.OnClickListener {
    private ClickLister mClickLister;


    public ConversationHeadView(Context context) {
        super(context);
    }


    public ConversationHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public ConversationHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    {
        View view = inflate(getContext(), R.layout.view_conver_head, this);
        TextView tvChat_suggestion = view.findViewById(R.id.tv_chat_suggestion);
        TextView tvChatSystem = view.findViewById(R.id.tv_chat_system);
        tvChat_suggestion.setOnClickListener(this);
        tvChatSystem.setOnClickListener(this);
    }


    public void setClickLister(ClickLister clickLister) {
        mClickLister = clickLister;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_chat_suggestion:
                if (null != mClickLister) {
                    mClickLister.ChatSuggestion();
                }
                break;
            case R.id.tv_chat_system:
                if (null != mClickLister) {
                    mClickLister.ChatSystem();
                }
                break;
            default:
                break;
        }
    }


    public interface ClickLister {
        /**
         * 投诉建议监听
         */
        void ChatSuggestion();

        /**
         * 系统消息监听
         */
        void ChatSystem();
    }
}
