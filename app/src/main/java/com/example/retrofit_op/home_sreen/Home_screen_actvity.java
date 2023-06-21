package com.example.retrofit_op.home_sreen;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.retrofit_op.R;
import com.google.android.material.navigation.NavigationView;

public class Home_screen_actvity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView header_name,header_email;
    String name,email;
Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_actvity);
        navigationView=findViewById(R.id.navigation_view);
        drawerLayout=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        name=getIntent().getStringExtra("name");
        email=getIntent().getStringExtra("email");
        Log.d("TAG", "onCreate: name="+name+"\temail="+email);
        View view= navigationView.getHeaderView(0);
        header_name=view.findViewById(R.id.header_name);
        header_email=view.findViewById(R.id.header_email);
        header_name.setText(name);
        header_email.setText(email);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(Home_screen_actvity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }
}