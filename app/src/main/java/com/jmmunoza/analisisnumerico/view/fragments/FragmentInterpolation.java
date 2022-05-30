package com.jmmunoza.analisisnumerico.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.jmmunoza.analisisnumerico.R;
import com.jmmunoza.analisisnumerico.view.adapters.MainButtonsAdapter;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentDividedDifferences;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentLagrange;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentLinealSplines;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentQuadraticSplines;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentVandermonde;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FragmentInterpolation extends Fragment {
    private RecyclerView       buttonsList;
    private AppBarLayout       noLinealAppBarLayout;
    private AppCompatImageView noLinealBackground;
    private RelativeLayout     noLinealContainer;
    private CoordinatorLayout  noLinealCoordinator;

    public FragmentInterpolation(){

    }

    @SuppressLint("InflateParams") @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postponeEnterTransition(1, TimeUnit.MILLISECONDS);
        return inflater.inflate(R.layout.fragment_interpolation, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadComponents();
    }

    private void loadComponents(){
        noLinealAppBarLayout   = requireView().findViewById(R.id.interpolation_app_bar);
        noLinealBackground     = requireView().findViewById(R.id.interpolation_image);
        noLinealContainer      = requireView().findViewById(R.id.interpolation_container);
        noLinealCoordinator    = requireView().findViewById(R.id.interpolation_coordinator);
        buttonsList            = requireView().findViewById(R.id.interpolation_button_list);

        setButtonsListFunction();
        setAppBarFunction();
    }

    private void setAppBarFunction(){
        ViewCompat.requestApplyInsets(noLinealCoordinator);
        noLinealAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            float percent = ((float)Math.abs(verticalOffset)) / appBarLayout.getTotalScrollRange();
            noLinealBackground.setTranslationY((float)-verticalOffset);
            noLinealBackground.setAlpha(1F - percent);
        });
    }

    private void setButtonsListFunction(){
        ArrayList<FragmentMain.MainButton> buttons = new ArrayList<>();
        buttons.add(new FragmentMain.MainButton(getString(R.string.divided_differences), R.drawable.image_interpolation, R.drawable.gradient_1) {
            @Override
            public void onClick() {
                SetFragmentDividedDifferences.set();
            }
        });

        buttons.add(new FragmentMain.MainButton(getString(R.string.lagrange), R.drawable.image_interpolation,R.drawable.gradient_5) {
            @Override
            public void onClick() {
                SetFragmentLagrange.set();
            }
        });

        buttons.add(new FragmentMain.MainButton(getString(R.string.lineal_splines), R.drawable.image_interpolation,R.drawable.gradient_2) {
            @Override
            public void onClick() {
                SetFragmentLinealSplines.set();
            }
        });

        buttons.add(new FragmentMain.MainButton(getString(R.string.quadratic_splines), R.drawable.image_interpolation,R.drawable.gradient_4) {
            @Override
            public void onClick() {
                SetFragmentQuadraticSplines.set();
            }
        });

        buttons.add(new FragmentMain.MainButton(getString(R.string.vandermonde), R.drawable.image_interpolation,R.drawable.gradient_6) {
            @Override
            public void onClick() {
                SetFragmentVandermonde.set();
            }
        });

        MainButtonsAdapter buttonsAdapter = new MainButtonsAdapter(requireContext(), buttons, true);
        buttonsList.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        buttonsList.setAdapter(buttonsAdapter);
    }
}