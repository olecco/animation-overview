package com.olecco.android.animationoverview.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.olecco.android.animationoverview.utils.Utils;

/**
 * Created by olecco on 31.10.2015.
 */
public class ColoredView extends View {

    private boolean expanded;

    private static final int EXPANDED_HEIGHT_DP = 80;
    private static final int COLLAPSED_HEIGHT_DP = 30;

    public ColoredView(Context context) {
        this(context, null);
    }

    public ColoredView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColoredView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Utils.getRandomColor());

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                expanded = !expanded;
                requestLayout();
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = expanded ? Utils.dpToPx(EXPANDED_HEIGHT_DP) :
                Utils.dpToPx(COLLAPSED_HEIGHT_DP);

        setMeasuredDimension(width, height);
    }

}