package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.interpolation.FragmentDividedDifferences;
import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmentCompletePivoting;

public class SetFragmentCompletePivoting {
    public static void set(){
        FragmentCompletePivoting fragment = new FragmentCompletePivoting();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
