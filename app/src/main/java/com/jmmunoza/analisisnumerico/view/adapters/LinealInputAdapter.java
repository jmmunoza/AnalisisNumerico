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

public class LinealInputAdapter  extends RecyclerView.Adapter<LinealInputAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<String> inputs;
    private boolean firstDark;
    private boolean isVector;

    public LinealInputAdapter(Context context, boolean firstDark, boolean isVector) {
        inflater       = LayoutInflater.from(context);
        inputs         = new ArrayList<>();
        this.firstDark = firstDark;
        this.isVector  = isVector;

        if(!isVector){
            inputs.add("0");
            inputs.add("0");
        }

        inputs.add("0");
        inputs.add("0");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.fragment_lineal_execution_input, parent, false));
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
                if(position < inputs.size()) inputs.set(position, s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.text.setHintTextColor(App.getContext().getResources().getColor(R.color.gray_4));
        if(getMatrixSize() % 2 == 0){
            if(position % 2 == 0){
                holder.card.setBackground(App.getContext().getDrawable(R.drawable.gradient_4));
                holder.text.setTextColor(App.getContext().getResources().getColor(R.color.white));
                holder.text.setBackgroundTintList(ColorStateList.valueOf(App.getContext().getResources().getColor(R.color.white)));
            } else {
                holder.card.setBackground(null);
                holder.card.setCardBackgroundColor(App.getContext().getResources().getColor(R.color.gray_1));
                holder.text.setBackgroundTintList(ColorStateList.valueOf(App.getContext().getResources().getColor(R.color.white)));
                holder.text.setTextColor(App.getContext().getResources().getColor(R.color.black));
            }

        } else {
            if(position % 2 == 0){
                holder.card.setBackground(null);
                holder.card.setCardBackgroundColor(App.getContext().getResources().getColor(R.color.gray_1));
                holder.text.setBackgroundTintList(ColorStateList.valueOf(App.getContext().getResources().getColor(R.color.white)));
                holder.text.setTextColor(App.getContext().getResources().getColor(R.color.black));
            } else {
                holder.card.setBackground(App.getContext().getDrawable(R.drawable.gradient_4));
                holder.text.setBackgroundTintList(ColorStateList.valueOf(App.getContext().getResources().getColor(R.color.white)));
                holder.text.setTextColor(App.getContext().getResources().getColor(R.color.white));
            }
        }

    }

    public int getMatrixSize(){
        return (int) Math.sqrt(inputs.size());
    }

    public void addInput(){
        if(!isVector){
            int size = (int) Math.sqrt(inputs.size());
            inputs.clear();
            for(int i = 0; i < (int)Math.pow(size+1, 2); i++) inputs.add("0");
        } else {
            int size = inputs.size();
            inputs.clear();
            for(int i = 0; i < size + 1; i++) inputs.add("0");
        }

        notifyDataSetChanged();
    }

    public void deleteInput(){
        if(!isVector){
            int size = (int) Math.sqrt(inputs.size());
            if(size > 2){
                inputs.clear();
                for(int i = 0; i < (int)Math.pow(size-1, 2); i++) inputs.add("0");
                notifyDataSetChanged();
            }
        } else {
            int size = inputs.size();
            if(size > 2){
                inputs.clear();
                for(int i = 0; i < size-1; i++) inputs.add("0");
                notifyDataSetChanged();
            }
        }

    }

    public double[][] getMatrix(){
        int size = (int)Math.sqrt(inputs.size());
        double[][] numbers = new double[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                double number = Double.parseDouble(inputs.get(j + i * size));
                numbers[i][j] = number;
            }
        }

        return numbers;
    }

    public double[] getVector(){
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
            card  = itemView.findViewById(R.id.lineal_execution_input_card);
            text  = itemView.findViewById(R.id.lineal_execution_input_text);
        }
    }
}