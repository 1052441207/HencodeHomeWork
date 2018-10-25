package com.yiyeye.hencodehomework;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Created by yi on 2018/10/22.
 */

public class Utils {
    public static float dp2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, Resources.getSystem().getDisplayMetrics());
    }
}
