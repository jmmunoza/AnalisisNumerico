package com.jmmunoza.analisisnumerico.view.fragments.nolineal;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
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

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.StepMode;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.jmmunoza.analisisnumerico.R;
import com.jmmunoza.analisisnumerico.numericalmethods.F;
import com.jmmunoza.analisisnumerico.numericalmethods.nolineal.IncrementalSearch;
import com.jmmunoza.analisisnumerico.util.KeyboardManager;
import com.jmmunoza.analisisnumerico.util.ToastMaker;
import com.jmmunoza.analisisnumerico.view.adapters.NoLinealResultAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FragmentIncrementalSearch extends Fragment {
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
    private EditText ERRORText;
    private EditText XFText;
    private EditText FXText;
    private EditText XIText;
    private EditText DXText;

    private TextView title;
    private TextView resultText;
    private TextView solve;

    private Spinner errorSpinner;
    private LineAndPointFormatter series1Format;

    private CardView result;
    private XYPlot plot;

    public FragmentIncrementalSearch(){

    }

    @SuppressLint("InflateParams") @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postponeEnterTransition(1, TimeUnit.MILLISECONDS);
        return inflater.inflate(R.layout.fragment_no_lineal_incremental_search, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadComponents();
    }

    private void loadComponents(){
        XFLayout     = requireView().findViewById(R.id.no_lineal_execution_xf);
        DXLayout     = requireView().findViewById(R.id.no_lineal_execution_dx);
        IMAXLayout   = requireView().findViewById(R.id.no_lineal_execution_i_max);
        ERRORLayout  = requireView().findViewById(R.id.no_lineal_execution_error);
        IMAXText     = requireView().findViewById(R.id.no_lineal_execution_limit_text);
        ERRORText    = requireView().findViewById(R.id.no_lineal_execution_error_text);
        XFText       = requireView().findViewById(R.id.no_lineal_execution_final_text);
        DXText       = requireView().findViewById(R.id.no_lineal_execution_dx_text);
        FXText       = requireView().findViewById(R.id.no_lineal_execution_function_text);
        XIText       = requireView().findViewById(R.id.no_lineal_execution_initial_text);
        title        = requireView().findViewById(R.id.no_lineal_execution_title);
        solve        = requireView().findViewById(R.id.no_lineal_solve);
        IMAXLayout   = requireView().findViewById(R.id.no_lineal_execution_i_max);
        result       = requireView().findViewById(R.id.no_lineal_result);
        resultText   = requireView().findViewById(R.id.no_lineal_result_text);
        GXLayout     = requireView().findViewById(R.id.no_lineal_execution_gx);
        GXText       = requireView().findViewById(R.id.no_lineal_execution_g_function_text);
        errorSpinner = requireView().findViewById(R.id.no_lineal_execution_error_spinner);
        resultsList  = requireView().findViewById(R.id.no_lineal_execution_list);
        plot         = requireView().findViewById(R.id.no_lineal_plot);
        results      = new ArrayList<>();

        setPlot();
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
        ERRORLayout.setVisibility(View.GONE);
        XFLayout.setVisibility(View.GONE);
        GXLayout.setVisibility(View.GONE);
        result.setVisibility(View.GONE);
    }

    /**
     * Custom line label renderer that highlights origin labels
     */
    class MyLineLabelRenderer extends XYGraphWidget.LineLabelRenderer {

        @Override
        protected void drawLabel(Canvas canvas, String text, Paint paint,
                                 float x, float y, boolean isOrigin) {
            if(isOrigin) {
                // make the origin labels red:
                final Paint originPaint = new Paint(paint);
                originPaint.setColor(Color.RED);
                super.drawLabel(canvas, text, originPaint, x, y , isOrigin);
            } else {
                super.drawLabel(canvas, text, paint, x, y , isOrigin);
            }
        }
    }

    /**
     * Draws every other tick label and renders text in gray instead of white.
     */
    class MySecondaryLabelRenderer extends MyLineLabelRenderer {


        @Override
        public void drawLabel(Canvas canvas, XYGraphWidget.LineLabelStyle style,
                              Number val, float x, float y, boolean isOrigin) {
            if(val.doubleValue() % 2 == 0) {
                final Paint paint = style.getPaint();
                if(!isOrigin) {
                    paint.setColor(Color.GRAY);
                }
                super.drawLabel(canvas, style, val, x, y, isOrigin);
            }
        }
    }

    private void setPlot(){
        plot.setDomainStep(StepMode.INCREMENT_BY_VAL, 1);
        plot.setRangeStep(StepMode.INCREMENT_BY_VAL, 1);

        plot.centerOnRangeOrigin(0);

        plot.getGraph().setLineLabelRenderer(XYGraphWidget.Edge.BOTTOM, new MyLineLabelRenderer());
        plot.getGraph().setLineLabelRenderer(XYGraphWidget.Edge.LEFT, new MyLineLabelRenderer());
        plot.getGraph().setLineLabelRenderer(XYGraphWidget.Edge.RIGHT, new MySecondaryLabelRenderer());
        plot.getGraph().setLineLabelRenderer(XYGraphWidget.Edge.TOP, new MySecondaryLabelRenderer());
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.TOP).setFormat(new DecimalFormat("0"));
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.RIGHT).setFormat(new DecimalFormat("0"));

        DashPathEffect dashFx = new DashPathEffect(new float[] {PixelUtils.dpToPix(3), PixelUtils.dpToPix(3)}, 0);
        plot.getGraph().getDomainGridLinePaint().setPathEffect(dashFx);
        plot.getGraph().getRangeGridLinePaint().setPathEffect(dashFx);
        series1Format = new LineAndPointFormatter(requireContext(), R.xml.eee);
    }

    protected XYSeries generateSeries(String f, double minX, double maxX, double resolution) {
        final double range = maxX - minX;
        final double step = range / resolution;
        List<Number> xVals = new ArrayList<>();
        List<Number> yVals = new ArrayList<>();

        double x = minX;
        while (x <= maxX) {
            double y = F.eval(f,x);
            if(Math.abs(y) <= 5){
                xVals.add(x);
                yVals.add(F.eval(f,x));
            }

            x +=step;
        }

        return new SimpleXYSeries(xVals, yVals, "f(x) = " + f);
    }

    private void setTitle(){
        title.setText(R.string.incremental_search);
    }

    private void setSolveFunction(){
        solve.setOnClickListener(v -> {
            KeyboardManager.hide(requireContext(), FXText.getWindowToken());
            results.clear();
            adapter.updateResults(results);
            results.add("Iteración   |   X inicial   |   X final");
            try {
                double xi         = Double.parseDouble(XIText.getText().toString());
                int    i_max      = Integer.parseInt(IMAXText.getText().toString());
                double dx         = Double.parseDouble(DXText.getText().toString());
                String fx         = FXText.getText().toString();

                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(10);
                df.setMinimumFractionDigits(10);

                final double[] resultValueXf = {0};
                final double[] resultValueXi = {0};

                boolean worked = IncrementalSearch.calculate(fx, xi, dx, i_max, (i, xi1, xf1) -> {
                    String result = "";
                    result += String.format("%1d", i);
                    result += "   |   ";
                    result += String.format("%6f", xi1);
                    result += "   |   ";
                    result += String.format("%6f", xf1);
                    results.add(result);
                    resultValueXi[0] = xi1;
                    resultValueXf[0] = xf1;
                });

                if(worked){
                    result.setVisibility(View.VISIBLE);
                    if(resultValueXf[0] == resultValueXi[0]){
                        resultText.setText(String.format("%6f", resultValueXi[0]));
                    } else {
                        resultText.setText("[ "+ String.format("%6f", resultValueXi[0]) + "   -   " + String.format("%6f", resultValueXf[0]) + " ]");

                    }

                    plot.clear();
                    plot.addSeries(generateSeries(fx, resultValueXi[0]-3, resultValueXf[0]+3, 500), series1Format);
                    plot.redraw();

                } else {
                    ToastMaker.show("No se llegó a una solución satisfactoria.");
                    result.setVisibility(View.GONE);
                    results.clear();
                }
                adapter.updateResults(results);
            } catch (Exception e){
                ToastMaker.show("Hay un error con los parámetros de entrada, por favor revísalos.");
            }
        });
    }
}
