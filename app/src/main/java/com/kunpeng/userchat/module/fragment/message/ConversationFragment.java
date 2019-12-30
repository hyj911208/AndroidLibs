package com.kunpeng.userchat.module.fragment.message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gyf.immersionbar.ImmersionBar;
import com.kunpeng.common.base.ui.BaseImmersionFragment;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.databinding.FragmentConversationBinding;
import com.kunpeng.userchat.model.UserInfo;
import com.kunpeng.userchat.module.activity.message.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class ConversationFragment extends BaseImmersionFragment<FragmentConversationBinding> implements View.OnClickListener {
    private List<UserInfo> mUserInfoList;


    @Override
    public void updateData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_conversation;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mUserInfoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.nickname = "土豪";
            userInfo.address = "在吗,老板";
            userInfo.photo = "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2208923809,2271919516&fm=26&gp=0.jpg";
            mUserInfoList.add(userInfo);
        }
        ConversationAdapter conversationAdapter = new ConversationAdapter(mUserInfoList);
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getBinding().recyclerView.setAdapter(conversationAdapter);
        getBinding().coolTitle.messageChange.setVisibility(View.GONE);
        getBinding().coolTitle.searchButton.setOnClickListener(this);
       // conversationAdapter.setHeaderView(getHeadView());
    }


   /* private View getHeadView() {
        ConversationHeadView conversationHeadView = new ConversationHeadView(getContext());
        conversationHeadView.setClickLister(new ConversationHeadView.ClickLister() {
            @Override
            public void ChatSuggestion() {
                ARouter.getInstance().build(RoutConstants.Activity.COMPLAINT_ADVICE_ACTIVITY)
                       .navigation();
            }


            @Override
            public void ChatSystem() {

            }
        });
        return conversationHeadView;
    }*/
   @Override
   public void initImmersionBar() {
       ImmersionBar.with(this)
               .statusBarView(getBinding().view)
               .statusBarDarkFont(true)
               .init();
   }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_button:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            default:
                break;
        }
    }
}
