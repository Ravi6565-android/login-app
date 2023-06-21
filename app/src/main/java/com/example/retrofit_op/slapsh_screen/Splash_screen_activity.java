package com.example.retrofit_op.slapsh_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.retrofit_op.R;
import com.example.retrofit_op.home_sreen.Home_screen_actvity;
import com.example.retrofit_op.register_activity.Register_activity;

public class Splash_screen_activity extends AppCompatActivity {

    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    Boolean islogin=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        preferences=getSharedPreferences("my_pref",MODE_PRIVATE);
        editor=preferences.edit();
       islogin= preferences.getBoolean("isLogin",false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;
                if (islogin) {
                    intent = new Intent(Splash_screen_activity.this, Home_screen_actvity.class);
                    startActivity(intent);
                    finish();
                }else {
                    intent = new Intent(Splash_screen_activity.this, Register_activity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },5000);
    }
}