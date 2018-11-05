package com.yiyeye.hencodehomework;

import android.animation.AnimatorSet;
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

    private int alphaValue;

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

                if (charSequence.length() > 0){
                    if (isFirstChange){
                        showAlert();
                        isFirstChange = false;
                    }
                }else {
                    dismissAlert();
                    isFirstChange = true;
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
        paint.setAlpha(alphaValue);
        String hintText =  getHint().toString();
        canvas.drawText(hintText,0,offsetY,paint);
    }


    private ObjectAnimator showAnimator,dismissAnimator;
    private ObjectAnimator showAlphaAnimator,dismissAlphaAnimator;

    private void showAlert(){
        if (showAnimator == null){
            showAnimator = ObjectAnimator.ofFloat(this,"offsetY",getHeight() - alertTextSize - getPaddingBottom(),(alertTextSize + Utils.dp2px(5)));
        }

        if (showAlphaAnimator == null){
            showAlphaAnimator = ObjectAnimator.ofInt(this,"alphaValue",0,100);
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.playTogether(showAnimator,showAlphaAnimator);
        animatorSet.start();
    }

    private void dismissAlert(){
        if (dismissAnimator == null){
            dismissAnimator = ObjectAnimator.ofFloat(this,"offsetY",(alertTextSize + Utils.dp2px(5)),getHeight() - alertTextSize - getPaddingBottom());
            dismissAnimator.setDuration(2000);
        }

        if (dismissAlphaAnimator == null){
            dismissAlphaAnimator = ObjectAnimator.ofInt(this,"alphaValue",100,0);
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.playTogether(dismissAnimator,dismissAlphaAnimator);
        animatorSet.start();
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
        invalidate();
    }

    public int getAlphaValue() {
        return alphaValue;
    }

    public void setAlphaValue(int alphaValue) {
        this.alphaValue = alphaValue;
    }
}
