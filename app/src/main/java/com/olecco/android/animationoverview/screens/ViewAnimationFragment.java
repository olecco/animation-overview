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

    private final View.OnClickListener animationButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Animation animation = AnimationUtils.loadAnimation(getActivity(),
                    getAnimationResourceId(v.getId()));
            animatedView.startAnimation(animation);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_animation, container, false);
        animatedView = view.findViewById(R.id.animated_view);
        view.findViewById(R.id.translate_button).setOnClickListener(animationButtonClickListener);
        view.findViewById(R.id.rotate_button).setOnClickListener(animationButtonClickListener);
        view.findViewById(R.id.scale_button).setOnClickListener(animationButtonClickListener);
        view.findViewById(R.id.alpha_button).setOnClickListener(animationButtonClickListener);
        view.findViewById(R.id.animation_set_button).setOnClickListener(animationButtonClickListener);
        return view;
    }

    @Override
    protected String getScreenName() {
        return getString(R.string.view_animation);
    }

    private int getAnimationResourceId(int viewId) {
        switch (viewId) {
            case R.id.translate_button:
                return R.anim.translate;
            case R.id.rotate_button:
                return R.anim.rotate;
            case R.id.scale_button:
                return R.anim.scale;
            case R.id.alpha_button:
                return R.anim.alpha;
            default:
                return R.anim.animation_set;
        }
    }

}
