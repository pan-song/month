package com.bawei.panshisong20191127.wight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.panshisong20191127.R;

/**
 * Created by Android Studio.
 * User: 潘世松
 * Date: 2019/11/27
 * Time: 9:23
 */
public class MyView extends ViewGroup {

    private Context mContext;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

        //获取父布局的总数量
        int childCount = getChildCount();
        int space = 20;
        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;

        for (int j = 0; j < childCount; j++) {
            //获取子布局的数量
            View childAt = getChildAt(j);
            //设置模式
            childAt.measure(0, 0);
            //获取测量的宽高
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();

            left = right + space;
            right = left + measuredWidth;

            int width = getWidth();
            if (right > width) {
                left = space;
                top = bottom + space;
            }
            right = left + measuredWidth;
            bottom = top + measuredHeight;

            childAt.layout(left,top,right,bottom);

        }

    }

    public void addTag(String str) {
        TextView textView = new TextView(mContext);
        textView.setText(str);
        textView.setTextSize(20);
        textView.setBackgroundResource(R.drawable.edit_text);
        addView(textView);
    }

}
