package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.FragmentInterpolation;
import com.jmmunoza.analisisnumerico.view.fragments.interpolation.FragmentLagrange;

public class SetFragmentLagrange {
    public static void set(){
        FragmentLagrange fragment = new FragmentLagrange();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
