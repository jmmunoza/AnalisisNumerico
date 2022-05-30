package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmentLUFactorization;
import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmentPartialPivoting;

public class SetFragmentPartialPivoting {
    public static void set(){
        FragmentPartialPivoting fragment = new FragmentPartialPivoting();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
