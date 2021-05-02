package com.example.cook_buddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ExampleViewHolder> {
    private ArrayList<FoodItem> mExampleList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView0;
        public TextView mTextView1;
        public TextView mTextView2;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView0 = itemView.findViewById(R.id.name);
            mTextView1 = itemView.findViewById(R.id.quantity);
            mTextView2 = itemView.findViewById(R.id.calories);
        }
    }

    public FoodAdapter(ArrayList<FoodItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        FoodItem currentItem = mExampleList.get(position);

        holder.mTextView0.setText(currentItem.getName());
        holder.mTextView1.setText(String.valueOf(currentItem.getQuantity()));
        holder.mTextView2.setText(String.valueOf(currentItem.getCalories()));
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
