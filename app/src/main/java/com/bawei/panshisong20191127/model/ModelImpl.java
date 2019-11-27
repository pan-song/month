package com.bawei.panshisong20191127.model;

import com.bawei.panshisong20191127.Contracts;
import com.bawei.panshisong20191127.utils.NetUtil;

import java.security.Key;
import java.util.Map;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/11/27
 * Time: 9:05
 */
public class ModelImpl implements Contracts.IModel {
    @Override
    public void onGetIofo(String url, final Contracts.MyCallBack myCallBack) {
        NetUtil.getInstance().onGetIofo(url, new NetUtil.NetCallBack() {
            @Override
            public void onSuccess(String json) {
                myCallBack.onSuccess(json);
            }

            @Override
            public void onError(String error) {
                myCallBack.onError(error);
            }
        });
    }

    @Override
    public void onPostIofo(String url, Map<String, String> map, final Contracts.MyCallBack myCallBack) {
        NetUtil.getInstance().onPostIofo(url, map, new NetUtil.NetCallBack() {
            @Override
            public void onSuccess(String json) {
                myCallBack.onSuccess(json);
            }

            @Override
            public void onError(String error) {
                myCallBack.onError(error);
            }
        });
    }
}
