package com.yiyeye.hencodehomework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yi on 2018/10/23.
 */

public class ArcView extends View {
    //弧的半径
    private static final float RADIUS = Utils.dp2px(100);

    private RectF rectF;

    private Paint paint;

    private int []colors = {Color.BLUE,Color.GREEN,Color.RED,Color.YELLOW};

    private float[]angle = {0,90,180,270};

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        rectF = new RectF();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.set(getWidth()/2 - RADIUS, getHeight()/2 - RADIUS,
                getWidth()/2 + RADIUS, getHeight()/2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < angle.length; i++){
            paint.setColor(colors[i]);

            if (i == 0){
                int saveId = canvas.save();
                canvas.translate(100,100);
                canvas.drawArc(rectF,angle[i],90,true,paint);
                canvas.restoreToCount(saveId);
            }else {
                canvas.drawArc(rectF,angle[i],90,true,paint);
            }

        }
    }
}
