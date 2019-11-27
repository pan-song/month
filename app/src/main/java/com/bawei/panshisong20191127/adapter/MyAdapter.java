package com.bawei.panshisong20191127.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.panshisong20191127.R;
import com.bawei.panshisong20191127.bean.DataBean;
import com.bawei.panshisong20191127.utils.GlideUtil;

import java.util.List;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/11/27
 * Time: 10:09
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context mContext;
    private List<DataBean.ResultBean> mList;

    public MyAdapter(Context mContext, List<DataBean.ResultBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ViewHolder viewHolder = null;
        View view = null;
        view = View.inflate(mContext, R.layout.shop, null);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        GlideUtil.loadImage(mList.get(i).getMasterPic(), viewHolder.recy_masterPic);
        viewHolder.recy_commodityName.setText(mList.get(i).getCommodityName() + "");
        viewHolder.recy_price.setText(mList.get(i).getPrice() + "");
        //添加点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.setOnItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recy_masterPic;
        TextView recy_commodityName, recy_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recy_masterPic = itemView.findViewById(R.id.recy_masterPic);
            recy_commodityName = itemView.findViewById(R.id.recy_commodityName);
            recy_price = itemView.findViewById(R.id.recy_price);
        }
    }

    //通过接口回调设置点击事件
    public interface ItemClick{
        void setOnItemClick(int position);
    }

    private ItemClick itemClick;

    public void setItemClick(ItemClick itemClick){
        this.itemClick = itemClick;
    }

}
