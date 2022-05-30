package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.FragmentLineal;

public class SetFragmentLineal {
    public static void set(){
        FragmentLineal fragment = new FragmentLineal();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
