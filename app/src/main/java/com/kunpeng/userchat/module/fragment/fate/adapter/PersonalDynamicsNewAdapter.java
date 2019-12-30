package com.kunpeng.userchat.module.fragment.fate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kunpeng.userchat.R;
import com.kunpeng.userchat.model.fate.PersonalDynamicsInfo;
import com.kunpeng.userchat.view.RecyclerGridView;

import java.util.List;

public class PersonalDynamicsNewAdapter  extends RecyclerView.Adapter<PersonalDynamicsNewAdapter.ViewHolder> {

     private List<PersonalDynamicsInfo> mModelTest;
     private Context mContext;
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dianzan;
        TextView pinglun;
        TextView shijian;
        RecyclerGridView mGridView;
        public ViewHolder(View view) {
            super(view);
            dianzan = (TextView) view.findViewById(R.id.tv_comment);
            pinglun = (TextView) view.findViewById(R.id.time);
            shijian = (TextView) view.findViewById(R.id.tv_like);
            mGridView= view.findViewById(R.id.gride);
        }

    }

    public PersonalDynamicsNewAdapter(  List<PersonalDynamicsInfo> mModel,Context context ) {
        mModelTest = mModel;
        mContext=context;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_personal_dynamics, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        PersonalDynamicsInfo fruit = mModelTest.get(position);
        holder.dianzan.setText(fruit.getGiftInfo());
        holder.pinglun.setText(fruit.getReceiveUser());
        holder.shijian.setText(fruit.getCallContent());
        holder.mGridView.setAdapter(new PersonalDynamicsImageAdapter(mContext,fruit.userList));
    }

    @Override
    public int getItemCount() {
        return mModelTest.size();
    }
}