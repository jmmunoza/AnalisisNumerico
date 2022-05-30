package com.jmmunoza.analisisnumerico.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmmunoza.analisisnumerico.R;

public class InterpolationResultAdapter extends RecyclerView.Adapter<InterpolationResultAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private double[]    x;
    private double[]    y;

    public InterpolationResultAdapter(Context context, double[] x, double[] y) {
        this.inflater = LayoutInflater.from(context);
        this.x        = x;
        this.y        = y;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.fragment_interpolation_execution_result, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        double x_value = x[position];
        double y_value = y[position];
        holder.result.setText(String.valueOf(x_value) + "    |    " + String.valueOf(y_value));
    }

    public void updateResults(double[] x, double[] y){
        this.x        = x;
        this.y        = y;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return x.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView result;

        ViewHolder(View itemView) {
            super(itemView);
            result  = itemView.findViewById(R.id.interpolation_execution_result_item_text);
        }
    }
}