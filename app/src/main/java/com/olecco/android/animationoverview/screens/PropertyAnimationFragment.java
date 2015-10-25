package com.olecco.android.animationoverview.screens;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.olecco.android.animationoverview.R;

/**
 * Created by olecco on 24.10.2015.
 */
public class PropertyAnimationFragment extends BaseAnimationFragment {

    private View animatedView;
    private View animatedArea;
    private int animationDuration;

    private final View.OnClickListener animationButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.translation_button:
                    onTranslationClick();
                    break;
                case R.id.rotation_button:
                    onRotationClick();
                    break;
                case R.id.scale_button:
                    onScaleClick();
                    break;
                case R.id.animator_set_button:
                    onAnimatorSetClick();
                    break;
                case R.id.color_button:
                    onColorClick();
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animationDuration = getResources().getInteger(R.integer.property_animation_duration);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.property_animation, container, false);
        animatedView = view.findViewById(R.id.animated_view);
        animatedArea = view.findViewById(R.id.animated_area);
        view.findViewById(R.id.translation_button).setOnClickListener(animationButtonClickListener);
        view.findViewById(R.id.rotation_button).setOnClickListener(animationButtonClickListener);
        view.findViewById(R.id.scale_button).setOnClickListener(animationButtonClickListener);
        view.findViewById(R.id.animator_set_button).setOnClickListener(animationButtonClickListener);
        view.findViewById(R.id.color_button).setOnClickListener(animationButtonClickListener);
        return view;
    }

    @Override
    protected String getScreenName() {
        return getString(R.string.property_animation);
    }

    private void onTranslationClick() {
        // inflating animator from XML
        Animator animator = AnimatorInflater.loadAnimator(getActivity(), R.animator.translation);
        animator.setTarget(animatedView);
        animator.start();
    }

    private void onRotationClick() {
        // two ObjectAnimators
        Animator rotationAnimator = ObjectAnimator.ofFloat(animatedView, "rotation", 360.0f);
        Animator rotationYAnimator = ObjectAnimator.ofFloat(animatedView, "rotationY", 45.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(animationDuration);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(rotationAnimator, rotationYAnimator);
        animatorSet.start();
    }

    private void onScaleClick() {
        // single ObjectAnimator and two PropertyValuesHolders
        PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofFloat("scaleX", 1.5f);
        PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat("scaleY", 1.5f);
        Animator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(
                animatedView, scaleXHolder, scaleYHolder);
        scaleAnimator.setDuration(animationDuration);
        scaleAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleAnimator.start();
    }

    private void onAnimatorSetClick() {
        // single ViewPropertyAnimator
        animatedView.animate()
                .translationX(0.0f)
                .translationY(0.0f)
                .translationZ(0.0f)
                .rotation(0.0f)
                .rotationX(0.0f)
                .rotationY(0.0f)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setDuration(animationDuration)
                .setInterpolator(new AccelerateDecelerateInterpolator());
    }

    private void onColorClick() {
        // example of ValueAnimator and TypeEvaluator implementation
        Integer colorFrom = getResources().getColor(R.color.animated_color_from);
        Integer colorTo = getResources().getColor(R.color.animated_color_to);
        ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimator.setDuration(animationDuration);
        colorAnimator.setRepeatCount(1);
        colorAnimator.setRepeatMode(ValueAnimator.REVERSE);
        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                animatedArea.setBackgroundColor((Integer)animator.getAnimatedValue());
            }

        });
        colorAnimator.start();
    }
}