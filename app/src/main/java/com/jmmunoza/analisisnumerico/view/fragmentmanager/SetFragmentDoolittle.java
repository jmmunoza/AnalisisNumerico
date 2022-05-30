package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmenCholesky;
import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmentDoolittle;

public class SetFragmentDoolittle {
    public static void set(){
        FragmentDoolittle fragment = new FragmentDoolittle();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
