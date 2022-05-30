package com.jmmunoza.analisisnumerico.view.fragments.lineal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import com.jmmunoza.analisisnumerico.numericalmethods.lineal.GaussSeidel;
import com.jmmunoza.analisisnumerico.numericalmethods.lineal.Jacobi;
import com.jmmunoza.analisisnumerico.util.KeyboardManager;
import com.jmmunoza.analisisnumerico.util.ToastMaker;
import com.jmmunoza.analisisnumerico.view.adapters.LinealInputAdapter;
import com.jmmunoza.analisisnumerico.view.adapters.NoLinealResultAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FragmentGaussSeidel  extends Fragment {
    private RecyclerView resultsList;
    private RecyclerView               matrix;
    private RecyclerView               vector;
    private NoLinealResultAdapter resultsAdapter;
    private LinealInputAdapter matrixAdapter;
    private LinealInputAdapter         vectorAdapter;

    private ArrayList<String> results;

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

    public FragmentGaussSeidel(){

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
        results           = new ArrayList<>();

        setErrorSpinner();
        setOptions();
        setInputsList();
        setAddButtons();
        setRemoveButtons();
        setResultsList();
        setSolveFunction();
        setTitle();
    }

    private void setOptions(){
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

    private void setErrorSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.errors,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        errorSpinner.setAdapter(adapter);
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
        resultsAdapter = new NoLinealResultAdapter(requireContext(), results);
        resultsList.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        resultsList.setAdapter(resultsAdapter);
    }

    private void setTitle(){
        title.setText(R.string.gauss_seidel);
    }

    private void setSolveFunction(){
        solve.setOnClickListener(v -> {
            KeyboardManager.hide(requireContext(), requireView().getWindowToken());
            results.clear();
            resultsAdapter.updateResults(results);

            try {
                int    i_max      = Integer.parseInt(IMAXText.getText().toString());
                double tol        = Double.parseDouble(ERRORText.getText().toString());
                boolean errorType = errorSpinner.getSelectedItem().toString().equals("E");

                double[][] A     = matrixAdapter.getMatrix();
                double[] b       = vectorAdapter.getVector();

                String resultsHeader = "";
                resultsHeader += "Iteración   |   ";
                for(int i = 0; i < A.length; i++) resultsHeader += "x" + (i+1) + "   |   ";
                resultsHeader += "Error";
                results.add(resultsHeader);

                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(10);
                df.setMinimumFractionDigits(10);

                double[] resultsAb = GaussSeidel.gaussSeidel(A, b, i_max, tol, errorType, (i, x, e) -> {
                    String result = "";
                    result += String.format("%1d", i);
                    result += "   |   ";
                    for(int k = 0; k < x.length; k++) result += String.format("%5f", x[k]) + "   |   ";
                    result += String.format("%10f", e);
                    results.add(result);
                });

                if(resultsAb != null){
                    resultsAdapter.updateResults(results);
                    result.setVisibility(View.VISIBLE);

                    String resultsString = "";
                    for(int i = 0; i < resultsAb.length; i++){
                        resultsString += "x" + (i+1) + " = " + resultsAb[i] + "\n";
                    }
                    result.setVisibility(View.VISIBLE);
                    resultText.setText(resultsString);

                } else {
                    ToastMaker.show("No se llegó a una solución satisfactoria.");
                    result.setVisibility(View.GONE);
                    results.clear();
                }
                resultsAdapter.updateResults(results);
            } catch (Exception e){
                System.out.println(e.toString());
                ToastMaker.show("Hay un error con los parámetros de entrada, por favor revísalos.");
            }


        });
    }
}
