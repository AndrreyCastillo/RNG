package com.RNG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.security.SecureRandom;
import java.util.stream.LongStream;

public class RandomNumberActivity extends AppCompatActivity {

    final private int LONG_STREAM_SIZE = 12;

    private TextView popupNumber;
    private VibrationHandler vibrationHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_number);

        vibrationHandler = new VibrationHandler();
    }

    public void rng_OnClick(View view) {
        vibrationHandler.vibrate(100, VibrationEffect.EFFECT_HEAVY_CLICK, this);

        String firstNumberText = ((EditText) findViewById(R.id.firstNumber)).getText().toString();
        String secondNumberText = ((EditText) findViewById(R.id.secondNumber)).getText().toString();

        if (firstNumberText.isEmpty() || secondNumberText.isEmpty()) {
            return;
        }
        if (firstNumberText.equals("-") || secondNumberText.equals("-")) {
            return;
        }

        long randomNumber = getRandomNumberWithinBounds(firstNumberText, secondNumberText);

        showPopupRandomNumber(randomNumber);
    }

    private long getRandomNumberWithinBounds(String firstNumberText, String secondNumberText) {

        long firstNumber = Long.parseLong(firstNumberText);
        long secondNumber = Long.parseLong(secondNumberText);

        if (firstNumber >= secondNumber) {
            long temp = firstNumber;
            firstNumber = secondNumber;
            secondNumber = temp;
        }

        SecureRandom secureRandom = new SecureRandom();
        LongStream longStream = secureRandom.longs(LONG_STREAM_SIZE, firstNumber, secondNumber + 1);

        long[] randomLongArray = longStream.toArray();
        int randomArrayIndex = new SecureRandom().nextInt(randomLongArray.length);

        return randomLongArray[randomArrayIndex];
    }

    private void showPopupRandomNumber(long randomNumber) {

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupLayout = inflater.inflate(R.layout.popup_window, null);

        // SET TEXT TO THE RANDOM NUMBER
        TextView popupNumber = (TextView) popupLayout.findViewById(R.id.popup_random_number);
        popupNumber.setText(Long.toString(randomNumber));

        PopupWindow popupWindow = new PopupWindow(popupLayout, this.getWindow().getDecorView().getWidth(), 600, false);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(popupLayout, Gravity.TOP, 0, 0);
    }
}
