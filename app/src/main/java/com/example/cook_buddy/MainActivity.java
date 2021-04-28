package com.example.cook_buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button cook, see, track, add;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = database.getReference("recipes");

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cook = findViewById(R.id.cook);
        see = findViewById(R.id.see);
        track = findViewById(R.id.track);
        add = findViewById(R.id.add);

        cook.setOnClickListener(this);
        see.setOnClickListener(this);
        track.setOnClickListener(this);
        add.setOnClickListener(this);

        Map<String, Recipe> recipes = new HashMap<>();

        ArrayList<String> chocolateMilk = new ArrayList<String>();
        chocolateMilk.add("chocolate");
        chocolateMilk.add("milk");
        recipes.put("0", new Recipe("Chocolate Milk", chocolateMilk, 230));

        ArrayList<String> salad = new ArrayList<String>();
        salad.add("carrot");
        salad.add("cucumber");
        salad.add("tomato");
        salad.add("olive");
        recipes.put("1", new Recipe("Salad", salad, 150));

        dbRef.setValue(recipes);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cook:
                Intent intent1 = new Intent(this, Cook.class);
                startActivity(intent1);
                Log.d("hi", "cook");
                break;
            case R.id.see:
//                Intent intent2 = new Intent(this, View.class);
//                startActivity(intent2);
                Log.d("hi", "see");
                break;
            case R.id.track:
//                Intent intent3 = new Intent(this, TrackCalories.class);
//                startActivity(intent3);
                Log.d("hi", "track");
                break;
            case R.id.add:
                Log.d("hi", "add");
                Intent intent4 = new Intent(this, AddItem.class);
                startActivity(intent4);
                break;
        }
    }
}