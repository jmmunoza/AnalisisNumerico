package com.jmmunoza.analisisnumerico.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmmunoza.analisisnumerico.R;

import java.util.ArrayList;

public class NoLinealResultAdapter extends RecyclerView.Adapter<NoLinealResultAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<String> results;

    public NoLinealResultAdapter(Context context, ArrayList<String> results) {
        this.inflater = LayoutInflater.from(context);
        this.results  = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.fragment_no_lineal_execution_result, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String result = results.get(position);
        holder.result.setText(result);
    }

    public void updateResults(ArrayList<String> results){
        this.results = results;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView result;

        ViewHolder(View itemView) {
            super(itemView);
            result  = itemView.findViewById(R.id.no_lineal_execution_result_item_text);
        }
    }
}