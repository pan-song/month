package com.bawei.panshisong20191127.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.panshisong20191127.app.MyApplication;

import java.util.Map;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/11/27
 * Time: 9:48
 */
public class NetUtil {
    private RequestQueue queue;

    private NetUtil() {
        queue = Volley.newRequestQueue(MyApplication.mContext);
    }

    private static class NetHolder {
        private static NetUtil netUtil = new NetUtil();
    }

    public static NetUtil getInstance() {
        return NetHolder.netUtil;
    }

    //封装Get请求
    public void onGetIofo(String url, final NetCallBack netCallBack) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                netCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netCallBack.onError(error.getMessage());
            }
        });
        queue.add(request);
    }

    //封装Post请求
    public void onPostIofo(String url, final Map<String, String> map, final NetCallBack netCallBack) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                netCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netCallBack.onError(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                if (map != null) {
                    return map;
                }
                return super.getParams();
            }
        };
        queue.add(request);
    }

    //判断是否有网
    public boolean hasNet(Context context) {
        //拿到网络管理者
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //通过网络管理者获取网络信息对象
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        //判断activeNetworkInfo！=null，并且可用
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }

    public interface NetCallBack {
        void onSuccess(String json);

        void onError(String error);
    }
}
