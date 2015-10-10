package com.olecco.android.animationoverview.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.olecco.android.animationoverview.R;

/**
 * Created by olecco on 10.10.2015.
 */
public class ViewAnimationFragment extends BaseAnimationFragment {

    private View animatedView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_animation, container, false);
        animatedView = view.findViewById(R.id.animated_view);
        return view;
    }

    @Override
    protected String getScreenName() {
        return getString(R.string.view_animation);
    }

    @Override
    protected void onPlayButtonClick() {
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.view_animation);
        animatedView.startAnimation(hyperspaceJumpAnimation);
    }
}
