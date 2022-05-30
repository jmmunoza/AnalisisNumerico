package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.nolineal.FragmentFalsePosition;
import com.jmmunoza.analisisnumerico.view.fragments.nolineal.FragmentFixedPoint;

public class SetFragmentFixedPoint {
    public static void set(){
        FragmentFixedPoint fragment = new FragmentFixedPoint();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
