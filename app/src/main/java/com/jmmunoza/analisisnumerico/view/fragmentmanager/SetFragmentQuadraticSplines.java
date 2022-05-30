package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.interpolation.FragmentLinealSplines;
import com.jmmunoza.analisisnumerico.view.fragments.interpolation.FragmentQuadraticSplines;

public class SetFragmentQuadraticSplines {
    public static void set(){
        FragmentQuadraticSplines fragment = new FragmentQuadraticSplines();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
