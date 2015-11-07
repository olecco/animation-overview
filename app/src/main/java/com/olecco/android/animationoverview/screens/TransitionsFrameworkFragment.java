package com.olecco.android.animationoverview.screens;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.olecco.android.animationoverview.R;
import com.olecco.android.animationoverview.utils.Utils;

/**
 * Created by olecco on 01.11.2015.
 */
public class TransitionsFrameworkFragment extends BaseAnimationFragment {

    private ViewGroup sceneRoot;

    private final View.OnClickListener sceneButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.scene_1_button:
                    onScene1Click();
                    break;
                case R.id.scene_2_button:
                    onScene2Click();
                    break;
                case R.id.scene_3_button:
                    onScene3Click();
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transitions_framework, container, false);
        sceneRoot = (ViewGroup) view.findViewById(R.id.scene_root);
        view.findViewById(R.id.scene_1_button).setOnClickListener(sceneButtonClickListener);
        view.findViewById(R.id.scene_2_button).setOnClickListener(sceneButtonClickListener);
        view.findViewById(R.id.scene_3_button).setOnClickListener(sceneButtonClickListener);
        return view;
    }

    private void onScene1Click() {
        Scene scene1 = Scene.getSceneForLayout(sceneRoot, R.layout.scene1, getActivity());

        TransitionSet transition = new TransitionSet();
        transition.setOrdering(TransitionSet.ORDERING_SEQUENTIAL);

        Fade fade = new Fade(Fade.OUT);
        fade.addTarget(R.id.item5);
        fade.addTarget(R.id.item6);

        transition.addTransition(fade);
        transition.addTransition(new ChangeBounds());

        TransitionSet buttonsTransition = new TransitionSet();

        Slide slideLeft = new Slide();
        slideLeft.setSlideEdge(Gravity.START);
        slideLeft.addTarget(R.id.item3);
        slideLeft.setInterpolator(new AccelerateDecelerateInterpolator());
        buttonsTransition.addTransition(slideLeft);

        Slide slideRight = new Slide();
        slideRight.setSlideEdge(Gravity.END);
        slideRight.addTarget(R.id.item4);
        slideRight.setInterpolator(new AccelerateDecelerateInterpolator());
        buttonsTransition.addTransition(slideRight);

        transition.addTransition(buttonsTransition);

        TransitionManager.go(scene1, transition);
    }

    private void onScene2Click() {
        Scene scene2 = Scene.getSceneForLayout(sceneRoot, R.layout.scene2, getActivity());

        TransitionInflater transitionInflater = TransitionInflater.from(getActivity());
        Transition transition = transitionInflater.inflateTransition(R.transition.scene2_transition);

        TransitionManager.go(scene2, transition);
    }

    private void onScene3Click() {
        TransitionManager.beginDelayedTransition(sceneRoot);

        View item5 = sceneRoot.findViewById(R.id.item5);
        if (item5 != null) {
            int changeHeight = Utils.dpToPx(60);
            item5.getLayoutParams().height -= changeHeight;
        }

        sceneRoot.findViewById(R.id.item6).setVisibility(View.VISIBLE);
    }

    @Override
    protected String getScreenName() {
        return getString(R.string.transitions_framework);
    }

}
