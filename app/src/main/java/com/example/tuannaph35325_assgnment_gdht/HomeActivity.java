package com.example.tuannaph35325_assgnment_gdht;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        runWhenTurnOnDevice();
    }



    private void runWhenTurnOnDevice() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                int count = 0;
                while (true) {
                    count++;
                    if (count>3) {
                        Intent intent = new Intent(HomeActivity.this, LoginUser.class);
                        startActivity(intent);
                        finish();
                        break;
                    }
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }

            }
        };
        thread.start();
    }
}