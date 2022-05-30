package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.nolineal.FragmentBisection;

public class SetFragmentBisection {
    public static void set(){
        FragmentBisection fragment = new FragmentBisection();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
