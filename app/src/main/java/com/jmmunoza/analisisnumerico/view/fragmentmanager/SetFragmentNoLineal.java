package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.FragmentNoLineal;

public class SetFragmentNoLineal {
    public static void set(){
        FragmentNoLineal fragmentNoLineal = new FragmentNoLineal();
        FragmentAdderManager.addFragmentUp(fragmentNoLineal);
    }
}
