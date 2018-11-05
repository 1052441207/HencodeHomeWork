package com.yiyeye.hencodehomework;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class LabelLayout extends ViewGroup {

    private List<Rect> rects = new ArrayList<>();

    public LabelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int lineMaxHeight =0;
        int parentWidth = 0;
        int parentHeight = 0;

        int widthUse = 0;
        int heightUse = 0;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        //遍历所有的子view
        for (int i = 0;i<getChildCount();i++){
            View view = getChildAt(i);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) view.getLayoutParams();

            int widthMargin = marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;

            int childWidthSize = 0;
            int childWidthMode = 0;

//            //根据子view的layout_ 属性和父view的可用空间计算出子view的measurSpec
//            switch (widthMode){
//                case MeasureSpec.EXACTLY:
//                    if (marginLayoutParams.width >= 0){
//                        childWidthSize = marginLayoutParams.width;
//                        childWidthMode = MeasureSpec.EXACTLY;
//                    }else if (marginLayoutParams.width == LayoutParams.MATCH_PARENT){
//                        childWidthSize = widthSize - widthMargin;
//                        childWidthMode = MeasureSpec.EXACTLY;
//                    }else if (marginLayoutParams.width == LayoutParams.WRAP_CONTENT){
//                        childWidthSize = marginLayoutParams.width;
//                        childWidthMode = MeasureSpec.AT_MOST;
//                    }
//                    break;
//
//                //父view指定了一个最大的尺寸
//                case MeasureSpec.AT_MOST:
//                    if (marginLayoutParams.width >= 0){
//                        childWidthSize = marginLayoutParams.width;
//                        childWidthMode = MeasureSpec.EXACTLY;
//                    }else if (marginLayoutParams.width == LayoutParams.MATCH_PARENT){
//                        childWidthSize = widthSize - widthMargin;
//                        childWidthMode = MeasureSpec.AT_MOST;
//                    }else {
//                        childWidthSize = widthSize - widthMargin;
//                        childWidthMode = MeasureSpec.AT_MOST;
//                    }
//                    break;
//                case MeasureSpec.UNSPECIFIED:
//                    if (marginLayoutParams.width >= 0){
//                        childWidthSize = marginLayoutParams.width;
//                        childWidthMode = MeasureSpec.EXACTLY;
//                    }else if (marginLayoutParams.width == LayoutParams.MATCH_PARENT){
//                        childWidthSize = 0;
//                        childWidthMode = MeasureSpec.UNSPECIFIED;
//                    }else if (marginLayoutParams.width == LayoutParams.WRAP_CONTENT){
//                        childWidthSize = 0;
//                        childWidthMode = MeasureSpec.UNSPECIFIED;
//                    }
//                    break;
//            }
//
//
//            int heightMargin = marginLayoutParams.bottomMargin + marginLayoutParams.topMargin + heightUse;
//
//            int childHeightSize =0;
//            int childHeightMode =0;
//
//            switch (heightMode){
//                case MeasureSpec.EXACTLY:
//                    if (marginLayoutParams.height >= 0){
//                        childHeightSize = marginLayoutParams.height;
//                        childHeightMode =  MeasureSpec.EXACTLY;
//                    }else if (marginLayoutParams.height == LayoutParams.MATCH_PARENT){
//                        childHeightSize = heightSize - heightMargin;
//                        childHeightMode =  MeasureSpec.EXACTLY;
//                    }else if (marginLayoutParams.height == LayoutParams.WRAP_CONTENT){
//                        childHeightSize =  heightSize - heightMargin;
//                        childHeightMode =  MeasureSpec.AT_MOST;
//                    }
//                    break;
//                case MeasureSpec.AT_MOST:
//                    if (marginLayoutParams.height >= 0){
//                        childHeightSize = marginLayoutParams.height;
//                        childHeightMode =  MeasureSpec.EXACTLY;
//                    }else if (marginLayoutParams.height == LayoutParams.MATCH_PARENT){
//                        childHeightSize = heightSize - heightMargin;
//                        childHeightMode = MeasureSpec.AT_MOST;
//                    }else if (marginLayoutParams.height == LayoutParams.WRAP_CONTENT){
//                        childHeightSize = heightSize - heightMargin;
//                        childHeightMode = MeasureSpec.AT_MOST;
//                    }
//                    break;
//                case MeasureSpec.UNSPECIFIED:
//                    if (marginLayoutParams.height >= 0){
//                        childHeightSize = marginLayoutParams.height;
//                        childHeightMode = MeasureSpec.EXACTLY;
//                    }else if (marginLayoutParams.height == LayoutParams.MATCH_PARENT){
//                        childHeightSize = 0;
//                        childHeightMode = MeasureSpec.UNSPECIFIED;
//                    }else if (marginLayoutParams.width == LayoutParams.WRAP_CONTENT){
//                        childHeightSize = 0;
//                        childHeightMode = MeasureSpec.UNSPECIFIED;
//                    }
//                    break;
//            }
//
//            view.measure(
//                    MeasureSpec.makeMeasureSpec(childWidthSize,childWidthMode),
//                    MeasureSpec.makeMeasureSpec(childHeightSize,childHeightMode)
//            );


            measureChildWithMargins(view,widthMeasureSpec,0,heightMeasureSpec,heightUse);


            lineMaxHeight = lineMaxHeight > view.getMeasuredHeight() ? lineMaxHeight : view.getMeasuredHeight();

            Rect rect;
            if (rects.size() > i){
                rect = rects.get(i);
            }else {
                rect = new Rect();
                rects.add(rect);
            }

            if (widthUse + view.getMeasuredWidth() > widthSize){
                widthUse = 0;
                heightUse += lineMaxHeight;
                rect.set(widthUse,heightUse,widthUse + view.getMeasuredWidth(),heightUse + view.getMeasuredHeight());
            }else {
                rect.set(widthUse,heightUse,widthUse + view.getMeasuredWidth(),heightUse + view.getMeasuredHeight());
                widthUse += view.getMeasuredWidth();
            }

            parentWidth =  parentWidth > widthUse ? parentWidth : widthUse;
            parentHeight = heightUse + view.getMeasuredHeight();


        }

        setMeasuredDimension(parentWidth,parentHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i ++ ){
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
