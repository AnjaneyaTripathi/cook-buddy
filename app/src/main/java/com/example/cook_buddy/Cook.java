package com.example.cook_buddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Cook extends AppCompatActivity {

    private static final String TAG = "com.example.cook_buddy";
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    private RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<Recipe> recipes = new ArrayList<>();
    public ArrayList<Food> foods = new ArrayList<>();
    public ArrayList<RecipeItem> exampleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);

        getData();

    }

    public static class Recipe {

        public String name;
        public ArrayList<String> items;
        public int calories;

        public Recipe() {
        }

        public Recipe(String vname, ArrayList<String> vitems, Integer vcalories) {
            name = vname;
            items = vitems;
            calories = vcalories;
        }

        public String getName() {
            return name;
        }

        public ArrayList<String> getItems() {
            return items;
        }

        public int getCalories() {
            return calories;
        }
    }

    public static class Food {

        public String name;
        public Integer quantity;
        public int calories;

        public Food() {
        }

        public Food(String vname, Integer vquantity, Integer vcalories) {
            name = vname;
            quantity = vquantity;
            calories = vcalories;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getCalories() {
            return calories;
        }
    }

    private void getData() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long num=0;
                long size_r = snapshot.child("recipes").getChildrenCount();
                for(long i=0; i<size_r; i++){
                    String name = snapshot.child("recipes").child(String.valueOf(i)).child("name").getValue().toString();
                    int calories = Integer.parseInt(snapshot.child("recipes").child(String.valueOf(i)).child("calories").getValue().toString());
                    num = snapshot.child("recipes").child(String.valueOf(i)).child("items").getChildrenCount();
                    ArrayList<String> items = new ArrayList<>();
                    for(long j=0; j<num; j++){
                        items.add(snapshot.child("recipes").child(String.valueOf(i)).child("items").child(String.valueOf(j)).getValue().toString());
                    }
                    recipes.add(new Recipe(name, items, calories));
                }

                long size_i = snapshot.child("items").getChildrenCount();
                for(long i=0; i<size_i; i++){
                    String name = snapshot.child("items").child(String.valueOf(i)).child("name").getValue().toString();
                    int calories = Integer.parseInt(snapshot.child("items").child(String.valueOf(i)).child("calories").getValue().toString());
                    int quantity = Integer.parseInt(snapshot.child("items").child(String.valueOf(i)).child("quantity").getValue().toString());
                    foods.add(new Food(name, quantity, calories));
                }
                Log.d("hi", String.valueOf(foods.size()));
                for(long i=0; i<size_r; i++) {
                    int cnt = 0;
                    int x = recipes.get((int) i).items.size();
                    for(long j=0; j<x; j++) {
                        //Log.d("hi", recipes.get((int) i).items.get((int) j));
                        for(int k=0; k<size_i; k++) {
                            //Log.d("hi", foods.get(k).getName());
                            if(foods.get(k).getName().equals(recipes.get((int) i).items.get((int) j))) {
                                cnt = cnt + 1;
                                Log.d("hi", recipes.get((int) i).items.get((int) j));
                            }
                        }
                    }
                    if(cnt == x) {
                        exampleList.add(new RecipeItem(recipes.get((int) i).getName(), recipes.get((int) i).getCalories()));
                    }
                }

                mRecyclerView = findViewById(R.id.recyclerView);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(Cook.this);
                mAdapter = new RecipeAdapter(exampleList);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

                Toast.makeText(Cook.this, "Data Retrieved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Cook.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}