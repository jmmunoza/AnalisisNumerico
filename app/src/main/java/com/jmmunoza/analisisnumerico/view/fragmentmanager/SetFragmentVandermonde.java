package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.interpolation.FragmentLinealSplines;
import com.jmmunoza.analisisnumerico.view.fragments.interpolation.FragmentVandermonde;

public class SetFragmentVandermonde {
    public static void set(){
        FragmentVandermonde fragment = new FragmentVandermonde();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
