package com.szubov.android_hw_42;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Button mBtnDeliveryOk;
    private Spinner mSpinnerCountries;
    private Spinner mSpinnerCities;
    private Spinner mSpinnerHouseNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mBtnDeliveryOk = findViewById(R.id.btnDeliveryOk);
        mSpinnerCountries = findViewById(R.id.spinnerCountries);
        mSpinnerCities = findViewById(R.id.spinnerCities);
        mSpinnerHouseNumbers = findViewById(R.id.spinnerHouseNumbers);

        ArrayAdapter mAdapterCountries = ArrayAdapter.createFromResource(this, R.array.countries_list,
                android.R.layout.simple_spinner_item);
        mAdapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerCountries.setAdapter(mAdapterCountries);

        mSpinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerCities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerHouseNumbers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void btnDeliveryOkOnClick(View view) {

    }
}