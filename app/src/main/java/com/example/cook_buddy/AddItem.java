package com.example.cook_buddy;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

import java.sql.Ref;
import java.util.HashMap;
import java.util.Map;

public class AddItem extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = database.getReference("items");

    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        add = findViewById(R.id.add);
        add.setOnClickListener(this);
    }

    public static class Item {

        public String name;
        public Integer quantity;
        public Integer calories;

        public Item(String vname, Integer vquantity, Integer vcalories) {
            name = vname;
            quantity = vquantity;
            calories = vcalories;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:

                EditText eName = findViewById(R.id.name);
                String name = eName.getText().toString();
                EditText eQuantity = findViewById(R.id.quantity);
                Integer quantity = Integer.parseInt(String.valueOf(eQuantity.getText()));
                EditText eCalories = findViewById(R.id.calories);
                Integer calories = Integer.parseInt(String.valueOf(eCalories.getText()));

                Log.d("hi", name);
                Log.d("hi", String.valueOf(quantity));
                Log.d("hi", String.valueOf(calories));

                Map<String, Item> items = new HashMap<>();

                items.put("chocolate", new Item("Chocolate", 7, 230));
                items.put("carrot", new Item("Carrot", 10, 150));
                items.put("cucumber", new Item("Cucumber", 7, 100));
                items.put("tomato", new Item("Tomato", 11, 110));
                items.put("olive", new Item("Olive", 31, 200));
                items.put("milk", new Item("Milk", 8, 180));
                items.put("paneer", new Item("Paneer", 2, 330));
                items.put("lemon", new Item("Lemon", 10, 10));
                items.put("cheese", new Item("Cheese", 4, 330));
                items.put("rice", new Item("Rice", 10, 180));
                items.put("noodle", new Item("Noodle", 5, 200));
                items.put("spice", new Item("Spice", 21, 40));
                items.put(name, new Item(name, quantity, calories));

                dbRef.setValue(items);

                Log.d("hi", "Item Added");
                break;
        }
    }
}