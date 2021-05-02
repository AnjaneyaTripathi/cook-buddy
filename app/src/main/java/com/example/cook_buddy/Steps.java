package com.example.cook_buddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class Steps extends AppCompatActivity {

    private TextView tTitle, tItems, tProc, tCals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        String procedure = getIntent().getStringExtra("procedure");
        String title = getIntent().getStringExtra("title");
        ArrayList<String> items = getIntent().getStringArrayListExtra("ingredients");
        Integer calories = getIntent().getIntExtra("calories", 0);

        Log.d("oops", title);

        tTitle = (TextView) findViewById(R.id.title);
        tCals = (TextView) findViewById(R.id.calories);
        tItems = (TextView) findViewById(R.id.ingredients);
        tProc = (TextView) findViewById(R.id.proc);

        StringBuilder res = new StringBuilder("Ingredients: ");
        int i;
        for(i=0; i<items.size()-1; i++) {
            res.append(items.get(i) + ", ");
            Log.d("yikes", items.get(i));
        }
        res.append(items.get(i));

        tTitle.setText(title);
        tCals.setText("Calories: " + String.valueOf(calories));
        tItems.setText(res);
        tProc.setText(procedure);

    }
}