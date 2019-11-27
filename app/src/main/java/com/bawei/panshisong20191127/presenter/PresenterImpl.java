package com.bawei.panshisong20191127.presenter;

import com.bawei.panshisong20191127.Contracts;
import com.bawei.panshisong20191127.base.BasePresenter;
import com.bawei.panshisong20191127.model.ModelImpl;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/11/27
 * Time: 9:06
 */
public class PresenterImpl extends BasePresenter {

    private ModelImpl model;

    @Override
    protected void initModel() {
        model = new ModelImpl();
    }

    @Override
    public void startRequest(String url) {
        model.onGetIofo(url, new Contracts.MyCallBack() {
            @Override
            public void onSuccess(String jsonStr) {
                getView().onSuccess(jsonStr);
            }

            @Override
            public void onError(String error) {
                getView().onError(error);
            }
        });
    }
}
