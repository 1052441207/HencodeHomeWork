package com.yiyeye.hencodehomework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Random;

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    private int index = 0;

    private int colorArray[] = {
            Color.BLACK,
            Color.BLUE,
            Color.RED,
            Color.YELLOW,
            Color.RED,
            Color.GREEN,
            Color.YELLOW,
            Color.RED,
            Color.GREEN,
            Color.YELLOW,
            Color.RED,
            Color.GREEN,
            Color.YELLOW,
            Color.RED,
            Color.GREEN

    };


    private String[] strings= {
            "是弹道式导弹","颠三倒四","dsdsd","dsdddd",
            "是弹道式导弹","颠三倒四","是导弹","颠三倒四","式导弹","倒四","是弹弹","颠三四","是弹道式导弹","颠三倒四",
            "dddd"
    };

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        Random random = new Random();
        index =  random.nextInt(colorArray.length);
        setText(strings[index]);
        setBackgroundColor(colorArray[index]);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


}
