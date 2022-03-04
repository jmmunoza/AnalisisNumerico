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

    private void setButtonsListFunction(){
        ArrayList<MainButton> buttons = new ArrayList<>();
        buttons.add(new MainButton("Diferenciacion", 1) {
            @Override
            public void onClick() {
                super.onClick();
                System.out.println("1");
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