package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmenCholesky;
import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmentGaussianElimination;

public class SetFragmentGaussianElimination {
    public static void set(){
        FragmentGaussianElimination fragment = new FragmentGaussianElimination();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
