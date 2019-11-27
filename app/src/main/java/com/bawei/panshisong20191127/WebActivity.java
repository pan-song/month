package com.bawei.panshisong20191127;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView web_view;
    private EditText edit;
    private Button but_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        initData();
    }

    @SuppressLint("JavascriptInterface")
    private void initData() {
        web_view.loadUrl("file:///android_asset/info.html");

        WebSettings settings = web_view.getSettings();
        settings.setJavaScriptEnabled(true);

        web_view.addJavascriptInterface(new MyJsDome(), "android");

    }

    private void initView() {
        web_view = (WebView) findViewById(R.id.web_view);
        edit = (EditText) findViewById(R.id.edit);
        edit.setOnClickListener(this);
        but_update = (Button) findViewById(R.id.but_update);
        but_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_update:
                String price = edit.getText().toString().trim();
//                web_view.loadUrl("file:///android_asset/info.html");

                web_view.loadDataWithBaseURL("file:///android_asset/info.html","script:changeNum('"+price+"')","","UTF-8","file:///android_asset/info.html");
                break;
        }
    }

    private class MyJsDome {
        @JavascriptInterface
        public void buyNow(int id) {
            Log.e("TAG", id + "");
        }
    }
}
