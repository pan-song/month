package com.bawei.panshisong20191127.base;

import com.bawei.panshisong20191127.Contracts;

import java.lang.ref.WeakReference;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/11/27
 * Time: 9:02
 */
public abstract class BasePresenter<V extends Contracts.IView> implements Contracts.IPresenter {
    //虚引用
    private WeakReference<V> mWeakR;

    public BasePresenter() {
        initModel();
    }

    //初始化M层
    protected abstract void initModel();

    //绑定View
    protected void onAttchView(V v) {
        mWeakR = new WeakReference<>(v);
    }

    //解绑View
    protected void onDeAttchView() {
        if (mWeakR != null) {
            mWeakR.clear();
            mWeakR = null;
        }
    }

    //获取View
    protected V getView() {
        return mWeakR.get();
    }
}
