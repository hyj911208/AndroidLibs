package com.kunpeng.userchat.module.fragment.user;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import com.kunpeng.common.base.ui.BaseFragment;
import com.kunpeng.common.view.adapter.GridSpacingItemDecoration;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.databinding.FragmentCommonBinding;
import com.kunpeng.userchat.model.UserInfo;
import java.util.ArrayList;
import java.util.List;
import net.lucode.hackware.magicindicator.buildins.UIUtil;

/**
 * 项目名称：AndroidLibs
 * 类描述：推荐
 * 创建人：wsk
 * 创建时间：2019-12-23 18:17
 * 修改人：wsk
 * 修改时间：2019-12-23 18:17
 * 修改备注：
 *
 * @author wsk
 */
public class RecommendFragment extends BaseFragment<FragmentCommonBinding> {
    private List<UserInfo> mUserInfos;
    private RecommendAdapter mRecommendAdapter;


    @Override
    public void updateData() {

    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_common;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mUserInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.nickname = "土豪";
            userInfo.address = "北京";
            userInfo.photo = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577687463\" +\n" +
                    "                    \"992&di=e807b57409766d62d43f94b8a107fb62&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn%2Fw640h426%2F20180201%2F081d-fyrcsrw4665955.jpg";
            mUserInfos.add(userInfo);
        }

        getBinding().recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        getBinding().recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, UIUtil
                .dip2px(mContext, 15), true));
        mRecommendAdapter = new RecommendAdapter(mContext, mUserInfos, R.layout.item_recommend);
        getBinding().recyclerView.setAdapter(mRecommendAdapter);
    }
}
