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

public class SportView extends View {

    private String text = "aaaaa";

    private Paint paint;

    private Rect rect = new Rect();

    private Paint.FontMetrics metrics;

    public SportView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(5));
        paint.setTextSize(Utils.dp2px(20));
        paint.setColor(Color.RED);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setTextAlign(Paint.Align.CENTER);

        metrics =  paint.getFontMetrics();
        paint.getTextBounds(text,0,text.length(),rect);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画外部圆
        paint.setColor(Color.RED);
        canvas.drawCircle(getWidth()/2,getHeight()/2,Utils.dp2px(50),paint);

        paint.setColor(Color.GREEN);
        canvas.drawArc(getWidth()/2-Utils.dp2px(50),getHeight()/2-Utils.dp2px(50),
                getWidth()/2+Utils.dp2px(50),getHeight()/2+Utils.dp2px(50),0,90,false,paint);


        Log.i("main","top" + rect.top + "-----bottom" + rect.bottom);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text,getWidth()/2,getHeight()/2 - (metrics.ascent + metrics.descent)/2,paint);
    }
}
