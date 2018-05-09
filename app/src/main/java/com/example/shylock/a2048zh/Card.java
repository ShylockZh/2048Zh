package com.example.shylock.a2048zh;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Shylock on 2018/5/8.
 */

public class Card extends FrameLayout {
    public Card(Context context) {
        super(context);

        label = new TextView(getContext());
        label.setTextSize(32);
        label.setBackgroundColor(0x33ffffff);
        label.setGravity(Gravity.CENTER);

        LayoutParams lp = new LayoutParams(-1, -1);//填满父元素
        lp.setMargins(10, 10, 0, 0);
        addView(label,lp);

        setNum(0);
    }

    private int num = 0;

    public int getNum() {
        return num;
    }//获取数字

    public void setNum(int num) {
        this.num = num;

        label.setText(num+"");//转换成字符串
    }

    public boolean equals(Card o){
        return getNum()==o.getNum();
    }

    private TextView label;
}
