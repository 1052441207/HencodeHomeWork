package com.yiyeye.hencodehomework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yi on 2018/10/22.
 */

public class DashView extends View {
    //弧的半径
    private static final float RADIUS = Utils.dp2px(100);

    private static final float POINTER = Utils.dp2px(80);
    //夹角
    private int angle = 60;

    private Paint paint;

    private RectF rectF;

    private Path arcPath = new Path();

    private Path dashPath = new Path();

    private PathMeasure pathMeasure;

    private PathDashPathEffect pathDashPathEffect;

    private int curDash = 0;

    public DashView(Context context) {
        this(context,null);
    }

    public DashView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        rectF = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);


        arcPath.addArc(
                getWidth()/2 - RADIUS,
                getHeight()/2-RADIUS,
                getWidth()/2 + RADIUS,
                getHeight()/2+ RADIUS,
                120f,300f);
        pathMeasure = new PathMeasure(arcPath,false);
        dashPath.addRect( 0,0,Utils.dp2px(2),Utils.dp2px(10), Path.Direction.CW);
        pathDashPathEffect = new PathDashPathEffect(dashPath,(pathMeasure.getLength() - Utils.dp2px(2))/20,0, PathDashPathEffect.Style.ROTATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画一个弧
        rectF.set(getWidth()/2 - RADIUS,getHeight()/2-RADIUS,getWidth()/2 + RADIUS,getHeight()/2 + RADIUS);
        canvas.drawArc(rectF,120,300,false,paint);

        //画刻度
        paint.setPathEffect(pathDashPathEffect);
        canvas.drawPath(arcPath,paint);

        //画表针
        paint.setPathEffect(null);
        paint.setColor(Color.RED);
        canvas.drawLine(getWidth()/2,getHeight()/2,getWidth()/2 + (float) (Math.cos(Math.toRadians(getAngleFromDash(curDash))) * POINTER),
               getHeight()/2 + (float) (Math.sin(Math.toRadians(getAngleFromDash(curDash))) * POINTER),paint);

    }


    public float getAngleFromDash(int curDash) {
        return (120 + (300/20) * curDash);
    }

    public void setCurDash(int curDash) {
        this.curDash = curDash;
        invalidate();
    }
}
