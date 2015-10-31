package com.olecco.android.animationoverview.screens;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
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
    private CompoundButton setTransitionCheck;
    private CompoundButton enableChangingCheck;
    private CompoundButton customAnimationsCheck;

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

    private final CompoundButton.OnCheckedChangeListener checkedChangeListener =
            new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            configureLayoutTransition();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_transitions, container, false);

        viewContainer = (LinearLayout) view.findViewById(R.id.view_container);

        view.findViewById(R.id.add_button).setOnClickListener(buttonClickListener);
        view.findViewById(R.id.remove_button).setOnClickListener(buttonClickListener);

        setTransitionCheck = (CompoundButton) view.findViewById(R.id.set_transition_check);
        setTransitionCheck.setOnCheckedChangeListener(checkedChangeListener);

        enableChangingCheck = (CompoundButton) view.findViewById(R.id.enable_changing_check);
        enableChangingCheck.setOnCheckedChangeListener(checkedChangeListener);

        customAnimationsCheck = (CompoundButton) view.findViewById(R.id.custom_animations_check);
        customAnimationsCheck.setOnCheckedChangeListener(checkedChangeListener);

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

        // APPEARING
        PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofFloat("scaleX", 0.0f, 1.0f);
        PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat("scaleY", 0.0f, 1.0f);
        ObjectAnimator animAdd = ObjectAnimator.ofPropertyValuesHolder(this, scaleXHolder, scaleYHolder);
        animAdd.setDuration(transition.getDuration(LayoutTransition.APPEARING));
        transition.setAnimator(LayoutTransition.APPEARING, animAdd);

        // DISAPPEARING
        scaleXHolder = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.0f);
        scaleYHolder = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.0f);
        ObjectAnimator animRemove = ObjectAnimator.ofPropertyValuesHolder(this, scaleXHolder, scaleYHolder);
        animAdd.setDuration(transition.getDuration(LayoutTransition.DISAPPEARING));
        transition.setAnimator(LayoutTransition.DISAPPEARING, animRemove);

        // CHANGE_APPEARING
        Animator animator = transition.getAnimator(LayoutTransition.CHANGE_APPEARING);
        animator.setInterpolator(new OvershootInterpolator());

        // CHANGE_DISAPPEARING
        transition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 400);
    }

    @Override
    protected String getScreenName() {
        return getResources().getString(R.string.layout_transitions);
    }
}
