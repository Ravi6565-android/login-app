package com.example.retrofit_op.home_sreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.retrofit_op.R;
import com.google.android.material.navigation.NavigationView;

public class Home_screen_actvity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView header_name,header_email;
    String name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_actvity);
        navigationView=findViewById(R.id.navigation_view);
        drawerLayout=findViewById(R.id.drawer_layout);

        header_name= navigationView.findViewById(R.id.header_name);
        header_email= navigationView.findViewById(R.id.header_email);
        name=getIntent().getStringExtra("name");
        email=getIntent().getStringExtra("email");
        Log.d("TAG", "onCreate: name="+name+"\temail="+email);
        header_name.setText(name);
        header_email.setText(email);


    }
}