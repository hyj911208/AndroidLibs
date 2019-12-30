package com.kunpeng.userchat.module.fragment.user.adapter;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

/**
 * 项目名称：AndroidLibs
 * 类描述：
 * 创建人：wsk
 * 创建时间：2019-12-23 15:24
 * 修改人：wsk
 * 修改时间：2019-12-23 15:24
 * 修改备注：
 *
 * @author wsk
 */
public class UserAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;


    public UserAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


    @Override
    public int getCount() {
        return mFragmentList.size();
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //super.destroyItem(container, position, object);
    }
}
