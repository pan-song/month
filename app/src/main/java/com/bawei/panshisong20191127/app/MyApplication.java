package com.bawei.panshisong20191127.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/11/27
 * Time: 8:52
 */
public class MyApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
