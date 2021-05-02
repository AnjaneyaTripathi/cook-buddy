package com.example.cook_buddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class See extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<Food> foods = new ArrayList<>();
    public ArrayList<FoodItem> exampleList = new ArrayList<>();
    public long size;

    private static final String TAG = "com.example.cook_buddy";
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("items");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        getData();
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
                size = snapshot.getChildrenCount();
                for(long i=0; i<size; i++){
                    String name = snapshot.child(String.valueOf(i)).child("name").getValue().toString();
                    int calories = Integer.parseInt(snapshot.child(String.valueOf(i)).child("calories").getValue().toString());
                    int quantity = Integer.parseInt(snapshot.child(String.valueOf(i)).child("quantity").getValue().toString());
                    foods.add(new Food(name, quantity, calories));
                }

                for(long i=0; i<size; i++) {
                    exampleList.add(new FoodItem(foods.get((int) i).getName(), foods.get((int) i).getQuantity(), foods.get((int) i).getCalories()));
                }

                mRecyclerView = findViewById(R.id.recyclerView);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(See.this);
                mAdapter = new FoodAdapter(exampleList);

                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

                Toast.makeText(See.this, "Data Retrieved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(See.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}