package com.szubov.android_hw_42;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mBtnDeliveryOk;
    private Spinner mSpinnerCountries;
    private Spinner mSpinnerCities;
    private Spinner mSpinnerHouseNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        mBtnDeliveryOk = findViewById(R.id.btnDeliveryOk);
        mSpinnerCountries = findViewById(R.id.spinnerCountries);
        mSpinnerCities = findViewById(R.id.spinnerCities);
        mSpinnerHouseNumbers = findViewById(R.id.spinnerHouseNumbers);

        mSpinnerHouseNumbers.setPrompt(getText(R.string.prompt_house_numbers));
        mSpinnerCountries.setPrompt(getText(R.string.prompt_countries));
        mSpinnerCities.setPrompt(getText(R.string.prompt_cities));

        initSpinnerCountries();
        initHouseNumbersSpinner();
    }

    private void initHouseNumbersSpinner() {
        Integer[] houseNumbers = new Integer[50];
        for (int i= 1; i <= 50; i++) {
            houseNumbers[i-1] = i;
        }
        ArrayAdapter<Integer> adapterNumbersHouse = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, houseNumbers);
        mSpinnerHouseNumbers.setAdapter(adapterNumbersHouse);
    }

    private void initSpinnerCountries() {
        ArrayAdapter<CharSequence> adapterCountries = ArrayAdapter.createFromResource(this, R.array.countries_list, android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCountries.setAdapter(adapterCountries);

        mSpinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] countries = getResources().getStringArray(R.array.countries_list);
                initSpinnerCities(countries[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initSpinnerCities(String country) {
        ArrayAdapter<CharSequence> adapterCities =  null;

        switch (country) {
            case "Россия":
                adapterCities = ArrayAdapter.createFromResource(this, R.array.russia_cities_list, android.R.layout.simple_spinner_item);
                break;
            case "Украина":
                adapterCities = ArrayAdapter.createFromResource(this, R.array.ukraine_cities_list, android.R.layout.simple_spinner_item);
                break;
            case "Белоруссия":
                adapterCities = ArrayAdapter.createFromResource(this,R.array.belarus_cities_list, android.R.layout.simple_spinner_item);
                break;
        }
        if (adapterCities != null) {
            adapterCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinnerCities.setAdapter(adapterCities);
        }
    }

    public void btnDeliveryOkOnClick(View view) {
        Toast.makeText(this, getText(R.string.delivery_address) + "\n" +
                mSpinnerCountries.getSelectedItem().toString() + "\n" +
                mSpinnerCities.getSelectedItem().toString() + "\n" +
                getText(R.string.house_number).toString() +
                mSpinnerHouseNumbers.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
        mSpinnerCountries.setSelection(0);
        mSpinnerHouseNumbers.setSelection(0);
    }
}