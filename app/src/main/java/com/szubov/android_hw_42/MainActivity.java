package com.szubov.android_hw_42;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner mSpinnerCountries;
    private Spinner mSpinnerCities;
    private Spinner mSpinnerHouseNumbers;
    private ArrayAdapter<CharSequence> adapterCountries;
    private ArrayAdapter<String> adapterNumbersHouse;
    private ArrayAdapter<CharSequence> adapterCities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {

        mSpinnerCountries = findViewById(R.id.spinnerCountries);
        mSpinnerCities = findViewById(R.id.spinnerCities);
        mSpinnerHouseNumbers = findViewById(R.id.spinnerHouseNumbers);

        initSpinnerCountries();
    }

    private void initHouseNumbersSpinner() {

        String[] houseNumbers = new String[51];
        for (int i= 1; i <= 50; i++) {
            houseNumbers[i-1] = String.valueOf(i);
        }houseNumbers[50] = getText(R.string.default_house_numbers).toString();

        adapterNumbersHouse = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, houseNumbers);
        mSpinnerHouseNumbers.setAdapter(adapterNumbersHouse);
        mSpinnerHouseNumbers.setSelection(adapterNumbersHouse.getCount() - 1);
    }

    private void initSpinnerCountries() {
        adapterCountries = ArrayAdapter.createFromResource(this, R.array.countries_list,
                android.R.layout.simple_spinner_item);
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerCountries.setAdapter(adapterCountries);
        mSpinnerCountries.setSelection(adapterCountries.getCount() - 1);

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
        adapterCities =  null;

        switch (country) {
            case "Выберите страну":
                break;
            case "Россия":
                adapterCities = ArrayAdapter.createFromResource(this,
                        R.array.russia_cities_list, android.R.layout.simple_spinner_item);
                break;
            case "Украина":
                adapterCities = ArrayAdapter.createFromResource(this,
                        R.array.ukraine_cities_list, android.R.layout.simple_spinner_item);
                break;
            case "Белоруссия":
                adapterCities = ArrayAdapter.createFromResource(this,
                        R.array.belarus_cities_list, android.R.layout.simple_spinner_item);
                break;
        }
        if (adapterCities != null) {
            adapterCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mSpinnerCities.setAdapter(adapterCities);
            mSpinnerCities.setSelection(adapterCities.getCount() - 1);
            initHouseNumbersSpinner();
        }
    }

    public void btnDeliveryOkOnClick(View view) {
        if (!mSpinnerCountries.getSelectedItem().toString().
                equals(getText(R.string.default_countries).toString()) ||
                !mSpinnerCities.getSelectedItem().toString().
                        equals(getText(R.string.default_cities).toString()) ||
                !mSpinnerHouseNumbers.getSelectedItem().toString().
                        equals(getText(R.string.default_house_numbers).toString())) {
            Toast.makeText(this, getText(R.string.delivery_address) + "\n" +
                    mSpinnerCountries.getSelectedItem().toString() + "\n" +
                    mSpinnerCities.getSelectedItem().toString() + "\n" +
                    getText(R.string.house_number).toString() +
                    mSpinnerHouseNumbers.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
            mSpinnerCountries.setSelection(adapterCountries.getCount() - 1);
            mSpinnerCities.setSelection(adapterCities.getCount() - 1);
            mSpinnerHouseNumbers.setSelection(adapterNumbersHouse.getCount() - 1);
        } else {
            Toast.makeText(this, getText(R.string.delivery_address_is_empty).toString(),
                    Toast.LENGTH_LONG).show();
        }

    }
}