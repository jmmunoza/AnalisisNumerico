package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.lineal.FragmenCholesky;

public class SetFragmentCholesky {
    public static void set(){
        FragmenCholesky fragment = new FragmenCholesky();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}

