package com.kunpeng.userchat.util;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.kunpeng.common.utils.image.ImageDisplayHelper;
import com.youth.banner.loader.ImageLoaderInterface;

/**
 * 项目名称：heixiu
 * 类描述：
 * 创建人：wsk
 * 创建时间：2016/12/7 19:06
 * 修改人：wsk
 * 修改时间：2016/12/7 19:06
 * 修改备注：
 */
public class BannerImageLoader implements ImageLoaderInterface<ImageView> {

    private int mWidth;
    private int mHeight;
    private int mType;


    public BannerImageLoader(int width, int height) {
        this.mWidth = width;
        this.mHeight = height;
    }


    public void setType(int type) {
        mType = type;
    }


    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageDisplayHelper.getInstance().display(context, imageView, path, mWidth, mHeight);
    }


    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(mWidth, mHeight));
        return imageView;
    }
}
