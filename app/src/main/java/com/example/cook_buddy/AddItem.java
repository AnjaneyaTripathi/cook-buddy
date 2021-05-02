package com.example.cook_buddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Ref;
import java.util.HashMap;
import java.util.Map;

public class AddItem extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "com.example.cook_buddy";
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child("items");

    private Button add;
    public long size;
    public boolean flag = false;

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

        public Item() {
        }

        public Item(String vname, Integer vquantity, Integer vcalories) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:

                EditText eName = findViewById(R.id.name);
                String tname = eName.getText().toString();
                EditText eQuantity = findViewById(R.id.quantity);
                Integer tquantity = Integer.parseInt(String.valueOf(eQuantity.getText()));
                EditText eCalories = findViewById(R.id.calories);
                Integer tcalories = Integer.parseInt(String.valueOf(eCalories.getText()));

                Log.d("hi", tname);
                Log.d("hi", String.valueOf(tquantity));
                Log.d("hi", String.valueOf(tcalories));

                Map<String, Item> items = new HashMap<>();

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(!flag) {
                            flag = true;
                            size = snapshot.getChildrenCount();
                            for (long i = 0; i < size; i++) {
                                String name = snapshot.child(String.valueOf(i)).child("name").getValue().toString();
                                int calories = Integer.parseInt(snapshot.child(String.valueOf(i)).child("calories").getValue().toString());
                                int quantity = Integer.parseInt(snapshot.child(String.valueOf(i)).child("quantity").getValue().toString());
                                items.put(String.valueOf(i), new Item(name, quantity, calories));
                            }
//                                                    items.put("0", new Item("Chocolate", 7, 230));
//                                                    items.put("1", new Item("Carrot", 10, 150));
//                                                    items.put("2", new Item("Cucumber", 7, 100));
//                                                    items.put("3", new Item("Tomato", 11, 110));
//                                                    items.put("4", new Item("Olive", 31, 200));
//                                                    items.put("5", new Item("Milk", 8, 180));
//                                                    items.put("6", new Item("Paneer", 2, 330));
//                                                    items.put("7", new Item("Lemon", 10, 10));
//                                                    items.put("8", new Item("Cheese", 4, 330));
//                                                    items.put("9", new Item("Rice", 10, 180));
//                                                    items.put("10", new Item("Noodle", 5, 200));
//                                                    items.put("11", new Item("Spice", 21, 40));
                            items.put(String.valueOf(size), new Item(tname, tquantity, tcalories));

                            ref.setValue(items);

                            Log.d("hi", "Item Added");

                            Intent intent1 = new Intent(AddItem.this, MainActivity.class);
                            startActivity(intent1);

                            //Toast.makeText(AddItem.this, "Data Retrieved", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddItem.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
                    }
                });

                break;
        }
    }
}