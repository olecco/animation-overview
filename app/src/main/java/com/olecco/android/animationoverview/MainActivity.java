package com.olecco.android.animationoverview;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.olecco.android.animationoverview.screens.ViewAnimationFragment;

public class MainActivity extends Activity implements MainMenuFragment.MainMenuListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            showFragment(new MainMenuFragment(), false);
        }
    }

    private void showFragment(Fragment fragment, boolean toBackStack) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        if (toBackStack) {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    private Fragment createMenuItemFragment(int itemId) {
        switch (itemId) {
            case R.id.view_animation_item_id:
                return new ViewAnimationFragment();
            case R.id.property_animation_item_id:
            case R.id.transition_framework_item_id:
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
            showFragment(fragment, true);
        }
    }
}
