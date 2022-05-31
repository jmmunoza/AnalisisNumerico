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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.jmmunoza.analisisnumerico.R;
import com.jmmunoza.analisisnumerico.view.adapters.MainButtonsAdapter;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentInterpolation;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentLineal;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentNoLineal;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FragmentMain extends Fragment  {
    private RecyclerView       buttonsList;
    private AppBarLayout       mainAppBarLayout;
    private AppCompatImageView mainBackground;
    private RelativeLayout     mainContainer;
    private CoordinatorLayout  mainCoordinator;

    public FragmentMain(){

    }

    @SuppressLint("InflateParams") @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postponeEnterTransition(1, TimeUnit.MILLISECONDS);
        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadComponents();
    }

    private void loadComponents(){
        mainAppBarLayout   = requireView().findViewById(R.id.main_app_bar);
        mainBackground     = requireView().findViewById(R.id.main_image);
        mainContainer      = requireView().findViewById(R.id.main_container);
        mainCoordinator    = requireView().findViewById(R.id.main_coordinator);
        buttonsList        = requireView().findViewById(R.id.main_button_list);

        setButtonsListFunction();
        setAppBarFunction();
    }

    private void setAppBarFunction(){
        ViewCompat.requestApplyInsets(mainCoordinator);
        mainAppBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            float percent = ((float)Math.abs(verticalOffset)) / appBarLayout.getTotalScrollRange();
            mainBackground.setTranslationY((float)-verticalOffset);
            mainBackground.setAlpha(1F - percent);
        });
    }

    private void printVector(double[] v){
        for(int i = 0; i < v.length; i++){
            System.out.print(v[i] + "   ");
        }

        System.out.println();
    }

    public static void print2D(double mat[][])
    {
        for (int i = 0; i < mat.length; i++){
            for (int j = 0; j < mat[i].length; j++){
                System.out.print(mat[i][j] + "            ");
            }
            System.out.println("");
        }
        System.out.println("----------------------------------------------------------------");
    }

    private void setButtonsListFunction(){
        ArrayList<MainButton> buttons = new ArrayList<>();
        buttons.add(new MainButton(getString(R.string.derivative), R.drawable.image_derivative, R.drawable.gradient_1) {
            @Override
            public void onClick() {

            }
        });
        buttons.add(new MainButton(getString(R.string.integral), R.drawable.image_integral, R.drawable.gradient_2) {
            @Override
            public void onClick() {

            }
        });
        buttons.add(new MainButton(getString(R.string.no_lineal), R.drawable.image_no_lineal, R.drawable.gradient_3) {
            @Override
            public void onClick() {
                SetFragmentNoLineal.set();
            }
        });
        buttons.add(new MainButton(getString(R.string.lineal), R.drawable.image_lineal, R.drawable.gradient_4) {
            @Override
            public void onClick() {
                SetFragmentLineal.set();
            }
        });
        buttons.add(new MainButton(getString(R.string.differentialecuations), R.drawable.image_differential_ecuation,R.drawable.gradient_5) {
            @Override
            public void onClick() {

            }
        });
        buttons.add(new MainButton(getString(R.string.interpolation), R.drawable.image_interpolation, R.drawable.gradient_6) {
            @Override
            public void onClick() {
                SetFragmentInterpolation.set();
            }
        });

        MainButtonsAdapter buttonsAdapter = new MainButtonsAdapter(requireContext(), buttons, false);
        buttonsList.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        buttonsList.setAdapter(buttonsAdapter);
    }

    public abstract static class MainButton {
        private final int    image;
        private final String title;
        private final int    background;

        public MainButton(String title, int image, int background){
            this.image      = image;
            this.title      = title;
            this.background = background;
        }

        public void onClick() {

        }

        public int getBackground() {
            return background;
        }

        public String getTitle() {
            return title;
        }

        public int getImage() {
            return image;
        }
    }
}