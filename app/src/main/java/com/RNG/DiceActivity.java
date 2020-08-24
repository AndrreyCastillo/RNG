package com.RNG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Vibrator;

import com.RNG.Adapters.DiceAdapter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

enum DICE_TYPE {
    D4 {
        @Override int diceNumber() {
            return 4;
        }
    },
    D6 {
        @Override int diceNumber() {
            return 6;
        }
    },
    D8 {
        @Override int diceNumber() {
            return 8;
        }
    },
    D10 {
        @Override int diceNumber() {
            return 10;
        }
    },
    D12 {
        @Override int diceNumber() {
            return 12;
        }
    },
    D20 {
        @Override int diceNumber() {
            return 20;
        }
    };

    abstract int diceNumber();
}

public class DiceActivity extends AppCompatActivity implements OnItemSelectedListener {

    private TextView diceNumber;
    private SeekBar seekBar;
    private Spinner spinner;
    private Vibrator vibrator;

    ImageView resultOne;
    ImageView resultTwo;
    ImageView resultThree;
    ImageView resultFour;
    ImageView resultFive;
    ImageView resultSix;
    ImageView resultSeven;
    ImageView resultEight;
    ImageView resultNine;
    ImageView resultTen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        diceNumber = findViewById(R.id.dice_number);
        seekBar = findViewById(R.id.seekBar);
        spinner = findViewById(R.id.dice_type_spinner);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        loadSpinnerList();

        diceNumber.setText(Integer.toString(seekBar.getProgress() + 1)); // default dice number

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                diceNumber.setText(" " + (progress + 1) + " "); // update dice number from seekbar

                vibrate(25, VibrationEffect.EFFECT_TICK);
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
        spinner.setOnItemSelectedListener(this);

        List<DICE_TYPE> dice_types = new ArrayList<>();
        dice_types.add(DICE_TYPE.D4);
        dice_types.add(DICE_TYPE.D6);
        dice_types.add(DICE_TYPE.D8);
        dice_types.add(DICE_TYPE.D10);
        dice_types.add(DICE_TYPE.D12);
        dice_types.add(DICE_TYPE.D20);

        // Creating adapter for spinner
        ArrayAdapter<DICE_TYPE> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dice_types);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        // set default selection to D6
        int defaultTypePosition = dataAdapter.getPosition(DICE_TYPE.D6);;
        spinner.setSelection(defaultTypePosition);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void roll_OnClick(View view) {
        vibrate(100, VibrationEffect.EFFECT_HEAVY_CLICK);

        // GET NUMBER OF DICES TO ROLL
        int numberOfDice = seekBar.getProgress() + 1;

        // GET TYPE OF DICE TO ROLL
        DICE_TYPE diceType = (DICE_TYPE) spinner.getSelectedItem();

        // GET THE DICE ROLLS
        int[] dice = rollDice(numberOfDice, diceType);

        setDiceImages(dice, diceType);
    }

    private void setGridAdapter(int[] dice) {
        GridView diceGrid = (GridView) findViewById(R.id.diceGrid); // init GridView
        DiceAdapter customAdapter = new DiceAdapter(getApplicationContext(), dice);
        diceGrid.setAdapter(customAdapter);
    }

    private void vibrate(int vibrationTimeInMilli, int vibrationEffect) {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(vibrationTimeInMilli,vibrationEffect));
        } else {
            ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(vibrationTimeInMilli);
        }
    }

    private int[] rollDice(int numberOfDice, DICE_TYPE diceType) {
        final SecureRandom random = new SecureRandom();

        int[] dice = new int[numberOfDice];

        for (int i = 0; i < dice.length; i++) {
            dice[i] = random.nextInt(diceType.diceNumber()) + 1;
        }

        return dice;
    }

    private void setDiceImages(int[] dice, DICE_TYPE diceType) {
        switch (diceType) {

            case D4:
                setD4Image(dice);
                break;
            case D6:
                setD6Image(dice);
                break;
            case D8:
                setD8Image(dice);
                break;
            case D10:
                setD10Image(dice);
                break;
            case D12:
                setD12Image(dice);
                break;
            case D20:
                setD20Image(dice);
                break;
        }
    }

    private void setD4Image(int[] dice) {
        for (int i = 0; i < dice.length; i++) {
            switch (dice[i]) {
                case 1:
                    dice[i] = R.drawable.d4_1;
                    break;
                case 2:
                    dice[i] = R.drawable.d4_2;
                    break;
                case 3:
                    dice[i] = R.drawable.d4_3;
                    break;
                case 4:
                    dice[i] = R.drawable.d4_4;
                    break;
            }
        }

        setGridAdapter(dice);
    }

    private void setD6Image(int[] dice) {
        for (int i = 0; i < dice.length; i++) {
            switch (dice[i]) {
                case 1:
                    dice[i] = R.drawable.d6_1;
                break;
                case 2:
                    dice[i] = R.drawable.d6_2;
                    break;
                case 3:
                    dice[i] = R.drawable.d6_3;
                    break;
                case 4:
                    dice[i] = R.drawable.d6_4;
                    break;
                case 5:
                    dice[i] = R.drawable.d6_5;
                    break;
                case 6:
                    dice[i] = R.drawable.d6_6;
                    break;
            }
        }

        setGridAdapter(dice);
    }

    private void setD8Image(int[] dice) {
        for (int i = 0; i < dice.length; i++) {
            switch (dice[i]) {
                case 1:
                    dice[i] = R.drawable.d8_1;
                    break;
                case 2:
                    dice[i] = R.drawable.d8_2;
                    break;
                case 3:
                    dice[i] = R.drawable.d8_3;
                    break;
                case 4:
                    dice[i] = R.drawable.d8_4;
                    break;
                case 5:
                    dice[i] = R.drawable.d8_5;
                    break;
                case 6:
                    dice[i] = R.drawable.d8_6;
                    break;
                case 7:
                    dice[i] = R.drawable.d8_7;
                    break;
                case 8:
                    dice[i] = R.drawable.d8_8;
                    break;
            }
        }

        setGridAdapter(dice);
    }

    private void setD10Image(int[] dice) {
        for (int i = 0; i < dice.length; i++) {
            switch (dice[i]) {
                case 1:
                    dice[i] = R.drawable.d10_1;
                    break;
                case 2:
                    dice[i] = R.drawable.d10_2;
                    break;
                case 3:
                    dice[i] = R.drawable.d10_3;
                    break;
                case 4:
                    dice[i] = R.drawable.d10_4;
                    break;
                case 5:
                    dice[i] = R.drawable.d10_5;
                    break;
                case 6:
                    dice[i] = R.drawable.d10_6;
                    break;
                case 7:
                    dice[i] = R.drawable.d10_7;
                    break;
                case 8:
                    dice[i] = R.drawable.d10_8;
                    break;
                case 9:
                    dice[i] = R.drawable.d10_9;
                    break;
                case 10:
                    dice[i] = R.drawable.d10_10;
                    break;
            }
        }

        setGridAdapter(dice);
    }

    private void setD12Image(int[] dice) {
        for (int i = 0; i < dice.length; i++) {
            switch (dice[i]) {
                case 1:
                    dice[i] = R.drawable.d12_1;
                    break;
                case 2:
                    dice[i] = R.drawable.d12_2;
                    break;
                case 3:
                    dice[i] = R.drawable.d12_3;
                    break;
                case 4:
                    dice[i] = R.drawable.d12_4;
                    break;
                case 5:
                    dice[i] = R.drawable.d12_5;
                    break;
                case 6:
                    dice[i] = R.drawable.d12_6;
                    break;
                case 7:
                    dice[i] = R.drawable.d12_7;
                    break;
                case 8:
                    dice[i] = R.drawable.d12_8;
                    break;
                case 9:
                    dice[i] = R.drawable.d12_9;
                    break;
                case 10:
                    dice[i] = R.drawable.d12_10;
                    break;
                case 11:
                    dice[i] = R.drawable.d12_11;
                    break;
                case 12:
                    dice[i] = R.drawable.d12_12;
                    break;
            }
        }

        setGridAdapter(dice);
    }

    private void setD20Image(int[] dice) {
        for (int i = 0; i < dice.length; i++) {
            switch (dice[i]) {
                case 1:
                    dice[i] = R.drawable.d20_1;
                    break;
                case 2:
                    dice[i] = R.drawable.d20_2;
                    break;
                case 3:
                    dice[i] = R.drawable.d20_3;
                    break;
                case 4:
                    dice[i] = R.drawable.d20_4;
                    break;
                case 5:
                    dice[i] = R.drawable.d20_5;
                    break;
                case 6:
                    dice[i] = R.drawable.d20_6;
                    break;
                case 7:
                    dice[i] = R.drawable.d20_7;
                    break;
                case 8:
                    dice[i] = R.drawable.d20_8;
                    break;
                case 9:
                    dice[i] = R.drawable.d20_9;
                    break;
                case 10:
                    dice[i] = R.drawable.d20_10;
                    break;
                case 11:
                    dice[i] = R.drawable.d20_11;
                    break;
                case 12:
                    dice[i] = R.drawable.d20_12;
                    break;
                case 13:
                    dice[i] = R.drawable.d20_13;
                    break;
                case 14:
                    dice[i] = R.drawable.d20_14;
                    break;
                case 15:
                    dice[i] = R.drawable.d20_15;
                    break;
                case 16:
                    dice[i] = R.drawable.d20_16;
                    break;
                case 17:
                    dice[i] = R.drawable.d20_17;
                    break;
                case 18:
                    dice[i] = R.drawable.d20_18;
                    break;
                case 19:
                    dice[i] = R.drawable.d20_19;
                    break;
                case 20:
                    dice[i] = R.drawable.d20_20;
                    break;
            }
        }

        setGridAdapter(dice);
    }
}
