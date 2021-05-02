package com.example.cook_buddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cook_buddy.R;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ExampleViewHolder> {
    private ArrayList<RecipeItem> mExampleList;
    private AdapterView.OnItemClickListener mListener;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView0;
        public TextView mTextView2;
        private CardView cardView;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView0 = itemView.findViewById(R.id.name);
            mTextView2 = itemView.findViewById(R.id.calories);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }

    public RecipeAdapter(ArrayList<RecipeItem> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        RecipeItem currentItem = mExampleList.get(position);

        holder.mTextView0.setText(currentItem.getName());
        holder.mTextView2.setText(String.valueOf(currentItem.getCalories()));
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}

//public class ViewGatesAdapter extends RecyclerView.Adapter<ViewGatesAdapter.MyViewHolder>{
//    private List<TollGate> gateList;
//    private OnItemClickListener mListener;
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        mListener = listener;
//    }
//    public ViewGatesAdapter(ArrayList<TollGate> gateList){
//        this.gateList = gateList;
//    }
//    @Override
//    public ViewGatesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.toll_gate_item_layout,parent,false);
//        return new MyViewHolder(view, mListener);
//    }
//    @Override
//    public void onBindViewHolder(ViewGatesAdapter.MyViewHolder holder, final int position) {
//        final TollGate tollGate = gateList.get(position);
//        holder.name.setText(tollGate.getName());
//        holder.city.setText(tollGate.getCity());
//    }
//    @Override
//    public int getItemCount() {
//        return gateList.size();
//    }
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        private TextView name;
//        private TextView city;
//        private CardView cardView;
//        public MyViewHolder(View itemView, final OnItemClickListener listener) {
//            super(itemView);
//            name = itemView.findViewById(R.id.gateName);
//            city = itemView.findViewById(R.id.gateCity);
//            cardView = itemView.findViewById(R.id.cardView);
//
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listener != null) {
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION) {
//                            listener.onItemClick(position);
//                        }
//                    }
//                }
//            });
//
//        }
//    }
//}
