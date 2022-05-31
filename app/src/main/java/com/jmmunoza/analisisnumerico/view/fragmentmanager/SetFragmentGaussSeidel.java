package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmentGaussSeidel;

public class SetFragmentGaussSeidel {
    public static void set(){
        FragmentGaussSeidel fragment = new FragmentGaussSeidel();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
