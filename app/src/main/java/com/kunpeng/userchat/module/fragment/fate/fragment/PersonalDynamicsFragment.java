package com.kunpeng.userchat.module.fragment.fate.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kunpeng.common.base.ui.BaseFragment;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.databinding.FragmentDynamicsBinding;
import com.kunpeng.userchat.model.fate.PersonalDynamicsInfo;
import com.kunpeng.userchat.module.fragment.fate.adapter.PersonalDynamicsNewAdapter;

import java.util.ArrayList;
import java.util.List;

public class PersonalDynamicsFragment  extends BaseFragment<FragmentDynamicsBinding> {
    private RecyclerView mRecyclerView;
    private List<PersonalDynamicsInfo> mModel;
    private List<PersonalDynamicsInfo.PersonalDynamicsImage> mImageList;
    private PersonalDynamicsInfo.PersonalDynamicsImage mImage;

    @Override
    public void updateData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_dynamics;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mRecyclerView = getBinding().dynamicsFragmentList;
        mModel = new ArrayList<>();
        mImageList=new ArrayList<>();
        for (int i = 0; i <9; i++) {
            PersonalDynamicsInfo mInfo  = new PersonalDynamicsInfo();
            mInfo.setCallContent("点赞");
            mInfo.setGiftInfo("评论");
            mInfo.setReceiveUser("时间");
            mInfo.setSendUser("删除");
            mImage = new PersonalDynamicsInfo.PersonalDynamicsImage();
            mImage.setUrl("http://files.live.paywt.com/file/e95b8421-6db1-471b-ae75-5dcfa6968c81.jpg");
            mImageList.add(mImage);
            mInfo.setUserList(mImageList);
            mModel.add(mInfo);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PersonalDynamicsNewAdapter adapter = new PersonalDynamicsNewAdapter(mModel,getContext());
        mRecyclerView.setAdapter(adapter);

    }
}
