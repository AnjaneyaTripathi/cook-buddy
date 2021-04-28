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

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("recipes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);

        getData();
    }

    public static class Recipe {

        public String name;
        public ArrayList<String> items;
        public Integer calories;

        public Recipe(String vname, ArrayList<String> vitems, Integer vcalories) {
            name = vname;
            items = vitems;
            calories = vcalories;
        }
    }

    private void getData() {

        // calling add value event listener method
        // for getting the values from database.

//        Query phoneQuery = ref.equalTo("recipes");
//        phoneQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
//                    Recipe user = singleSnapshot.getValue(Recipe.class);
//                    Log.d("hi", user.name);
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d("hi", "onCancelled", databaseError.toException());
//            }
//        });
        
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Recipe recipe = snapshot.getValue(Recipe.class);
                Toast.makeText(Cook.this, "Data is retrieved", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Cook.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}