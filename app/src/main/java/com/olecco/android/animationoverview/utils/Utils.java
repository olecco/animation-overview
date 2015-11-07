package com.olecco.android.animationoverview.utils;

import android.content.res.Resources;
import android.os.Build;

import com.olecco.android.animationoverview.R;

/**
 * Created by olecco on 31.10.2015.
 */
public class Utils {

    public static final int[] ANDROID_NAMES = { R.string.jelly_bean, R.string.kitkat,
            R.string.lollipop, R.string.marshmallow };

    public static final int[] ANDROID_INFOS = { R.string.jelly_bean_info, R.string.kitkat_info,
            R.string.lollipop_info, R.string.marshmallow_info };

    public static final int[] ANDROID_ICONS = { R.drawable.jelly_bean, R.drawable.kitkat,
            R.drawable.lollipop, R.drawable.marshmallow };

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getRandomColor() {
        int red = (int)(Math.random() * 128 + 127);
        int green = (int)(Math.random() * 128 + 127);
        int blue = (int)(Math.random() * 128 + 127);
        return 0xff << 24 | (red << 16) |
                (green << 8) | blue;
    }

}
