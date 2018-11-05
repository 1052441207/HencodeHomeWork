package com.yiyeye.hencodehomework;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class LabelLayout extends ViewGroup {

    public LabelLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int lineMaxHeight =0;
        int maxWidth;

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

            int widthMargin = marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + widthUse;

            int childWidthSize = 0;
            int childWidthMode = 0;

            //根据子view的layout_ 属性和父view的可用空间计算出子view的measurSpec
            switch (widthMode){
                case MeasureSpec.EXACTLY:
                    if (marginLayoutParams.width >= 0){
                        childWidthSize = marginLayoutParams.width;
                        childWidthMode = MeasureSpec.EXACTLY;
                    }else if (marginLayoutParams.width == LayoutParams.MATCH_PARENT){
                        childWidthSize = widthSize - widthMargin;
                        childWidthMode = MeasureSpec.EXACTLY;
                    }else if (marginLayoutParams.width == LayoutParams.WRAP_CONTENT){
                        childWidthSize = marginLayoutParams.width;
                        childWidthMode = MeasureSpec.AT_MOST;
                    }
                    break;

                //父view指定了一个最大的尺寸
                case MeasureSpec.AT_MOST:
                    if (marginLayoutParams.width >= 0){
                        childWidthSize = marginLayoutParams.width;
                        childWidthMode = MeasureSpec.EXACTLY;
                    }else if (marginLayoutParams.width == LayoutParams.MATCH_PARENT){
                        childWidthSize = widthSize - widthMargin;
                        childWidthMode = MeasureSpec.AT_MOST;
                    }else {
                        childWidthSize = widthSize - widthMargin;
                        childWidthMode = MeasureSpec.AT_MOST;
                    }
                    break;
                case MeasureSpec.UNSPECIFIED:
                    if (marginLayoutParams.width >= 0){
                        childWidthSize = marginLayoutParams.width;
                        childWidthMode = MeasureSpec.EXACTLY;
                    }else if (marginLayoutParams.width == LayoutParams.MATCH_PARENT){
                        childWidthSize = 0;
                        childWidthMode = MeasureSpec.UNSPECIFIED;
                    }else if (marginLayoutParams.width == LayoutParams.WRAP_CONTENT){
                        childWidthSize = 0;
                        childWidthMode = MeasureSpec.UNSPECIFIED;
                    }
                    break;
            }


            int heightMargin = marginLayoutParams.bottomMargin + marginLayoutParams.topMargin + heightUse;

            int childHeightSize =0;
            int childHeightMode =0;

            switch (heightMode){
                case MeasureSpec.EXACTLY:
                    if (marginLayoutParams.height >= 0){
                        childHeightSize = marginLayoutParams.height;
                        childHeightMode =  MeasureSpec.EXACTLY;
                    }else if (marginLayoutParams.height == LayoutParams.MATCH_PARENT){
                        childHeightSize = heightSize - heightMargin;
                        childHeightMode =  MeasureSpec.EXACTLY;
                    }else if (marginLayoutParams.height == LayoutParams.WRAP_CONTENT){
                        childHeightSize =  heightSize - heightMargin;
                        childHeightMode =  MeasureSpec.AT_MOST;
                    }
                    break;
                case MeasureSpec.AT_MOST:
                    if (marginLayoutParams.height >= 0){
                        childHeightSize = marginLayoutParams.height;
                        childHeightMode =  MeasureSpec.EXACTLY;
                    }else if (marginLayoutParams.height == LayoutParams.MATCH_PARENT){
                        childHeightSize = heightSize - heightMargin;
                        childHeightMode = MeasureSpec.AT_MOST;
                    }else if (marginLayoutParams.height == LayoutParams.WRAP_CONTENT){
                        childHeightSize = heightSize - heightMargin;
                        childHeightMode = MeasureSpec.AT_MOST;
                    }
                    break;
                case MeasureSpec.UNSPECIFIED:
                    if (marginLayoutParams.height >= 0){
                        childHeightSize = marginLayoutParams.height;
                        childHeightMode = MeasureSpec.EXACTLY;
                    }else if (marginLayoutParams.height == LayoutParams.MATCH_PARENT){
                        childHeightSize = 0;
                        childHeightMode = MeasureSpec.UNSPECIFIED;
                    }else if (marginLayoutParams.width == LayoutParams.WRAP_CONTENT){
                        childHeightSize = 0;
                        childHeightMode = MeasureSpec.UNSPECIFIED;
                    }
                    break;
            }

            view.measure(
                    MeasureSpec.makeMeasureSpec(childWidthSize,childWidthMode),
                    MeasureSpec.makeMeasureSpec(childHeightSize,childHeightMode)
            );


            lineMaxHeight = lineMaxHeight > view.getMeasuredHeight() ? lineMaxHeight : view.getMeasuredHeight();

            if (widthUse + view.getMeasuredWidth() > getMeasuredWidth()){
                widthUse = 0;
                heightUse += lineMaxHeight;
            }else {
                widthUse += view.getMeasuredWidth();
            }
        }

        //setMeasuredDimension();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
