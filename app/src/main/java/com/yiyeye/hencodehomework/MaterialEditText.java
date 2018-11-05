package com.yiyeye.hencodehomework;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

public class MaterialEditText extends android.support.v7.widget.AppCompatEditText {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float alertTextSize = Utils.dp2px(15);

    private float offsetY;

    private String content;

    private boolean isFirstChange = true;

    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPadding(0, (int) (alertTextSize + Utils.dp2px(10)),0,(int) alertTextSize);
    }

    {
        paint.setColor(Color.RED);
        paint.setTextSize(alertTextSize);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                content = charSequence.toString();
                if (!TextUtils.isEmpty(content)){
                    showAlert();
                }else {
                    dismissAlert();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String hintText =  getHint().toString();
        canvas.drawText(hintText,0,offsetY,paint);
    }


    private ObjectAnimator showAnimator,dismissAnimator;

    private void showAlert(){
        if (showAnimator == null){
            showAnimator = ObjectAnimator.ofFloat(this,"offsetY",getHeight(),(alertTextSize + Utils.dp2px(5)));
            showAnimator.setDuration(2000);
        }
        showAnimator.start();
    }

    private void dismissAlert(){
        if (dismissAnimator == null){
            dismissAnimator = ObjectAnimator.ofFloat(this,"offsetY",(alertTextSize + Utils.dp2px(5)),getHeight());
            dismissAnimator.setDuration(2000);
        }
        dismissAnimator.start();
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }
}
