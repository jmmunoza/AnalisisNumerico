package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.FragmentTest;

public class SetFragmentTest {
    public static void set(){
        FragmentTest fragmentTest = new FragmentTest();
        FragmentAdderManager.addFragmentUp(fragmentTest);
    }
}
