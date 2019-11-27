package com.bawei.panshisong20191127;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.panshisong20191127.adapter.MyAdapter;
import com.bawei.panshisong20191127.base.BaseActivity;
import com.bawei.panshisong20191127.base.BasePresenter;
import com.bawei.panshisong20191127.bean.DataBean;
import com.bawei.panshisong20191127.presenter.PresenterImpl;
import com.bawei.panshisong20191127.utils.NetUtil;
import com.bawei.panshisong20191127.wight.MyView;
import com.google.gson.Gson;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private EditText edit_text;
    private TextView text_view;
    private MyView myView;
    private List<String> list = new ArrayList<>();
    private String sp;
    private RecyclerView recy_view;
    private List<DataBean.ResultBean> mList = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void startCoding() {

    }

    @Override
    protected BasePresenter initPresenter() {
        return new PresenterImpl();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //获取资源ID
        edit_text = (EditText) findViewById(R.id.edit_text);
        text_view = (TextView) findViewById(R.id.text_view);
        myView = (MyView) findViewById(R.id.myView);
        recy_view = findViewById(R.id.recy_view);

        recy_view.setLayoutManager(new GridLayoutManager(this, 2));

        edit_text.setOnClickListener(this);
        text_view.setOnClickListener(this);
        myView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_view:
                //获取输入框的值
                sp = edit_text.getText().toString().trim();
                //判断SP是否为空
                if (sp.isEmpty()) {
                    Toast.makeText(this, "参数为空", Toast.LENGTH_SHORT).show();
                } else {
                    //判断参数是否重复
                    if (!list.contains(sp)) {
                        myView.addTag(sp);
                        list.add(sp);
                    } else {
                        Toast.makeText(this, "参数重复", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.myView:
                if (sp != null) {

                    //判断是否有网
                    if (NetUtil.getInstance().hasNet(this)) {

                        String url = "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=" + URLEncoder.encode(sp) + "&page=1&count=5";
                        //开始请求接口
                        mPresenter.startRequest(url);
                        //设置适配器
                        myAdapter = new MyAdapter(this, mList);
                        recy_view.setAdapter(myAdapter);

                    } else {
                        Toast.makeText(this, "当前没有网络", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }


    @Override
    public void onSuccess(String jsonStr) {
        //解析
        DataBean dataBean = new Gson().fromJson(jsonStr, DataBean.class);
        //清除集合里的数据
        mList.clear();
        //重新添加
        mList.addAll(dataBean.getResult());
        //刷新适配器
        myAdapter.notifyDataSetChanged();


        myAdapter.setItemClick(new MyAdapter.ItemClick() {
            @Override
            public void setOnItemClick(int position) {
                //跳转到WebActivity页面
                Intent intent = new Intent(MainActivity.this,WebActivity.class);
                //开启
                startActivity(intent);
                //关闭本页面
                finish();

            }
        });

    }

    @Override
    public void onError(String error) {

    }

    @Override
    protected int layoutID() {
        return R.layout.activity_main;
    }
}
