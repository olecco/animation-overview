package com.olecco.android.animationoverview.screens;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.olecco.android.animationoverview.R;

/**
 * Created by olecco on 10.10.2015.
 */
public abstract class BaseAnimationFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onStart() {
        super.onStart();
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        getActivity().getActionBar().setSubtitle(getScreenName());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (hasPlayButton()) {
            inflater.inflate(R.menu.play, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getFragmentManager().popBackStack();
                return true;
            case R.id.play:
                onPlayButtonClick();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onPlayButtonClick() { }

    protected boolean hasPlayButton() {
        return false;
    }

    protected abstract String getScreenName();

}
