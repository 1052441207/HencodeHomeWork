package com.yiyeye.hencodehomework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class ScalAbleImageView extends View implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Bitmap bitmap;

    private float originalOffsetX;
    private float originalOffsetY;

    private float bigScale,smallScale;

    private boolean isBig;

    private GestureDetector gestureDetector;


    public ScalAbleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = getBitmap(R.mipmap.icon,400);
        gestureDetector = new GestureDetector(context,this);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        originalOffsetX = (getWidth()-bitmap.getWidth())/2;
        originalOffsetY = (getHeight()-bitmap.getHeight())/2;

        if (getWidth()/getHeight() > bitmap.getWidth()/bitmap.getHeight()){
            bigScale = getWidth()/(float)bitmap.getWidth();
            smallScale = getHeight()/(float)bitmap.getHeight();
        }else {
            bigScale = getHeight()/(float)bitmap.getHeight();
            smallScale = getWidth()/(float)bitmap.getWidth();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float scale = isBig ? bigScale : smallScale;

        canvas.scale(scale,scale,getWidth()/2,getHeight()/2);

        canvas.drawBitmap(bitmap,originalOffsetX,originalOffsetY,paint);

    }



    private Bitmap getBitmap(int resId, int width){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(),resId,options);

        options.inJustDecodeBounds = false;
        options.inSampleSize = options.outWidth / width;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),resId,options);
        return bitmap;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    /**
     * 预按下的状态， 当按下100毫秒后调用此方法
     * @param motionEvent
     */
    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    /**
     * 按下的时间<500ms抬起调用此方法  单击事件
     * 当调用gestureDetector.setIsLongpressEnabled(false)方法关闭长按事件后，
     * 不管按下的事件是否大于500ms都会调用此方法
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    /**
     * 按下的时间>500ms调用此方法 长按事件
     * @param motionEvent
     */
    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }


    /**
     * 当手指在屏幕上滑动的时候调用此方法
     * @param motionEvent
     * @param motionEvent1
     * @param v
     * @param v1
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }


    /**
     * 当滑动完抬起手指后调用此方法
     * @param motionEvent
     * @param motionEvent1
     * @param v
     * @param v1
     * @return
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    /**
     * 当按下的时间大于 双击判断时间的时候
     * 在抬起手指后300ms后调用此方法
     * 注意   当设置双击监听后 必须使用此方法监听单击事件，不可以使用onSingleTapUp
     * 因为双击也会触发onSingleTapUp方法
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    /**
     * 40ms(这时会认为你是不小心抖了一下) < 第一次按下和第二次按下的时间间隔 <300ms 时调用此方法
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        isBig = !isBig;
        invalidate();
        return false;
    }

    /**
     * 当onDoubleTap被调用时也会调用此方法，但是第二次按下后的后续事件都会触发此方法，例如
     * 第二次按下后的move，up事件
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }
}
