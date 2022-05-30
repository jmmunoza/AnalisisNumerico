package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.nolineal.FragmentSecant;

public class SetFragmentSecant {
    public static void set(){
        FragmentSecant fragment = new FragmentSecant();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
