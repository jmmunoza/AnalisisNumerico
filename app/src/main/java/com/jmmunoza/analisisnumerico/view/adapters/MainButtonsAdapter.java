package com.jmmunoza.analisisnumerico.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.AlignSelf;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.jmmunoza.analisisnumerico.App;
import com.jmmunoza.analisisnumerico.R;
import com.jmmunoza.analisisnumerico.view.fragments.FragmentMain;

import java.util.ArrayList;

public class MainButtonsAdapter extends RecyclerView.Adapter<MainButtonsAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final ArrayList<FragmentMain.MainButton> buttons;
    private final boolean gridView;

    public MainButtonsAdapter(Context context, ArrayList<FragmentMain.MainButton> buttons, boolean gridView) {
        this.inflater = LayoutInflater.from(context);
        this.buttons  = buttons;
        this.gridView = gridView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(gridView)
            view = inflater.inflate(R.layout.fragment_main_button_grid, parent, false);
        else
            view = inflater.inflate(R.layout.fragment_main_button, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FragmentMain.MainButton button = buttons.get(position);
        holder.title.setText(button.getTitle());
        holder.layout.setBackgroundResource(button.getBackground());
        holder.image.setImageResource(button.getImage());
    }

    @Override
    public int getItemCount() {
        return buttons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView         title;
        ImageView        image;
        ConstraintLayout layout;

        ViewHolder(View itemView) {
            super(itemView);

            title   = itemView.findViewById(R.id.main_button_title);
            image   = itemView.findViewById(R.id.main_button_image);
            layout  = itemView.findViewById(R.id.main_button_layout);

            layout.setOnClickListener(v -> buttons.get(getAdapterPosition()).onClick());
        }
    }
}