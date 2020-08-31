package com.RNG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.security.SecureRandom;

public class SpinTheBottleActivity extends AppCompatActivity {

    private ImageView bottle;

    public static final SecureRandom RANDOM = new SecureRandom();
    private int lastAngle = -1;
    private final int DURATION = 3200;

    private boolean spinning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spin_the_bottle);

        bottle = (ImageView) findViewById(R.id.bottle);

        Toast.makeText(this, R.string.tap_to_spin, Toast.LENGTH_SHORT).show();
    }

    public void bottle_onClick(View view) {
        if (spinning) return;

        spinTheBottle();
    }

    private void spinTheBottle() {

        // RANDOM.nextInt((high+1)-low) + low; generates a random int within bounds
        // the reason these angles are so high so it can spin 10 times
        int highAngle = 3600;
        int lowAngle = 3240; // 3600 - 360 = 3240
        int angle = RANDOM.nextInt((highAngle + 1) - lowAngle) + lowAngle;

        float pivotX = bottle.getWidth() >> 1;
        float pivotY = bottle.getHeight() >> 1;

        final Animation animRotate = new RotateAnimation(lastAngle == -1 ? 0 : lastAngle, angle, pivotX, pivotY);
        lastAngle = angle - lowAngle; // 0 <= lastAngle <= 360, so it resets to a low number so it can spin 10 times again
        animRotate.setDuration(DURATION);
        animRotate.setFillAfter(true); // show the image after animation

        animRotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                spinning = true;
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                spinning = false;
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        bottle.startAnimation(animRotate);
    }

//    private void resetTheBottle() {
//
//        // RANDOM.nextInt(high-low) + low; generates random int within bounds
//        // the reason these angles are so high so it can spin 10 times
//        int highAngle = 3600;
//        int lowAngle = 3240; // 3600 - 360 = 3240
//        int angle = RANDOM.nextInt(highAngle - lowAngle) + lowAngle;
//
//        float pivotX = bottle.getWidth() >> 1;
//        float pivotY = bottle.getHeight() >> 1;
//
//        final Animation animRotate = new RotateAnimation(lastAngle == -1 ? 0 : lastAngle, angle, pivotX, pivotY);
//        lastAngle = -1;
//        animRotate.setDuration(DURATION);
//        animRotate.setFillAfter(true); // show the image after animation
//
//        animRotate.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                spinning = true;
//            }
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                spinning = false;
//            }
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            }
//        });
//
//        bottle.startAnimation(animRotate);
//    }
}
