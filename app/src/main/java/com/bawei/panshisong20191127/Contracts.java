package com.bawei.panshisong20191127;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/11/27
 * Time: 9:00
 */
public interface Contracts {

    interface IModel{
        void onGetIofo(String url,MyCallBack myCallBack);
        void onPostIofo(String url, Map<String,String> map, MyCallBack myCallBack);
    }

    interface IView{
        void onSuccess(String jsonStr);
        void onError(String error);
    }

    interface IPresenter{
        void startRequest(String url);
    }

    interface MyCallBack{
        void onSuccess(String jsonStr);
        void onError(String error);
    }
}
