package com.example.prosp.er24;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.prosp.er24.users.Login;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_DURATION = 4000;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(MainActivity.this,Login.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, SPLASH_DISPLAY_DURATION);
    }}
