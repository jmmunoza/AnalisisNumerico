package com.jmmunoza.analisisnumerico.view.fragments;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.StepMode;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.jmmunoza.analisisnumerico.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FragmentTest extends Fragment {


    public FragmentTest(){

    }

    @SuppressLint("InflateParams") @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        postponeEnterTransition(1, TimeUnit.MILLISECONDS);
        return inflater.inflate(R.layout.prueba_layout, null);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadComponents();
    }


    private XYPlot plot;

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

    private void loadComponents(){


        plot.setDomainStep(StepMode.INCREMENT_BY_VAL, 1);
        plot.setRangeStep(StepMode.INCREMENT_BY_VAL, 1);

        plot.centerOnDomainOrigin(0);
        plot.centerOnRangeOrigin(0);

        // create formatters to use for drawing a series using LineAndPointRenderer
        // and configure them from xml:
        LineAndPointFormatter series1Format = new LineAndPointFormatter(requireContext(), R.xml.eee);

        // use our custom renderer to make origin labels red
        plot.getGraph().setLineLabelRenderer(XYGraphWidget.Edge.BOTTOM, new MyLineLabelRenderer());
        plot.getGraph().setLineLabelRenderer(XYGraphWidget.Edge.LEFT, new MyLineLabelRenderer());

        // skip every other line for top and right edge labels
        plot.getGraph().setLineLabelRenderer(XYGraphWidget.Edge.RIGHT, new MySecondaryLabelRenderer());
        plot.getGraph().setLineLabelRenderer(XYGraphWidget.Edge.TOP, new MySecondaryLabelRenderer());

        // don't show decimal places for top and right edge labels
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.TOP).setFormat(new DecimalFormat("0"));
        plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.RIGHT).setFormat(new DecimalFormat("0"));

        // create a dash effect for domain and range grid lines:
        DashPathEffect dashFx = new DashPathEffect(
                new float[] {PixelUtils.dpToPix(3), PixelUtils.dpToPix(3)}, 0);
        plot.getGraph().getDomainGridLinePaint().setPathEffect(dashFx);
        plot.getGraph().getRangeGridLinePaint().setPathEffect(dashFx);

        // add a new series' to the xyplot:
        plot.addSeries(generateSeries(-5, 5, 100), series1Format);
    }

    protected XYSeries generateSeries(double minX, double maxX, double resolution) {
        final double range = maxX - minX;
        final double step = range / resolution;
        List<Number> xVals = new ArrayList<>();
        List<Number> yVals = new ArrayList<>();

        double x = minX;
        while (x <= maxX) {
            xVals.add(x);
            yVals.add(fx(x));
            x +=step;
        }

        return new SimpleXYSeries(xVals, yVals, "f(x) = sin(x)");
    }

    protected double fx(double x) {
        return Math.sin(x);
    }

}