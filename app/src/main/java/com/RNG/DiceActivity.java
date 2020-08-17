package com.RNG;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DiceActivity extends AppCompatActivity implements OnItemSelectedListener {
    private TextView dice_number;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        loadSpinnerList();

        dice_number = findViewById(R.id.dice_number);
        seekBar = findViewById(R.id.seekBar);

        dice_number.setText(Integer.toString(seekBar.getProgress() + 1));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dice_number.setText(" " + (progress + 1) + " ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    protected void loadSpinnerList() {
        Spinner spinner = findViewById(R.id.dice_type_spinner);

        spinner.setOnItemSelectedListener(this);

        List<String> dice_types = new ArrayList<>();
        dice_types.add(getString(R.string.dice_type_d4));
        dice_types.add(getString(R.string.dice_type_d6));
        dice_types.add(getString(R.string.dice_type_d8));
        dice_types.add(getString(R.string.dice_type_d10));
        dice_types.add(getString(R.string.dice_type_d12));
        dice_types.add(getString(R.string.dice_type_d20));

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dice_types);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        // set default selection to D6
        int defaultTypePosition = dataAdapter.getPosition(getString(R.string.dice_type_d6));;
        spinner.setSelection(defaultTypePosition);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
