package com.yiyeye.hencodehomework;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yi on 2018/10/26.
 */

public class TransView extends View {

    private Bitmap bitmap;

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private Matrix matrix = new Matrix();

    private Camera camera = new Camera();

    private float topDegress = 0f;

    private float bottomDegress  = 0f;

    private float picDegress = 0f;

    public TransView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = getBitmap(R.mipmap.fei,(int)Utils.dp2px(150));
        matrix.preScale(3,3);
        matrix.preRotate(50,bitmap.getWidth()/2,bitmap.getHeight()/2);

        camera.setLocation(0,0,getResources().getDisplayMetrics().density * 5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //上半部分
        canvas.save();
        canvas.translate(getWidth()/2 ,getHeight()/2 );
        canvas.rotate(-picDegress);
        camera.save();
        camera.rotateX(topDegress);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-bitmap.getWidth(),-bitmap.getHeight(),
                bitmap.getWidth(),0);
        canvas.rotate(picDegress);
        canvas.translate(-(getWidth()/2),-(getHeight()/2));
        canvas.drawBitmap(bitmap,getWidth()/2  - bitmap.getWidth()/2,getHeight()/2 - bitmap.getHeight()/2,paint);
        canvas.restore();

        //下半部分
        canvas.save();
        canvas.translate(getWidth()/2 - bitmap.getWidth()/2 + bitmap.getWidth()/2,getHeight()/2 - bitmap.getHeight()/2 + bitmap.getHeight()/2  );
        canvas.rotate(-picDegress);
        camera.save();
        camera.rotateX(bottomDegress);
        camera.applyToCanvas(canvas);
        camera.restore();
        canvas.clipRect(-bitmap.getWidth(),0,
                bitmap.getWidth(), bitmap.getHeight());
        canvas.rotate(picDegress);
        canvas.translate(-(getWidth()/2 - bitmap.getWidth()/2 + bitmap.getWidth()/2),-(getHeight()/2 - bitmap.getHeight()/2 + bitmap.getHeight()/2 ));
        canvas.drawBitmap(bitmap,getWidth()/2 - bitmap.getWidth()/2,getHeight()/2 - bitmap.getHeight()/2,paint);
        canvas.restore();
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

    public float getTopDegress() {
        return topDegress;
    }

    public void setTopDegress(float topDegress) {
        this.topDegress = topDegress;
        invalidate();
    }

    public float getBottomDegress() {
        return bottomDegress;
    }

    public void setBottomDegress(float bottomDegress) {
        this.bottomDegress = bottomDegress;
        invalidate();
    }

    public float getPicDegress() {
        return picDegress;
    }

    public void setPicDegress(float picDegress) {
        this.picDegress = picDegress;
        invalidate();
    }
}
