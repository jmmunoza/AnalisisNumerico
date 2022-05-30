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
import com.jmmunoza.analisisnumerico.numericalmethods.derivatives.CentralDerivative;
import com.jmmunoza.analisisnumerico.numericalmethods.interpolation.DividedDifferences;
import com.jmmunoza.analisisnumerico.numericalmethods.interpolation.Lagrange;
import com.jmmunoza.analisisnumerico.numericalmethods.interpolation.LinealSplines;
import com.jmmunoza.analisisnumerico.numericalmethods.interpolation.QuadraticSplines;
import com.jmmunoza.analisisnumerico.numericalmethods.interpolation.Vandermonde;
import com.jmmunoza.analisisnumerico.numericalmethods.lineal.Cholesky;
import com.jmmunoza.analisisnumerico.numericalmethods.lineal.CompletePivoting;
import com.jmmunoza.analisisnumerico.numericalmethods.lineal.Crout;
import com.jmmunoza.analisisnumerico.numericalmethods.lineal.Doolittle;
import com.jmmunoza.analisisnumerico.numericalmethods.lineal.GaussSeidel;
import com.jmmunoza.analisisnumerico.numericalmethods.lineal.GaussianElimination;
import com.jmmunoza.analisisnumerico.numericalmethods.lineal.Jacobi;
import com.jmmunoza.analisisnumerico.numericalmethods.lineal.LUFactorization;
import com.jmmunoza.analisisnumerico.numericalmethods.lineal.PartialPivoting;
import com.jmmunoza.analisisnumerico.numericalmethods.matrixoperations.Elementary;
import com.jmmunoza.analisisnumerico.numericalmethods.nolineal.MultipleRoots;
import com.jmmunoza.analisisnumerico.view.adapters.MainButtonsAdapter;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentInterpolation;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentLineal;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentNoLineal;
import com.jmmunoza.analisisnumerico.view.fragmentmanager.SetFragmentTest;
import com.udojava.evalex.Expression;

import java.math.BigDecimal;
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
                SetFragmentTest.set();
            }
        });
        buttons.add(new MainButton(getString(R.string.integral), R.drawable.image_integral, R.drawable.gradient_2) {
            @Override
            public void onClick() {
 /*
                double[][]A = new double[][]{
                        {2, -1, -3, 2},
                        {5, -10, 2, -6},
                        {5, -9, 15, -6},
                        {2, 1, -1, 10}
                };

                double[] b = {
                        4,3,2,1
                };

                printVector(GaussSeidel.gaussSeidel(A.clone(), b.clone(), 500));

  */

                double[] X = {
                        40,140,240,340,440,540,740,940,1140,1340,1540
                };

                double[] Y = {
                        119.5, 143.5,167.6,191.8,216.3,241,291.3,342.9,395.7,449.7,504.7
                };


                double[] values = {
                        40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 300, 310, 320, 330, 340, 350, 360, 370, 380, 390, 400, 410, 420, 430, 440, 450, 460, 470, 480, 490, 500, 510, 520, 530, 540, 550, 560, 570, 580, 590, 600, 610, 620, 630, 640, 650, 660, 670, 680, 690, 700, 710, 720, 730, 740, 750, 760, 770, 780, 790, 800, 810, 820, 830, 840, 850, 860, 870, 880, 890, 900, 910, 920, 930, 940, 950, 960, 970, 980, 990, 1000, 1010, 1020, 1030, 1040, 1050, 1060, 1070, 1080, 1090, 1100, 1110, 1120, 1130, 1140, 1150, 1160, 1170, 1180, 1190, 1200, 1210, 1220, 1230, 1240, 1250,
                        1260, 1270, 1280, 1290, 1300, 1310, 1320, 1330, 1340, 1350, 1360, 1370, 1380, 1390, 1400, 1410, 1420, 1430, 1440, 1450, 1460, 1470, 1480, 1490, 1500, 1510, 1520, 1530, 1540, 1550
                };

                printVector(Lagrange.lagrange(X,Y,values));


                 /*
                A = new double[][]{
                         {2, -1, -3, 2},
                         {5, -10, 2, -6},
                         {5, -9, 15, -6},
                         {2, 1, -1, 10}
                 };

                b = new double[]{
                        4,3,2,1
                };

                printVector(Jacobi.jacobi(A.clone(), b.clone(), 500));

                 A = new double[][]{
                         {2, -1, -3, 2},
                         {5, -10, 2, -6},
                         {5, -9, 15, -6},
                         {2, 1, -1, 10}
                 };

                b = new double[]{
                        4, 3, 2, 1
                };

                printVector(PartialPivoting.pivoting(A.clone(), b.clone()));

                A = new double[][]{
                        {2, -1, -3, 2},
                        {5, -10, 2, -6},
                        {5, -9, 15, -6},
                        {2, 1, -1, 10}
                };

                b = new double[]{
                        4, 3, 2, 1
                };

                printVector(CompletePivoting.pivoting(A.clone(), b.clone()));

                A = new double[][]{
                        {2, -1, -3, 2},
                        {5, -10, 2, -6},
                        {5, -9, 15, -6},
                        {2, 1, -1, 10}
                };

                b = new double[]{
                        4, 3, 2, 1
                };

                printVector(GaussianElimination.gauss(A.clone(), b.clone()));


                A = new double[][]{
                        {2, -1, -3, 2},
                        {5, -10, 2, -6},
                        {5, -9, 15, -6},
                        {2, 1, -1, 10}
                };

                b = new double[]{
                        4, 3, 2, 1
                };

                printVector(LUFactorization.LU(A.clone(), b.clone()));

                A = new double[][]{
                        {2, -1, -3, 2},
                        {5, -10, 2, -6},
                        {5, -9, 15, -6},
                        {2, 1, -1, 10}
                };

                b = new double[]{
                        4, 3, 2, 1
                };

                printVector(Crout.crout(A.clone(), b.clone()));

                A = new double[][]{
                        {2, -1, -3, 2},
                        {5, -10, 2, -6},
                        {5, -9, 15, -6},
                        {2, 1, -1, 10}
                };

                b = new double[]{
                        4, 3, 2, 1
                };

                printVector(Doolittle.doolittle(A.clone(), b.clone()));

                 */
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