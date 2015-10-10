package com.olecco.android.animationoverview.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olecco.android.animationoverview.R;

/**
 * Created by olecco on 10.10.2015.
 */
public class ViewAnimationFragment extends BaseAnimationFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_animation, container, false);

        return view;
    }

    @Override
    protected String getScreenName() {
        return getString(R.string.view_animation);
    }
}
