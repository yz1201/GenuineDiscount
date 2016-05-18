package com.example.tyz1201.mytablayout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by tyz1201 on 2016/5/10.
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //反向倾斜度45,上下左右居中
        canvas.rotate(315, getMeasuredWidth() / 3, getMeasuredHeight() / 3);
        super.onDraw(canvas);
    }

}

