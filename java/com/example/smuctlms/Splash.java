package com.example.smuctlms;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Splash extends AppCompatActivity {
    TextView textView;
    LottieAnimationView lottie;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //hide action bar in splash screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();



        setContentView(R.layout.activity_splash);
        textView = findViewById(R.id.textView);
        logo = findViewById(R.id.imageView);
        lottie = findViewById(R.id.lottie);


        lottie.animate().translationY(1600).setDuration(4000).setStartDelay(3000);
        textView.animate().translationY(1600).setDuration(4000).setStartDelay(3000);
        logo.animate().translationY(1600).setDuration(4000).setStartDelay(3000);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                    Intent intent = new Intent(Splash.this,Login.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }
}