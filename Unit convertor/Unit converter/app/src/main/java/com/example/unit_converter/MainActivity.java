package com.example.unit_converter;



import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerFrom, spinnerTo;
    private Button buttonConvert;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editTextValue = findViewById(R.id.input_value);
        spinnerFrom = findViewById(R.id.input_spinner);
        spinnerTo = findViewById(R.id.output_spinner);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.output_value);

        // Array of units
        String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};

        // Create adapter for spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set adapter to both spinners
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        // Set onClick listener for Convert button
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        // Get user input value
        String inputValue = editTextValue.getText().toString();
        if (inputValue.isEmpty()) {
            textViewResult.setText("Please enter a value.");
            return;
        }

        // Parse the input value as double
        double value = Double.parseDouble(inputValue);

        // Get selected units from the spinners
        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        // Convert the value
        double convertedValue = convert(value, fromUnit, toUnit);

        // Display the result
        textViewResult.setText(String.format("Result: %.2f %s", convertedValue, toUnit));
    }

    private double convert(double value, String fromUnit, String toUnit) {
        // Convert input value to meters
        double inMeters = 0;
        switch (fromUnit) {
            case "Feet":
                inMeters = value * 0.3048;
                break;
            case "Inches":
                inMeters = value * 0.0254;
                break;
            case "Centimeters":
                inMeters = value * 0.01;
                break;
            case "Meters":
                inMeters = value;
                break;
            case "Yards":
                inMeters = value * 0.9144;
                break;
        }

        // Convert meters to the target unit
        double result = 0;
        switch (toUnit) {
            case "Feet":
                result = inMeters / 0.3048;
                break;
            case "Inches":
                result = inMeters / 0.0254;
                break;
            case "Centimeters":
                result = inMeters / 0.01;
                break;
            case "Meters":
                result = inMeters;
                break;
            case "Yards":
                result = inMeters / 0.9144;
                break;
        }

        return result;
    }
}