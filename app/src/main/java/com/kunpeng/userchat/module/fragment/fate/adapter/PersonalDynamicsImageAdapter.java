package com.kunpeng.userchat.module.fragment.fate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.model.fate.PersonalDynamicsInfo;

import java.util.List;

public class PersonalDynamicsImageAdapter extends BaseAdapter {

    private List<PersonalDynamicsInfo.PersonalDynamicsImage> provinceBeanList;
    private LayoutInflater layoutInflater;
    private Context mContext;
    public PersonalDynamicsImageAdapter(Context context, List<PersonalDynamicsInfo.PersonalDynamicsImage> provinceBeanList) {
        this.provinceBeanList = provinceBeanList;
        layoutInflater = LayoutInflater.from(context);
        mContext=context;
    }

    @Override
    public int getCount() {
        return provinceBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return provinceBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_grid_image, null);
            holder = new ViewHolder();
            holder.text = convertView.findViewById(R.id.img_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        PersonalDynamicsInfo.PersonalDynamicsImage personalDynamicsImage = provinceBeanList.get(position);
        if (personalDynamicsImage != null) {
            Glide.with(mContext).load(personalDynamicsImage.getUrl()).into(holder.text);
        }
        return convertView;
    }

    class ViewHolder {
        ImageView text;
    }

}
