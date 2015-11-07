package com.olecco.android.animationoverview.screens;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.olecco.android.animationoverview.AndroidDetailsActivity;
import com.olecco.android.animationoverview.R;

/**
 * Created by olecco on 07.11.2015.
 */
public class ActivityTransitionsFragment extends BaseAnimationFragment {

    private final int[] android_names = { R.string.jelly_bean, R.string.kitkat,
            R.string.lollipop, R.string.marshmallow };

    private final int[] android_icons = { R.drawable.jelly_bean, R.drawable.kitkat,
            R.drawable.lollipop, R.drawable.marshmallow };

    private final View.OnClickListener onCardClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(), AndroidDetailsActivity.class));
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_transitions, container, false);
        ViewGroup grid = (ViewGroup) view.findViewById(R.id.grid);
        inflateCards(inflater, grid);
        return view;
    }

    private void inflateCards(LayoutInflater inflater, ViewGroup container) {
        for (int i = 0; i < android_names.length; i++) {
            inflateCard(inflater, container, android_icons[i], android_names[i]);
        }
    }

    private void inflateCard(LayoutInflater inflater, ViewGroup container, int iconId, int textId) {
        View cardView = inflater.inflate(R.layout.android_card, container, false);
        cardView.setOnClickListener(onCardClickListener);

        ImageView icon = (ImageView) cardView.findViewById(android.R.id.icon);
        icon.setImageResource(iconId);
        TextView text = (TextView) cardView.findViewById(android.R.id.text1);
        text.setText(textId);

        container.addView(cardView);
    }

    @Override
    protected String getScreenName() {
        return getString(R.string.activity_fragments_transitions);
    }
}
