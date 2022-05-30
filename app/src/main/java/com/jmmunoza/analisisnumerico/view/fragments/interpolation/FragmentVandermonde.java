package com.jmmunoza.analisisnumerico.view.fragments.interpolation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmmunoza.analisisnumerico.R;
import com.jmmunoza.analisisnumerico.numericalmethods.interpolation.QuadraticSplines;
import com.jmmunoza.analisisnumerico.numericalmethods.interpolation.Vandermonde;
import com.jmmunoza.analisisnumerico.util.KeyboardManager;
import com.jmmunoza.analisisnumerico.view.adapters.InterpolationInputAdapter;
import com.jmmunoza.analisisnumerico.view.adapters.InterpolationResultAdapter;

import java.util.concurrent.TimeUnit;

public class FragmentVandermonde extends Fragment {
    private RecyclerView resultsList;
    private RecyclerView               coordinatesYList;
    private RecyclerView               coordinatesXList;
    private RecyclerView               XValuesList;
    private InterpolationResultAdapter resultsAdapter;
    private InterpolationInputAdapter coordinatesYAdapter;
    private InterpolationInputAdapter  coordinatesXAdapter;
    private InterpolationInputAdapter  XValuesAdapter;

    private double[]    results;

    private TextView title;
    private TextView solve;

    private ImageButton addCoordinates;
    private ImageButton addXValues;
    private ImageButton removeCoordinates;
    private ImageButton removeXValues;

    public FragmentVandermonde(){

    }

    @SuppressLint("InflateParams") @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postponeEnterTransition(1, TimeUnit.MILLISECONDS);
        return inflater.inflate(R.layout.fragment_interpolation_execution, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadComponents();
    }

    private void loadComponents(){
        addCoordinates    = requireView().findViewById(R.id.interpolation_execution_add_coordinates);
        addXValues        = requireView().findViewById(R.id.interpolation_execution_add_values);
        removeCoordinates = requireView().findViewById(R.id.interpolation_execution_remove_coordinates);
        removeXValues     = requireView().findViewById(R.id.interpolation_execution_remove_values);
        title             = requireView().findViewById(R.id.interpolation_execution_title);
        solve             = requireView().findViewById(R.id.interpolation_solve);
        resultsList       = requireView().findViewById(R.id.interpolation_execution_list);
        coordinatesXList  = requireView().findViewById(R.id.interpolation_execution_coordinates_x);
        coordinatesYList  = requireView().findViewById(R.id.interpolation_execution_coordinates_y);
        XValuesList       = requireView().findViewById(R.id.interpolation_execution_values);
        results           = new double[]{0};

        setInputsList();
        setAddButtons();
        setRemoveButtons();
        setResultsList();
        setSolveFunction();
        setTitle();
    }

    private void setAddButtons(){
        addCoordinates.setOnClickListener(v -> {
            coordinatesXAdapter.addInput("0.0");
            coordinatesYAdapter.addInput("0.0");
        });

        addXValues.setOnClickListener(v -> {
            XValuesAdapter.addInput("0.0");
        });
    }

    private void setRemoveButtons(){
        removeCoordinates.setOnClickListener(v -> {
            coordinatesXAdapter.deleteInput();
            coordinatesYAdapter.deleteInput();
        });

        removeXValues.setOnClickListener(v -> {
            XValuesAdapter.deleteInput();
        });
    }

    private void setInputsList(){
        coordinatesXAdapter = new InterpolationInputAdapter(requireContext(), true);
        coordinatesXList.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        coordinatesXList.setAdapter(coordinatesXAdapter);

        coordinatesYAdapter = new InterpolationInputAdapter(requireContext(), false);
        coordinatesYList.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        coordinatesYList.setAdapter(coordinatesYAdapter);

        XValuesAdapter = new InterpolationInputAdapter(requireContext(), true);
        XValuesList.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        XValuesList.setAdapter(XValuesAdapter);

        coordinatesXAdapter.addInput("0.0");
        coordinatesYAdapter.addInput("0.0");
        coordinatesXAdapter.addInput("1.0");
        coordinatesYAdapter.addInput("1.0");
        XValuesAdapter.addInput("0.0");
        XValuesAdapter.addInput("1.0");
    }

    private void setResultsList(){
        resultsAdapter = new InterpolationResultAdapter(requireContext(), results, results);
        resultsList.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        resultsList.setAdapter(resultsAdapter);
    }

    private void setTitle(){
        title.setText(R.string.vandermonde);
    }

    private void setSolveFunction(){
        solve.setOnClickListener(v -> {
            KeyboardManager.hide(requireContext(), requireView().getWindowToken());
            results  = new double[]{0};
            resultsAdapter.updateResults(results, results);

            try {

                double[] X        = coordinatesXAdapter.getNumbers();
                double[] Y        = coordinatesYAdapter.getNumbers();
                double[] X_values = XValuesAdapter.getNumbers();
                double[] results  = Vandermonde.vandermonde(X,Y,X_values);
                resultsAdapter.updateResults(X_values, results);

            } catch (Exception e){

            }
        });
    }
}