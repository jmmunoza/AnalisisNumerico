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
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentNoLineal;
import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FragmentMain extends Fragment  {
    private RecyclerView         buttonsList;
    private AppBarLayout         mainAppBarLayout;
    private AppCompatImageView   mainBackground;
    private RelativeLayout       mainContainer;
    private CoordinatorLayout    mainCoordinator;

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

    private double calcDerivative(String f, double x){
        double h = 0.1;
        double x0 = getDerivative(f, x, h);
        h/=10;
        double x1 = getDerivative(f, x, h);
        double E = Math.abs(x0-x1);
        while (Math.abs((x0-x1)) >= E && E != 0){
            E = Math.abs(x0-x1);
            x0 = x1;
            h/=10;
            if(h == 0){
                return x0;
            }
            x1 = getDerivative(f, x, h);
        }

        return x1;
    }

    private double getDerivative(String f, double x, double h){
        String first = f.replace("x", "(x+h)");
        String second = f.replace("x", "(x-h)");
        String df = "((" + first + ")-(" + second + "))/(2*h)";
        return new Expression(df)
                .with("x", BigDecimal.valueOf(x))
                .and("h", String.valueOf(h))
                .setPrecision(15)
                .eval()
                .doubleValue();
    }

    private void setButtonsListFunction(){
        ArrayList<MainButton> buttons = new ArrayList<>();
        buttons.add(new MainButton(getString(R.string.derivative), R.drawable.image_derivative, R.drawable.gradient_1) {
            @Override
            public void onClick() {
                super.onClick();
                System.out.println("1");
            }
        });
        buttons.add(new MainButton(getString(R.string.integral), R.drawable.image_integral, R.drawable.gradient_2) {
            @Override
            public void onClick() {
                super.onClick();
                System.out.println("1");
            }
        });
        buttons.add(new MainButton(getString(R.string.no_lineal), R.drawable.image_no_lineal, R.drawable.gradient_3) {
            @Override
            public void onClick() {
                super.onClick();
                SetFragmentNoLineal.set();
            }
        });
        buttons.add(new MainButton(getString(R.string.lineal), R.drawable.image_lineal, R.drawable.gradient_4) {
            @Override
            public void onClick() {
                super.onClick();
                System.out.println("2");
            }
        });
        buttons.add(new MainButton(getString(R.string.differentialecuations), R.drawable.image_differential_ecuation,R.drawable.gradient_5) {
            @Override
            public void onClick() {
                super.onClick();
                System.out.println("3");
            }
        });
        buttons.add(new MainButton(getString(R.string.interpolation), R.drawable.image_interpolation, R.drawable.gradient_6) {
            @Override
            public void onClick() {
                super.onClick();
                System.out.println("4");
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