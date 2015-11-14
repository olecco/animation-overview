package com.olecco.android.animationoverview;

import android.app.Activity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import static com.olecco.android.animationoverview.utils.Utils.ANDROID_ICONS;
import static com.olecco.android.animationoverview.utils.Utils.ANDROID_INFOS;
import static com.olecco.android.animationoverview.utils.Utils.ANDROID_NAMES;

/**
 * Created by olecco on 07.11.2015.
 */
public class AndroidDetailsActivity extends Activity {

    public static final String POSITION_EXTRA = "POSITION_EXTRA";

    private ImageView icon;
    private TextView textName;
    private TextView textInfo;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.android_details);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().hide();

        getWindow().setSharedElementEnterTransition(makeSharedElementsTransition());
        getWindow().setEnterTransition(makeEnterTransition());
        getWindow().setReturnTransition(makeReturnTransition());

        icon = (ImageView) findViewById(android.R.id.icon);
        textName = (TextView) findViewById(android.R.id.text1);
        textInfo = (TextView) findViewById(android.R.id.text2);

        int position = getIntent().getIntExtra(POSITION_EXTRA, -1);
        if (position == -1) {
            finish();
        } else {
            updateViews(position);
        }
    }

    private void updateViews(int position) {
        icon.setImageResource(ANDROID_ICONS[position]);
        textName.setText(ANDROID_NAMES[position]);
        textInfo.setText(ANDROID_INFOS[position]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Transition makeEnterTransition() {
        TransitionSet enterTransition = new TransitionSet();

        Slide slide = new Slide(Gravity.BOTTOM);
        slide.addTarget(android.R.id.text2);
        enterTransition.addTransition(slide);

        Fade fade = new Fade(Fade.IN);
        fade.addTarget(R.id.details_header);
        fade.setDuration(800);
        enterTransition.addTransition(fade);

        return enterTransition;
    }

    private Transition makeReturnTransition() {
        TransitionSet returnTransition = new TransitionSet();

        Slide slide = new Slide(Gravity.BOTTOM);
        slide.addTarget(android.R.id.text2);
        returnTransition.addTransition(slide);

        Slide slide2 = new Slide(Gravity.TOP);
        slide2.addTarget(R.id.details_header);
        returnTransition.addTransition(slide2);

        return returnTransition;
    }

    private Transition makeSharedElementsTransition() {
        TransitionSet set = new TransitionSet();

        Transition changeTransform = new ChangeTransform();
        set.addTransition(changeTransform);

        Transition changeBounds = new ChangeBounds();
        set.addTransition(changeBounds);

        return set;
    }
}
