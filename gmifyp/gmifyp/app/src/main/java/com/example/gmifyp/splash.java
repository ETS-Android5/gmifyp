package com.example.gmifyp;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               setContentView(R.layout.splash);
        //startActivity(new Intent(this,MainActivity.class));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(splash.this, Register.class);
                splash.this.startActivity(mainIntent);
                splash.this.finish();
            }
        },1500);

    }
}