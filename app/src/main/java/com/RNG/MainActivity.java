package com.RNG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void wheelOfRNG_OnClick(View view) {
        Intent intent = new Intent(this, WheelOfRNGActivity.class);
        startActivity(intent);
    }

    public void dice_OnClick(View view) {
        Intent intent = new Intent(this, DiceActivity.class);
        startActivity(intent);
    }

    public void randomNumber_OnClick(View view) {
        Intent intent = new Intent(this, RandomNumberActivity.class);
        startActivity(intent);
    }

    public void randomPassword_OnClick(View view) {
        Intent intent = new Intent(this, RandomPasswordActivity.class);
        startActivity(intent);
    }
}
