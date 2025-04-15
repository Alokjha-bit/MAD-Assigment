package com.example.theme_unit_converter;


import android.os.Bundle;
import android.content.Intent;
import com.example.theme_unit_converter.SettingsActivity;
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
    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextValue = findViewById(R.id.input_value);
        spinnerFrom = findViewById(R.id.input_spinner);
        spinnerTo = findViewById(R.id.output_spinner);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.output_value);
        settingsButton = findViewById(R.id.settingsbutton);

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fixed here
                Intent intent = new Intent(MainActivity.this, com.example.theme_unit_converter.SettingsActivity.class);
                startActivity(intent);
            }
        });

        String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String inputValue = editTextValue.getText().toString();
        if (inputValue.isEmpty()) {
            textViewResult.setText("Please enter a value.");
            return;
        }

        double value = Double.parseDouble(inputValue);
        String fromUnit = spinnerFrom.getSelectedItem().toString();
        String toUnit = spinnerTo.getSelectedItem().toString();

        double convertedValue = convert(value, fromUnit, toUnit);

        textViewResult.setText(String.format("Result: %.2f %s", convertedValue, toUnit));
    }

    private double convert(double value, String fromUnit, String toUnit) {
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