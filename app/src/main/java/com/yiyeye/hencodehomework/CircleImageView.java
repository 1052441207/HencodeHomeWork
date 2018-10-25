package com.yiyeye.hencodehomework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yi on 2018/10/23.
 */

public class CircleImageView extends View {

    private Bitmap bitmap;

    private Paint paint;

    private RectF rectF = new RectF();

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = getBitmap(R.mipmap.icon,200);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth()/2,getHeight()/2,bitmap.getWidth()/2,paint);
        rectF.set(getWidth()/2 - bitmap.getWidth()/2 ,
                getHeight()/2 - bitmap.getWidth()/2,
                getWidth()/2 + bitmap.getWidth()/2,
                getHeight()/2 + bitmap.getWidth()/2);
        int layerID = canvas.saveLayer(0,0,getWidth(),getHeight(),paint,Canvas.ALL_SAVE_FLAG);
        //canvas.drawRect(rectF,paint);
        canvas.drawCircle(getWidth()/2,getHeight()/2,bitmap.getWidth()/2 - 20 ,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,getWidth()/2 - bitmap.getWidth()/2,getHeight()/2 - bitmap.getWidth()/2,paint);
        paint.setXfermode(null);
        canvas.restoreToCount(layerID);
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
