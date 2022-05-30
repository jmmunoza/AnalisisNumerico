package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmentLUFactorization;

public class SetFragmentLUFactorization {
    public static void set(){
        FragmentLUFactorization fragment = new FragmentLUFactorization();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
