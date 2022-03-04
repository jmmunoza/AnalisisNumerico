package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jmmunoza.analisisnumerico.MainActivity;
import com.jmmunoza.analisisnumerico.R;

public class FragmentAdderManager {
    private static MainActivity activityInstance;
    private static FragmentManager fragmentManager;

    private FragmentAdderManager(Context context){
        activityInstance = (MainActivity) context;
        fragmentManager = activityInstance.getSupportFragmentManager();
        SetFragmentMain.set();
    }

    public static void init(Context context){
        new FragmentAdderManager(context);
    }

    public static void reload(Context context){
        activityInstance = (MainActivity) context;
        fragmentManager = activityInstance.getSupportFragmentManager();
    }

    public static void onBackPressed(){
        int count = fragmentManager.getBackStackEntryCount();
        if (count <= 1) {
            activityInstance.moveTaskToBack(true);
        } else {
            deleteLastFragment();
        }
    }

    public static void showBottomSheetDialogFragment(BottomSheetDialogFragment dialog){
        dialog.show(fragmentManager, dialog.getTag());
    }

    public static void deleteAllFragments(){
        while(fragmentManager.getBackStackEntryCount() != 0){
            fragmentManager.popBackStackImmediate();
        }

        for (Fragment fragment : fragmentManager.getFragments()) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
    }

    public static void deleteLastFragment(){
        fragmentManager.popBackStackImmediate();
    }

    public static void addFragmentUp(Fragment fragment){
        fragmentManager.beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_up,
                        R.anim.slide_out_down,
                        R.anim.slide_in_down,
                        R.anim.slide_out_up)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.fragment_main_activity, fragment)
                .addToBackStack(null)
                .commit();
    }

    public static void addFragmentRight(Fragment fragment){
        fragmentManager.beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in_right,
                        R.anim.slide_out_left,
                        R.anim.slide_in_left,
                        R.anim.slide_out_right)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.fragment_main_activity, fragment)
                .addToBackStack(null)
                .commit();
    }

    public static void addFragmentFade(Fragment fragment){
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.fragment_main_activity, fragment)
                .addToBackStack(null)
                .commit();
    }
}
