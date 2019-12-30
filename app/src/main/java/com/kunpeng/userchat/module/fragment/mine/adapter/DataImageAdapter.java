package com.kunpeng.userchat.module.fragment.mine.adapter;

import android.widget.ImageView;
import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kunpeng.common.utils.image.ImageDisplayHelper;
import com.kunpeng.userchat.R;
import com.kunpeng.userchat.model.ImageInfo;
import java.util.List;

/**
 * 项目名称：AndroidLibs
 * 类描述：图片显示列表
 * 创建人：wsk
 * 创建时间：2019-12-26 15:13
 * 修改人：wsk
 * 修改时间：2019-12-26 15:13
 * 修改备注：
 *
 * @author wsk
 */
public class DataImageAdapter extends BaseQuickAdapter<ImageInfo, BaseViewHolder> {
    public DataImageAdapter(@Nullable List data) {
        super(R.layout.item_image_album, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ImageInfo item) {
        helper.setText(R.id.tv_num, "12张");
        ImageDisplayHelper.getInstance()
                          .display(mContext, (ImageView) helper.getView(R.id.iv_image), item.url);
    }
}
