package com.olecco.android.animationoverview;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;

import com.olecco.android.animationoverview.screens.LayoutTransitionsFragment;
import com.olecco.android.animationoverview.screens.PropertyAnimationFragment;
import com.olecco.android.animationoverview.screens.TransitionsFrameworkFragment;
import com.olecco.android.animationoverview.screens.ViewAnimationFragment;

public class MainActivity extends Activity implements MainMenuFragment.MainMenuListener {

    private Fragment mainMenuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainMenuFragment = getFragmentManager().findFragmentByTag(MainMenuFragment.TAG);
        if (mainMenuFragment == null) {
            mainMenuFragment = new MainMenuFragment();
            showFragment(mainMenuFragment, false, MainMenuFragment.TAG);
        }
    }

    private void showFragment(Fragment fragment, boolean toBackStack, String tag) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment, tag);
        if (toBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    private void setupMenuFragmentTransitions() {
        Slide slideInTransition = new Slide();
        Slide slideOutTransition = new Slide();
        slideInTransition.setSlideEdge(Gravity.START);
        slideInTransition.setStartDelay(getResources().getInteger(R.integer.transition_duration));
        slideInTransition.setDuration(getResources().getInteger(R.integer.transition_duration));
        slideOutTransition.setSlideEdge(Gravity.START);
        slideOutTransition.setDuration(getResources().getInteger(R.integer.transition_duration));
        mainMenuFragment.setReenterTransition(slideInTransition);
        mainMenuFragment.setExitTransition(slideOutTransition);
    }

    private void setupFragmentTransitions(Fragment fragment) {
        Fade fadeInTransition = new Fade();
        Fade fadeOutTransition = new Fade();
        fadeInTransition.setDuration(getResources().getInteger(R.integer.transition_duration));
        fadeInTransition.setStartDelay(getResources().getInteger(R.integer.transition_duration));
        fadeOutTransition.setDuration(getResources().getInteger(R.integer.transition_duration));
        fragment.setEnterTransition(fadeInTransition);
        fragment.setReturnTransition(fadeOutTransition);
    }

    private Fragment createMenuItemFragment(int itemId) {
        switch (itemId) {
            case R.id.view_animation_item_id:
                return new ViewAnimationFragment();
            case R.id.property_animation_item_id:
                return new PropertyAnimationFragment();
            case R.id.layout_transitions_item_id:
                return new LayoutTransitionsFragment();
            case R.id.transitions_framework_item_id:
                return new TransitionsFrameworkFragment();
            case R.id.shared_element_item_id:
            case R.id.drawable_animation_item_id:
            default:
                return null;
        }
    }

    @Override
    public void onMenuItemClicked(int itemId) {
        Fragment fragment = createMenuItemFragment(itemId);
        if (fragment != null) {
            setupMenuFragmentTransitions();
            setupFragmentTransitions(fragment);
            showFragment(fragment, true, null);
        }
    }
}
