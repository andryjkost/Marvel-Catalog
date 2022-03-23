package com.example.exampl_r;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;


public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumberViewHolder> {
    private ArrayList<String> mass;//здесь массив элементов списка героев
    private LayoutInflater layoutInflater;

    public NumbersAdapter(Context context, ArrayList<String> mass){
        this.layoutInflater = LayoutInflater.from(context);
        this.mass = mass;
    }
    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.number_list_item, parent, false);
        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.item.setText(mass.get(position));


    }

    @Override
    public int getItemCount() {
        return mass.size();
    }

    public void filterList(ArrayList<String> moment_list){
        mass = moment_list;
        notifyDataSetChanged();
    }






    class NumberViewHolder extends RecyclerView.ViewHolder {

        TextView item;
        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), Element.class);
                    i.putExtra("new_element", mass.get(getAdapterPosition()));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(i);
                }
            });

            item = itemView.findViewById(R.id.item_1);


        }


    }


}
