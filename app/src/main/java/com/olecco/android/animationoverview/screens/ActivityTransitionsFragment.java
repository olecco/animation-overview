package com.olecco.android.animationoverview.screens;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.olecco.android.animationoverview.AndroidDetailsActivity;
import com.olecco.android.animationoverview.R;

import static com.olecco.android.animationoverview.utils.Utils.ANDROID_ICONS;
import static com.olecco.android.animationoverview.utils.Utils.ANDROID_NAMES;

/**
 * Created by olecco on 07.11.2015.
 */
public class ActivityTransitionsFragment extends BaseAnimationFragment {

    private final View.OnClickListener onCardClickListener = new View.OnClickListener() {
        @SuppressWarnings("unchecked")
        @Override
        public void onClick(View v) {
            View icon = v.findViewById(android.R.id.icon);
            View text = v.findViewById(android.R.id.text1);

            Pair<View, String> iconPair = new Pair<>(icon, icon.getTransitionName());
            Pair<View, String> textPair = new Pair<>(text, text.getTransitionName());

            ActivityOptions activityOptions =
                    ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                            iconPair, textPair);

            Intent intent = new Intent(getActivity(), AndroidDetailsActivity.class);
            intent.putExtra(AndroidDetailsActivity.POSITION_EXTRA, (int) v.getTag());
            startActivity(intent, activityOptions.toBundle());
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
        for (int i = 0; i < ANDROID_NAMES.length; i++) {
            inflateCard(inflater, container, ANDROID_ICONS[i], ANDROID_NAMES[i], i);
        }
    }

    private void inflateCard(LayoutInflater inflater, ViewGroup container,
                             int iconId, int textId, int position) {
        View cardView = inflater.inflate(R.layout.android_card, container, false);
        cardView.setOnClickListener(onCardClickListener);
        cardView.setTag(position);

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
