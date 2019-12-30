package com.kunpeng.common.utils.image;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.kunpeng.common.R;
import com.kunpeng.common.utils.image.glide.GlideApp;


public class ImageDisplayHelper {
    private RequestOptions options;

    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     *
     * @author xuqm
     */
    private static class ImageDisplayHolder {
        static ImageDisplayHelper instance = new ImageDisplayHelper();
    }

    public static ImageDisplayHelper getInstance() {
        return ImageDisplayHolder.instance;
    }

    private ImageDisplayHelper() {
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_empty_zhihu)
                .error(R.drawable.ic_empty_zhihu);
    }

    /**
     * 加载图片
     *
     * @param mContext 上下文
     * @param view     ImageView
     * @param object   图片资源
     */
    public void display(Context mContext, ImageView view, Object object) {

        GlideApp.with(mContext)
                .asBitmap()  // some .jpeg files are actually gif
                .load(object)
                .apply(options)
                .into(view);
    }

    /**
     * 加载图片
     *
     * @param mContext 上下文
     * @param view     ImageView
     * @param object   图片资源
     * @param width    指定宽
     * @param height   指定高度
     */
    public void display(Context mContext, ImageView view, Object object, int width, int height) {

        RequestOptions o = new RequestOptions()
                .centerCrop()
                .override(width, height)
                .placeholder(R.drawable.ic_empty_zhihu)
                .error(R.drawable.ic_empty_zhihu);

        GlideApp.with(mContext)
                .asBitmap()  // some .jpeg files are actually gif
                .load(object)
                .apply(o)
                .into(view);
    }
    public static boolean isGif(String extension) {
        return !TextUtils.isEmpty(extension) && extension.toLowerCase().equals("gif");
    }

}
