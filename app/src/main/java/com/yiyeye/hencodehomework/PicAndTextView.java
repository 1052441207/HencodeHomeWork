package com.yiyeye.hencodehomework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yi on 2018/10/25.
 */

public class PicAndTextView extends View {

    private String text = "a person who was born in a particular place,a person who was born in a particular place,a person who was born in a particular place,a person who was born in a particular place,a person who was born in a particular place,a person who was born in a particular place,a person who was born in a particular place,\n" +
            "English is not the native language for almost half of our overseas visitors.我们有差不多一半的海外游客母语不是英语。";

    private Bitmap bitmap;

    private Paint paint;

    private  float[] measuredWidth={1};

    public PicAndTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(Utils.dp2px(20));
        paint.setColor(Color.RED);
        bitmap = getBitmap(R.mipmap.icon,200);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,getWidth()/2 - 200,getHeight()/2 - 200,paint);

        int start = 0;


        int end = paint.breakText(text,start,text.length(),true,getWidth(),measuredWidth);
        canvas.drawText(text,start,end,0,80,paint);
        start = end;

        end = paint.breakText(text,start,text.length(),true,getWidth(),measuredWidth);
        canvas.drawText(text,start,end,0,80 + paint.getLetterSpacing(),paint);
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
