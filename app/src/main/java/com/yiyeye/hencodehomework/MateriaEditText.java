package com.yiyeye.hencodehomework;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

public class MateriaEditText extends android.support.v7.widget.AppCompatEditText {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float hintTextSize = Utils.dp2px(20);

    private float offsetY = Utils.dp2px(20);

    public MateriaEditText(Context context) {
        this(context,null);
    }

    public MateriaEditText(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MateriaEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
       // setPadding((int) Utils.dp2px(20),(int)hintTextSize,0,(int)hintTextSize);
    }

    {
        paint.setColor(Color.BLUE);
        paint.setTextSize(hintTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String hintText = getHint().toString();
        //canvas.drawText(hintText,0,offsetY,paint);
    }
}
