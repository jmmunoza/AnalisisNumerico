package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.nolineal.FragmentMultipleRoots;

public class SetFragmentMultipleRoots {
    public static void set(){
        FragmentMultipleRoots fragment = new FragmentMultipleRoots();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
