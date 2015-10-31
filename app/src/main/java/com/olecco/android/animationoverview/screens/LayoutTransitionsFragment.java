package com.olecco.android.animationoverview.screens;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.olecco.android.animationoverview.R;
import com.olecco.android.animationoverview.utils.Utils;
import com.olecco.android.animationoverview.views.ColoredView;

/**
 * Created by olecco on 25.10.2015.
 */
public class LayoutTransitionsFragment extends BaseAnimationFragment {

    private LinearLayout viewContainer;
    private CheckBox setTransitionCheck;
    private CheckBox enableChangingCheck;
    private CheckBox customAnimationsCheck;

    private final View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_button:
                    onAddClick();
                    break;
                case R.id.remove_button:
                    onRemoveClick();
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_transitions, container, false);

        viewContainer = (LinearLayout) view.findViewById(R.id.view_container);

        view.findViewById(R.id.add_button).setOnClickListener(buttonClickListener);
        view.findViewById(R.id.remove_button).setOnClickListener(buttonClickListener);

        setTransitionCheck = (CheckBox) view.findViewById(R.id.set_transition_check);
        setTransitionCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                configureLayoutTransition();
            }
        });

        enableChangingCheck = (CheckBox) view.findViewById(R.id.enable_changing_check);
        enableChangingCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                configureLayoutTransition();
            }
        });

        customAnimationsCheck = (CheckBox) view.findViewById(R.id.custom_animations_check);
        customAnimationsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                configureLayoutTransition();
            }
        });


        return view;
    }

    private void configureLayoutTransition() {
        viewContainer.setLayoutTransition(
                setTransitionCheck.isChecked() ? new LayoutTransition() : null);

        if (viewContainer.getLayoutTransition() != null) {
            if (enableChangingCheck.isChecked()) {
                viewContainer.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
            } else {
                viewContainer.getLayoutTransition().disableTransitionType(LayoutTransition.CHANGING);
            }

            if (customAnimationsCheck.isChecked()) {
                setupCustomAnimations();
            }
        }
    }

    private void onAddClick() {
        View coloredView = new ColoredView(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, Utils.dpToPx(4), 0, 0);
        viewContainer.addView(coloredView, 1, lp);
    }

    private void onRemoveClick() {
        if (viewContainer.getChildCount() > 2) {
            viewContainer.removeViewAt(1);
        }
    }

    private void setupCustomAnimations() {
        LayoutTransition transition = viewContainer.getLayoutTransition();

//        // Changing while Adding
//        PropertyValuesHolder pvhLeft =
//                PropertyValuesHolder.ofInt("left", 0, 1);
//        PropertyValuesHolder pvhTop =
//                PropertyValuesHolder.ofInt("top", 0, 1);
//        PropertyValuesHolder pvhRight =
//                PropertyValuesHolder.ofInt("right", 0, 1);
//        PropertyValuesHolder pvhBottom =
//                PropertyValuesHolder.ofInt("bottom", 0, 1);
//        PropertyValuesHolder pvhScaleX =
//                PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f);
//        PropertyValuesHolder pvhScaleY =
//                PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f);
//        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
//                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX, pvhScaleY).
//                setDuration(transition.getDuration(LayoutTransition.CHANGE_APPEARING));
//        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);
//        changeIn.addListener(new AnimatorListenerAdapter() {
//            public void onAnimationEnd(Animator anim) {
//                View view = (View) ((ObjectAnimator) anim).getTarget();
//                view.setScaleX(1f);
//                view.setScaleY(1f);
//            }
//        });
//        // Changing while Removing
//        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
//        Keyframe kf1 = Keyframe.ofFloat(.9999f, 360f);
//        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
//        PropertyValuesHolder pvhRotation =
//                PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
//        final ObjectAnimator changeOut = ObjectAnimator.ofPropertyValuesHolder(
//                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhRotation).
//                setDuration(transition.getDuration(LayoutTransition.CHANGE_DISAPPEARING));
//        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeOut);
//        changeOut.addListener(new AnimatorListenerAdapter() {
//            public void onAnimationEnd(Animator anim) {
//                View view = (View) ((ObjectAnimator) anim).getTarget();
//                view.setRotation(0f);
//            }
//        });

        // Adding
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "rotationY", 90f, 0f);
        animIn.setDuration(transition.getDuration(LayoutTransition.APPEARING));
        animIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                view.setRotationY(0f);
            }
        });
        transition.setAnimator(LayoutTransition.APPEARING, animIn);


//        // Removing
//        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotationX", 0f, 90f).
//                setDuration(transition.getDuration(LayoutTransition.DISAPPEARING));
//        transition.setAnimator(LayoutTransition.DISAPPEARING, animOut);
//        animOut.addListener(new AnimatorListenerAdapter() {
//            public void onAnimationEnd(Animator anim) {
//                View view = (View) ((ObjectAnimator) anim).getTarget();
//                view.setRotationX(0f);
//            }
//        });
    }

    @Override
    protected String getScreenName() {
        return getResources().getString(R.string.layout_transitions);
    }
}
