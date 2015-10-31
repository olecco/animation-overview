package com.olecco.android.animationoverview.screens;

import android.animation.LayoutTransition;
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

        setTransitionCheck = (CheckBox) view.findViewById(R.id.set_transition);
        setTransitionCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                configureLayoutTransition();
            }
        });

        enableChangingCheck = (CheckBox) view.findViewById(R.id.enable_changing);
        enableChangingCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        }
    }

    private void onAddClick() {
        configureLayoutTransition();
        View coloredView = new ColoredView(getActivity());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, Utils.dpToPx(4), 0, 0);
        viewContainer.addView(coloredView, 1, lp);
    }

    private void onRemoveClick() {
        if (viewContainer.getChildCount() > 2) {
            configureLayoutTransition();
            viewContainer.removeViewAt(1);
        }
    }

    @Override
    protected String getScreenName() {
        return getResources().getString(R.string.layout_transitions);
    }
}
