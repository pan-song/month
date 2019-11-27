package com.bawei.panshisong20191127.utils;

import android.widget.ImageView;

import com.bawei.panshisong20191127.R;
import com.bawei.panshisong20191127.app.MyApplication;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/11/27
 * Time: 8:56
 */
public class GlideUtil {
    public static void loadImage(String url, ImageView imageView) {
        Glide.with(MyApplication.mContext)
                .load(url)
                .error(R.drawable.ic_launcher)
                .placeholder(R.drawable.ic_launcher)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }
}
