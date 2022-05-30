package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmentCrout;
import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmentDoolittle;

public class SetFragmentCrout {
    public static void set(){
        FragmentCrout fragment = new FragmentCrout();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
