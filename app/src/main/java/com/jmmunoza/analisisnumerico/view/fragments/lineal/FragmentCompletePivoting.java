package com.jmmunoza.analisisnumerico.view.fragments.lineal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmmunoza.analisisnumerico.R;
import com.jmmunoza.analisisnumerico.numericalmethods.lineal.CompletePivoting;
import com.jmmunoza.analisisnumerico.util.KeyboardManager;
import com.jmmunoza.analisisnumerico.view.adapters.InterpolationResultAdapter;
import com.jmmunoza.analisisnumerico.view.adapters.LinealInputAdapter;

import java.util.concurrent.TimeUnit;

public class FragmentCompletePivoting extends Fragment {
    private RecyclerView resultsList;
    private RecyclerView               matrix;
    private RecyclerView               vector;
    private InterpolationResultAdapter resultsAdapter;
    private LinealInputAdapter         matrixAdapter;
    private LinealInputAdapter         vectorAdapter;

    private double[]    results;

    private ConstraintLayout IMAXLayout;
    private ConstraintLayout ERRORLayout;

    private EditText IMAXText;
    private EditText ERRORText;

    private CardView result;

    private TextView title;
    private TextView resultText;
    private TextView solve;

    private ImageButton add;
    private ImageButton remove;

    private Spinner errorSpinner;

    public FragmentCompletePivoting(){

    }

    @SuppressLint("InflateParams") @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postponeEnterTransition(1, TimeUnit.MILLISECONDS);
        return inflater.inflate(R.layout.fragment_lineal_execution, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadComponents();
    }

    private void loadComponents(){
        errorSpinner = requireView().findViewById(R.id.lineal_execution_error_spinner);
        IMAXLayout        = requireView().findViewById(R.id.lineal_execution_i_max);
        ERRORLayout       = requireView().findViewById(R.id.lineal_execution_error);
        IMAXText          = requireView().findViewById(R.id.lineal_execution_limit_text);
        ERRORText         = requireView().findViewById(R.id.lineal_execution_error_text);
        add               = requireView().findViewById(R.id.lineal_execution_add);
        remove            = requireView().findViewById(R.id.lineal_execution_remove);
        title             = requireView().findViewById(R.id.lineal_execution_title);
        solve             = requireView().findViewById(R.id.lineal_solve);
        resultsList       = requireView().findViewById(R.id.lineal_execution_list);
        matrix            = requireView().findViewById(R.id.lineal_execution_matrix);
        vector            = requireView().findViewById(R.id.lineal_execution_vector);
        result            = requireView().findViewById(R.id.lineal_result);
        resultText        = requireView().findViewById(R.id.lineal_result_text);
        results           = new double[]{0};

        setResult();
        setInputsList();
        setAddButtons();
        setRemoveButtons();
        setResultsList();
        setSolveFunction();
        setTitle();
    }

    private void setResult(){
        result.setVisibility(View.GONE);
    }

    private void setAddButtons(){
        add.setOnClickListener(v -> {
            matrixAdapter.addInput();
            vectorAdapter.addInput();
            matrix.setLayoutManager(new GridLayoutManager(requireContext(), matrixAdapter.getMatrixSize()));
        });

    }

    private void setRemoveButtons(){
        remove.setOnClickListener(v -> {
            matrixAdapter.deleteInput();
            vectorAdapter.deleteInput();
            matrix.setLayoutManager(new GridLayoutManager(requireContext(), matrixAdapter.getMatrixSize()));
        });
    }

    private void setInputsList(){
        matrixAdapter = new LinealInputAdapter(requireContext(), true, false);
        matrix.setLayoutManager(new GridLayoutManager(requireContext(), matrixAdapter.getMatrixSize()));
        matrix.setAdapter(matrixAdapter);

        vectorAdapter = new LinealInputAdapter(requireContext(), true, true);
        vector.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        vector.setAdapter(vectorAdapter);
    }

    private void setResultsList(){
        resultsAdapter = new InterpolationResultAdapter(requireContext(), results, results);
        resultsList.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        resultsList.setAdapter(resultsAdapter);
    }

    private void setTitle(){
        title.setText(R.string.completePivoting);
    }

    private void setSolveFunction(){
        solve.setOnClickListener(v -> {
            KeyboardManager.hide(requireContext(), requireView().getWindowToken());
            results  = new double[]{0};
            resultsAdapter.updateResults(results, results);

            try {

                double[][] A        = matrixAdapter.getMatrix();
                double[] b          = vectorAdapter.getVector();
                double[] results    = CompletePivoting.pivoting(A,b);

                String resultsString = "";
                for(int i = 0; i < results.length; i++){
                    resultsString += "x" + (i+1) + " = " + results[i] + "\n";
                }
                result.setVisibility(View.VISIBLE);
                resultText.setText(resultsString);

                resultsAdapter.updateResults(results, results);

            } catch (Exception e){

            }


        });
    }
}
