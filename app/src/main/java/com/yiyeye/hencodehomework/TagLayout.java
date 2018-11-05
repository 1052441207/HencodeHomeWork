package com.yiyeye.hencodehomework;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends ViewGroup {

    private List<Rect> rects = new ArrayList<>();

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthUse = 0;
        int heightUse = 0;

        int parentWidth = 0;
        int parentHeight = 0;

        int parWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int parWidthMode = MeasureSpec.getMode(widthMeasureSpec);

        for (int i = 0; i < getChildCount(); i ++){
            View view = getChildAt(i);
            measureChildWithMargins(view,widthMeasureSpec,0,heightMeasureSpec,heightUse);
            int measureWidth = view.getMeasuredWidth();
            int measureHeigh = view.getMeasuredHeight();

            heightUse = heightUse > measureHeigh ? heightUse : measureHeigh;

            Rect rect = null;
            if (rects.size() > i){
                rect =  rects.get(i);
            }else {
                rect = new Rect();
                rects.add(rect);
            }

            if (widthUse + measureWidth < parWidthSize){
                rect.set(widthUse,parentHeight,widthUse + measureWidth,parentHeight + measureHeigh);
                widthUse += measureWidth;
            } else{
                parentWidth = parentWidth > widthUse  ? parentWidth : widthUse;
                widthUse = 0;
                parentHeight += heightUse;
                rect.set(widthUse,parentHeight,widthUse + measureWidth,parentHeight + measureHeigh);
                parentHeight += measureHeigh;
            }
        }
        setMeasuredDimension(parentWidth,parentHeight);
    }

    @Override
    protected void onLayout(boolean b, int l, int t, int r, int bottom) {
        for (int i = 0; i < getChildCount(); i ++){
            View view = getChildAt(i);
            Rect rect = rects.get(i);
            view.layout(rect.left,rect.top,rect.right,rect.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
