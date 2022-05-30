package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.FragmentInterpolation;
import com.jmmunoza.analisisnumerico.view.fragments.nolineal.FragmentFixedPoint;

public class SetFragmentInterpolation {
    public static void set(){
        FragmentInterpolation fragment = new FragmentInterpolation();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
