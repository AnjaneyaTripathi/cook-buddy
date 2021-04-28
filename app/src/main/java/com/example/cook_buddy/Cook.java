package com.example.cook_buddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
    DatabaseReference ref = database.getReference().child("recipes");

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
    }

    private void getData() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long size = snapshot.getChildrenCount();
                ArrayList<Recipe> recipes = new ArrayList<>();
                for(long i=0; i<size; i++){
                    String name = snapshot.child(String.valueOf(i)).child("name").getValue().toString();
                    int calories = Integer.parseInt(snapshot.child(String.valueOf(i)).child("calories").getValue().toString());
                    long num = snapshot.child(String.valueOf(i)).child("items").getChildrenCount();
                    ArrayList<String> items = new ArrayList<>();
                    for(long j=0; j<num; j++){
                        items.add(snapshot.child(String.valueOf(i)).child("items").child(String.valueOf(i)).getValue().toString());
                    }
                    recipes.add(new Recipe(name, items, calories));
                }
                Toast.makeText(Cook.this, "Data Retrieved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Cook.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}