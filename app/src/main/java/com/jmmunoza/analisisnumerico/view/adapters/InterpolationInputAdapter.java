package com.jmmunoza.analisisnumerico.view.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jmmunoza.analisisnumerico.App;
import com.jmmunoza.analisisnumerico.R;

import java.util.ArrayList;

public class InterpolationInputAdapter extends RecyclerView.Adapter<InterpolationInputAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<String> inputs;
    private boolean firstDark;


    public InterpolationInputAdapter(Context context, boolean firstDark) {
        inflater       = LayoutInflater.from(context);
        inputs         = new ArrayList<>();
        this.firstDark = firstDark;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.fragment_interpolation_execution_input, parent, false));
    }

    @SuppressLint("ResourceAsColor") @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String input = inputs.get(position);
        holder.text.setText(input);
        holder.text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputs.set(position, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.text.setHintTextColor(App.getContext().getResources().getColor(R.color.gray_4));
        if(firstDark){
            if(position % 2 == 0){
                holder.card.setCardBackgroundColor(App.getContext().getResources().getColor(R.color.black));
                holder.text.setTextColor(App.getContext().getResources().getColor(R.color.white));
                holder.text.setBackgroundTintList(ColorStateList.valueOf(App.getContext().getResources().getColor(R.color.black)));
            } else {
                holder.card.setCardBackgroundColor(App.getContext().getResources().getColor(R.color.white));
                holder.text.setBackgroundTintList(ColorStateList.valueOf(App.getContext().getResources().getColor(R.color.white)));
                holder.text.setTextColor(App.getContext().getResources().getColor(R.color.black));
            }
        } else {
            if(position % 2 == 0){
                holder.card.setCardBackgroundColor(App.getContext().getResources().getColor(R.color.white));
                holder.text.setBackgroundTintList(ColorStateList.valueOf(App.getContext().getResources().getColor(R.color.white)));
                holder.text.setTextColor(App.getContext().getResources().getColor(R.color.black));
            } else {
                holder.card.setCardBackgroundColor(App.getContext().getResources().getColor(R.color.black));
                holder.text.setBackgroundTintList(ColorStateList.valueOf(App.getContext().getResources().getColor(R.color.black)));
                holder.text.setTextColor(App.getContext().getResources().getColor(R.color.white));
            }
        }

    }

    public void addInput(String input){
        inputs.add(input);
        notifyItemInserted(inputs.size()-1);
    }

    public void deleteInput(){
        if(inputs.size() > 2){
            inputs.remove(inputs.size()-1);
            notifyItemRemoved(inputs.size());
        }
    }

    public double[] getNumbers(){
        double[] numbers = new double[inputs.size()];
        for(int i = 0; i < inputs.size(); i++){
            double number = Double.parseDouble(inputs.get(i));
            numbers[i] = number;
        }

        return numbers;
    }

    @Override
    public int getItemCount() {
        return inputs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText text;
        CardView card;

        ViewHolder(View itemView) {
            super(itemView);
            card  = itemView.findViewById(R.id.interpolation_execution_input_card);
            text  = itemView.findViewById(R.id.interpolation_execution_input_text);
        }
    }
}