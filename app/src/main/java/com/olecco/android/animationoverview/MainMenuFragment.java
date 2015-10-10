package com.olecco.android.animationoverview;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by olecco on 10.10.2015.
 */
public class MainMenuFragment extends Fragment {

    public interface MainMenuListener {
        void onMenuItemClicked(int itemId);
    }

    private MainMenuListener mainMenuListener;

    private final View.OnClickListener menuItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            notifyMenuItemClicked(v.getId());
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_menu, container, false);
        ViewGroup menuContainer = (ViewGroup) view.findViewById(R.id.menu_container);
        for (int i = 0; i < menuContainer.getChildCount(); i++) {
            menuContainer.getChildAt(i).setOnClickListener(menuItemClickListener);
        }
        return view;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onStart() {
        super.onStart();
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
        getActivity().getActionBar().setSubtitle(null);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainMenuListener = (MainMenuListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainMenuListener = null;
    }

    private void notifyMenuItemClicked(int itemId) {
        if (mainMenuListener != null) {
            mainMenuListener.onMenuItemClicked(itemId);
        }
    }
}
