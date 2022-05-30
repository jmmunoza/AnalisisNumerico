package com.jmmunoza.analisisnumerico.view.fragments.nolineal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmmunoza.analisisnumerico.R;
import com.jmmunoza.analisisnumerico.numericalmethods.nolineal.MultipleRoots;
import com.jmmunoza.analisisnumerico.util.KeyboardManager;
import com.jmmunoza.analisisnumerico.util.ToastMaker;
import com.jmmunoza.analisisnumerico.view.adapters.NoLinealResultAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FragmentMultipleRoots extends Fragment {
    private RecyclerView resultsList;
    private NoLinealResultAdapter adapter;
    private ArrayList<String> results;

    private ConstraintLayout IMAXLayout;
    private ConstraintLayout XFLayout;
    private ConstraintLayout DXLayout;
    private ConstraintLayout ERRORLayout;
    private ConstraintLayout GXLayout;

    private EditText GXText;
    private EditText IMAXText;
    private EditText XFText;
    private EditText FXText;
    private EditText XIText;
    private EditText DXText;
    private EditText ERRORText;

    private TextView title;
    private TextView resultText;
    private TextView solve;

    private Spinner errorSpinner;

    private CardView result;

    public FragmentMultipleRoots(){

    }

    @SuppressLint("InflateParams") @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postponeEnterTransition(1, TimeUnit.MILLISECONDS);
        return inflater.inflate(R.layout.fragment_no_lineal_execution, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadComponents();
    }

    private void loadComponents(){
        IMAXLayout   = requireView().findViewById(R.id.no_lineal_execution_i_max);
        XFLayout     = requireView().findViewById(R.id.no_lineal_execution_xf);
        DXLayout     = requireView().findViewById(R.id.no_lineal_execution_dx);
        ERRORLayout  = requireView().findViewById(R.id.no_lineal_execution_error);
        IMAXText     = requireView().findViewById(R.id.no_lineal_execution_limit_text);
        XFText       = requireView().findViewById(R.id.no_lineal_execution_final_text);
        DXText       = requireView().findViewById(R.id.no_lineal_execution_dx_text);
        FXText       = requireView().findViewById(R.id.no_lineal_execution_function_text);
        XIText       = requireView().findViewById(R.id.no_lineal_execution_initial_text);
        ERRORText    = requireView().findViewById(R.id.no_lineal_execution_error_text);
        title        = requireView().findViewById(R.id.no_lineal_execution_title);
        solve        = requireView().findViewById(R.id.no_lineal_solve);
        IMAXLayout   = requireView().findViewById(R.id.no_lineal_execution_i_max);
        GXLayout     = requireView().findViewById(R.id.no_lineal_execution_gx);
        GXText       = requireView().findViewById(R.id.no_lineal_execution_g_function_text);
        result       = requireView().findViewById(R.id.no_lineal_result);
        resultText   = requireView().findViewById(R.id.no_lineal_result_text);
        errorSpinner = requireView().findViewById(R.id.no_lineal_execution_error_spinner);
        resultsList  = requireView().findViewById(R.id.no_lineal_execution_list);
        results      = new ArrayList<>();

        setResultsList();
        setErrorSpinner();
        setOptions();
        setSolveFunction();
        setTitle();
    }

    private void setResultsList(){
        adapter = new NoLinealResultAdapter(requireContext(), results);
        resultsList.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false));
        resultsList.setAdapter(adapter);
    }

    private void setErrorSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.errors,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        errorSpinner.setAdapter(adapter);
    }

    private void setOptions(){
        XFLayout.setVisibility(View.GONE);
        DXLayout.setVisibility(View.GONE);
        GXLayout.setVisibility(View.GONE);
        result.setVisibility(View.GONE);
    }

    private void setTitle(){
        title.setText(R.string.multiple_roots);
    }

    private void setSolveFunction(){
        solve.setOnClickListener(v -> {
            KeyboardManager.hide(requireContext(), FXText.getWindowToken());
            results.clear();
            adapter.updateResults(results);
            results.add("Iteración   |   Resultado   |   Error");
            try {
                double xi         = Double.parseDouble(XIText.getText().toString());
                String fx         = FXText.getText().toString();
                int    i_max      = Integer.parseInt(IMAXText.getText().toString());
                double tol        = Double.parseDouble(ERRORText.getText().toString());
                boolean errorType = errorSpinner.getSelectedItem().toString().equals("E");

                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(10);
                df.setMinimumFractionDigits(10);

                final double[] resultValue = {0};

                boolean worked = MultipleRoots.calculate(fx, xi, i_max, tol, errorType, (i, x, e) -> {
                    results.add(i + "   |   " + df.format(x) + "   |   " + df.format(e));
                    resultValue[0] = x;
                });

                if(worked){
                    adapter.updateResults(results);
                    result.setVisibility(View.VISIBLE);
                    resultText.setText(String.valueOf(resultValue[0]));
                } else {
                    ToastMaker.show("No se llegó a una solución satisfactoria.");
                    result.setVisibility(View.GONE);
                    results.clear();
                    adapter.updateResults(results);
                }

            } catch (Exception e){
                ToastMaker.show("Hay un error con los parámetros de entrada, por favor revísalos.");
            }
        });
    }
}