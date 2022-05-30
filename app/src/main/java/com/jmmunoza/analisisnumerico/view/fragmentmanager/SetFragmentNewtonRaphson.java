package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.nolineal.FragmentNewtonRaphson;

public class SetFragmentNewtonRaphson {
    public static void set(){
        FragmentNewtonRaphson fragment = new FragmentNewtonRaphson();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
