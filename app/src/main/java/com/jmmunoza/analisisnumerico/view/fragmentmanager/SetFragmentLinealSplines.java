package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.interpolation.FragmentLinealSplines;
import com.jmmunoza.analisisnumerico.view.fragments.nolineal.FragmentFixedPoint;

public class SetFragmentLinealSplines {
    public static void set(){
        FragmentLinealSplines fragment = new FragmentLinealSplines();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
