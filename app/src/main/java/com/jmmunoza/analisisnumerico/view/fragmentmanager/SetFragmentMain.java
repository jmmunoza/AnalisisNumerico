package com.jmmunoza.analisisnumerico.view.fragmentmanager;
import com.jmmunoza.analisisnumerico.view.fragments.FragmentMain;

public class SetFragmentMain {
    public static void set(){
        FragmentMain fragmentMain = new FragmentMain();
        FragmentAdderManager.addFragmentFade(fragmentMain);
    }
}
