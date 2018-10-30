package com.yiyeye.hencodehomework;

import android.content.Context;
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

    public TransView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = getBitmap(R.mipmap.icon,200);
        matrix.preScale(3,3);
        matrix.preRotate(50,bitmap.getWidth()/2,bitmap.getHeight()/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap,0,0,paint);

        canvas.clipRect( 0,0,)
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
}
