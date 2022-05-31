package com.jmmunoza.analisisnumerico.view.fragmentmanager;

import com.jmmunoza.analisisnumerico.view.fragments.nolineal.FragmentIncrementalSearch;

public class SetFragmentIncrementalSearch {
    public static void set(){
        FragmentIncrementalSearch fragment = new FragmentIncrementalSearch();
        FragmentAdderManager.addFragmentUp(fragment);
    }
}
