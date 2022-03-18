package com.jmmunoza.analisisnumerico.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.jmmunoza.analisisnumerico.R;
import com.jmmunoza.analisisnumerico.view.adapters.MainButtonsAdapter;
import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FragmentMain extends Fragment  {
    private MainButtonsAdapter buttonsAdapter;
    private RecyclerView       buttonsRecyclerView;

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
        buttonsRecyclerView   = requireView().findViewById(R.id.main_button_list);
        setButtonsListFunction();
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
        buttons.add(new MainButton("Diferenciacion", 1) {
            @Override
            public void onClick() {
                super.onClick();
            }
        });
        buttons.add(new MainButton("Solución de ecuaciones no lineales", 1) {
            @Override
            public void onClick() {
                super.onClick();
                System.out.println("1");
            }
        });
        buttons.add(new MainButton("Integración", 1) {
            @Override
            public void onClick() {
                super.onClick();
                System.out.println("1");
            }
        });
        buttons.add(new MainButton("Solución de ecuaciones  lineales", 1) {
            @Override
            public void onClick() {
                super.onClick();
                System.out.println("2");
            }
        });
        buttons.add(new MainButton("Ecuaciones diferenciales", 1) {
            @Override
            public void onClick() {
                super.onClick();
                System.out.println("3");
            }
        });
        buttons.add(new MainButton("Interpolacion", 1) {
            @Override
            public void onClick() {
                super.onClick();
                System.out.println("4");
            }
        });
        buttons.add(new MainButton("Extrapolacion", 1) {
            @Override
            public void onClick() {
                super.onClick();
                System.out.println("4");
            }
        });

        buttonsAdapter = new MainButtonsAdapter(requireContext(), buttons);
        buttonsRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        buttonsRecyclerView.setAdapter(buttonsAdapter);
    }

    public abstract static class MainButton {
        private final int    image;
        private final String title;

        public MainButton(String title, int image){
            this.image = image;
            this.title = title;
        }

        public void onClick() {

        }

        public String getTitle() {
            return title;
        }

        public int getImage() {
            return image;
        }
    }
}