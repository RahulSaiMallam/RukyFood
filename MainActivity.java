package com.example.rukyfood;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText foodTypeEditText, quantityEditText, locationEditText;
    private Button submitButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("SurplusFood");

        // Link UI elements
        foodTypeEditText = findViewById(R.id.foodTypeEditText);
        quantityEditText = findViewById(R.id.quantityEditText);
        locationEditText = findViewById(R.id.locationEditText);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postFoodSurplus();
            }
        });
    }

    private void postFoodSurplus() {
        String foodType = foodTypeEditText.getText().toString().trim();
        String quantity = quantityEditText.getText().toString().trim();
        String location = locationEditText.getText().toString().trim();

        if (!foodType.isEmpty() && !quantity.isEmpty() && !location.isEmpty()) {
            String id = databaseReference.push().getKey();
            SurplusFood food = new SurplusFood(id, foodType, quantity, location);
            databaseReference.child(id).setValue(food).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Food posted successfully!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to post food. Please try again.", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_LONG).show();
        }
    }
}
