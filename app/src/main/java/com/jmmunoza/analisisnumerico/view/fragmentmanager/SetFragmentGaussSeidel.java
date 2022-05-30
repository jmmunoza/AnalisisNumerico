package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmentGaussSeidel;
import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmentJacobi;

public class SetFragmentGaussSeidel {
    public static void set(){
        FragmentGaussSeidel fragment = new FragmentGaussSeidel();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
