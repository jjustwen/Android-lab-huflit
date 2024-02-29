package com.example.buoi07;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.buoi07.Department;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SpinnerActivity extends AppCompatActivity {
    Spinner snDepartC;
    Spinner snDepartNewC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        snDepartC = findViewById(R.id.snDepartL);

        // Creating an array of strings for the first Spinner
        String[] arrType = new String[]{"Khu 1", "Khu 2", "Khu 3"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrType);
        snDepartC.setAdapter(arrayAdapter);

        snDepartNewC = findViewById(R.id.snDepartNew);

        // Retrieve the list of departments from your data source
        ArrayList<Department> lstDepart = DepartmentDataQuery.getAll(this);

        // Set up adapter for snDepartNewC
        ArrayAdapter<com.example.buoi07.Department> departmentArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lstDepart);
        departmentArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snDepartNewC.setAdapter(departmentArrayAdapter);
    }
}
