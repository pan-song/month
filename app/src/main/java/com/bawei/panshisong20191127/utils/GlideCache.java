package com.bawei.panshisong20191127.utils;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/11/27
 * Time: 8:58
 */
public class GlideCache implements GlideModule {
    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        int myCacheSize = 1024 * 1024 * 20;
        File file = new File(Environment.getExternalStorageDirectory(),"images");
        String path = file.getPath();
        builder.setDiskCache(new DiskLruCacheFactory(path,myCacheSize));
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {

    }
}
