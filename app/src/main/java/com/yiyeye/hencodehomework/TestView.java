package com.yiyeye.hencodehomework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by yi on 2018/10/25.
 */

public class TestView extends View{

    private String text = "sadrefcgg";

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Rect rect = new Rect();

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setTextSize(Utils.dp2px(150));
        paint.getTextBounds(text,0,text.length(),rect);
        Log.i("main",rect.left + "");
        canvas.drawText(text,0 - rect.left,500,paint);

        float spacing = paint.getLetterSpacing();
        paint.setTextSize(Utils.dp2px(20));
        canvas.drawText(text,0,500 + 100,paint);
    }
}
