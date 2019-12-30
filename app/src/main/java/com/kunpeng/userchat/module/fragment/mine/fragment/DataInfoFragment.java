package com.kunpeng.userchat.module.fragment.mine.fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.kunpeng.common.base.ui.BaseFragment;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.databinding.FragmentInfoBinding;
import com.kunpeng.userchat.model.ImageInfo;
import com.kunpeng.userchat.module.fragment.mine.adapter.DataImageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：AndroidLibs
 * 类描述：个人信息
 * 创建人：wsk
 * 创建时间：2019-12-26 10:32
 * 修改人：wsk
 * 修改时间：2019-12-26 10:32
 * 修改备注：
 *
 * @author wsk
 */
public class DataInfoFragment extends BaseFragment<FragmentInfoBinding> {

    private List<ImageInfo> mImageInfoList;


    @Override
    public void updateData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_info;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mImageInfoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ImageInfo imageInfo = new ImageInfo();
            imageInfo.url = "http://files.live.paywt.com/file/e95b8421-6db1-471b-ae75-5dcfa6968c81.jpg";
            if (i == 0) {
                imageInfo.isblur = true;
            } else {
                imageInfo.isblur = false;
            }
            mImageInfoList.add(imageInfo);
        }
        getBinding().recyclerView
                .setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        DataImageAdapter dataImageAdapter = new DataImageAdapter(mImageInfoList);
        getBinding().recyclerView.setAdapter(dataImageAdapter);
    }
}
