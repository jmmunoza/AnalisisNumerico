package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.interpolation.FragmentDividedDifferences;
import com.jmmunoza.analisisnumerico.view.fragments.nolineal.FragmentFalsePosition;

public class SetFragmentDividedDifferences {
    public static void set(){
        FragmentDividedDifferences fragment = new FragmentDividedDifferences();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
