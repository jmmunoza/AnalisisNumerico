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
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentBisection;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentCholesky;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentCompletePivoting;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentCrout;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentDoolittle;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentFalsePosition;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentFixedPoint;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentGaussianElimination;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentLUFactorization;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentMultipleRoots;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentNewtonRaphson;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentPartialPivoting;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentSecant;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FragmentLineal extends Fragment {
    private RecyclerView       buttonsList;
    private AppBarLayout       noLinealAppBarLayout;
    private AppCompatImageView noLinealBackground;
    private RelativeLayout     noLinealContainer;
    private CoordinatorLayout  noLinealCoordinator;

    public FragmentLineal(){

    }

    @SuppressLint("InflateParams") @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postponeEnterTransition(1, TimeUnit.MILLISECONDS);
        return inflater.inflate(R.layout.fragment_lineal, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadComponents();
    }

    private void loadComponents(){
        noLinealAppBarLayout   = requireView().findViewById(R.id.lineal_app_bar);
        noLinealBackground     = requireView().findViewById(R.id.lineal_image);
        noLinealContainer      = requireView().findViewById(R.id.lineal_container);
        noLinealCoordinator    = requireView().findViewById(R.id.lineal_coordinator);
        buttonsList            = requireView().findViewById(R.id.lineal_button_list);

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
        buttons.add(new FragmentMain.MainButton(getString(R.string.cholesky), R.drawable.image_lineal, R.drawable.gradient_1) {
            @Override
            public void onClick() {
                SetFragmentCholesky.set();
            }
        });

        buttons.add(new FragmentMain.MainButton(getString(R.string.completePivoting), R.drawable.image_lineal,R.drawable.gradient_5) {
            @Override
            public void onClick() {
                SetFragmentCompletePivoting.set();
            }
        });

        buttons.add(new FragmentMain.MainButton(getString(R.string.crout), R.drawable.image_lineal,R.drawable.gradient_2) {
            @Override
            public void onClick() {
                SetFragmentCrout.set();
            }
        });

        buttons.add(new FragmentMain.MainButton(getString(R.string.doolittle), R.drawable.image_lineal,R.drawable.gradient_4) {
            @Override
            public void onClick() {
                SetFragmentDoolittle.set();
            }
        });

        buttons.add(new FragmentMain.MainButton(getString(R.string.gaussianElimination), R.drawable.image_lineal,R.drawable.gradient_6) {
            @Override
            public void onClick() {
                SetFragmentGaussianElimination.set();
            }
        });

        buttons.add(new FragmentMain.MainButton(getString(R.string.gauss_seidel), R.drawable.image_lineal,R.drawable.gradient_2) {
            @Override
            public void onClick() {
                SetFragmentGaussianElimination.set();
            }
        });

        buttons.add(new FragmentMain.MainButton(getString(R.string.jacobi), R.drawable.image_lineal,R.drawable.gradient_1) {
            @Override
            public void onClick() {
                SetFragmentFalsePosition.set();
            }
        });

        buttons.add(new FragmentMain.MainButton(getString(R.string.LU), R.drawable.image_lineal,R.drawable.gradient_5) {
            @Override
            public void onClick() {
                SetFragmentLUFactorization.set();
            }
        });

        buttons.add(new FragmentMain.MainButton(getString(R.string.partialPivoting), R.drawable.image_lineal,R.drawable.gradient_2) {
            @Override
            public void onClick() {
                SetFragmentPartialPivoting.set();
            }
        });

        MainButtonsAdapter buttonsAdapter = new MainButtonsAdapter(requireContext(), buttons, true);
        buttonsList.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        buttonsList.setAdapter(buttonsAdapter);
    }
}