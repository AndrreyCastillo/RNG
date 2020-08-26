package com.RNG;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import static android.content.Context.VIBRATOR_SERVICE;

public class VibrationHandler {

    public void vibrate(int vibrationTimeInMilli, int vibrationEffect, Context context) {
        Vibrator vibrator = (Vibrator)((Activity)context).getSystemService(VIBRATOR_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vibrator.vibrate(VibrationEffect.createOneShot(vibrationTimeInMilli,vibrationEffect));
        }
        else {
            vibrator.vibrate(vibrationTimeInMilli);
        }
    }
}
