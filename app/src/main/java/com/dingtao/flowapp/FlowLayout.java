package com.dingtao.flowapp;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author dingtao
 * @date 2018/11/29 10:36
 * 效果参照：https://www.jianshu.com/p/05954091c650
 */
public class FlowLayout extends FrameLayout {

    private final static int H_DISTANCE = 20;//水平间距是20
    private final static int V_DISTANCE = 20;//竖直间距是20

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void addTextView(String keys){

        //*******加载xml布局的方式********
        TextView textView = (TextView) View.inflate(getContext(),
                R.layout.flow_item,null);
        //*******加载xml布局的方式********

        //******new对象的方式新建textView**********
//        TextView textView = new TextView(getContext());
        textView.setText(keys);
//        textView.setTextSize(20);
//        //getResources加载资源，获取padding的像素大小
//        //getDimensionPixelSize会自动把sp单位转化为px单位，读取dimens.xml里面的属性
//        int padding = getResources().getDimensionPixelSize(R.dimen.flow_text_padding);
//        textView.setPadding(padding, padding, padding, padding);
//        textView.setTextColor(0xFF928374);
//        textView.setBackgroundResource(R.drawable.car_btn_bg);
        //******new对象的方式新建textView**********

        //布局宽高自适应
        LayoutParams params = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(params);//控件设置上布局参数

        addView(textView);
    }

    //效果参照：https://www.jianshu.com/p/05954091c650
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        int width = getWidth();//获取本控件的宽度,用于计算换行
        int row = 0;//行数

        int disWidth = H_DISTANCE;//子控件左边的坐标

        for (int i = 0; i <getChildCount() ; i++) {

            View view = getChildAt(i);//查找子控件

            int viewWidth = view.getWidth();
            int viewHeight = view.getHeight();
            Log.i("dt","textHeight:"+viewHeight);

            if (disWidth+viewWidth>width){//接下来的控件的右边坐标超过了屏幕宽度
                row++;//行数增加
                disWidth = H_DISTANCE;//还原左边坐标
            }

            //第一个参数是左边坐标，第二个参数是上边坐标
            //左坐标应该是每行子控件宽度的总和disWidth
            //右坐标为左坐标+子控件宽度
            //上坐标应该是行数*控件高度
            //下坐标是上坐标+控件高度=（行数+1）*控件高度
            int viewTop =row*viewHeight+row*V_DISTANCE;
            view.layout(disWidth,viewTop,
                    disWidth+viewWidth,viewTop+viewHeight);//子控件布局

            disWidth+=(viewWidth+H_DISTANCE);//记录下一次子控件左边的坐标

        }
    }
}
