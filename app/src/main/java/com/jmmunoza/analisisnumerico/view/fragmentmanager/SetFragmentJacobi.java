package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmentGaussianElimination;
import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmentJacobi;

public class SetFragmentJacobi {
    public static void set(){
        FragmentJacobi fragment = new FragmentJacobi();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
