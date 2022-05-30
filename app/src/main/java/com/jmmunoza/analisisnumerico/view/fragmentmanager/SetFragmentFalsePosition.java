package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.nolineal.FragmentFalsePosition;

public class SetFragmentFalsePosition {
    public static void set(){
        FragmentFalsePosition fragment = new FragmentFalsePosition();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
