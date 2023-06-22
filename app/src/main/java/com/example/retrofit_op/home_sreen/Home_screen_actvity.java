package com.example.retrofit_op.home_sreen;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import static com.example.retrofit_op.slapsh_screen.Splash_screen_activity.preferences;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.retrofit_op.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Home_screen_actvity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FloatingActionButton floatingActionButton;
    TextView header_name,header_email;
    String name,email;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_actvity);
        init();//reffrence
        setname();


     //   setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(Home_screen_actvity.this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        floatingActionButton.setOnClickListener(v -> {
            Dialog  dialog= new Dialog(Home_screen_actvity.this);
            dialog.setContentView(R.layout.add_product_dialog);

            EditText pname,pprice,pdisc,pimage;
            Button add;
            pname=dialog.findViewById(R.id.apname);
            pprice=dialog.findViewById(R.id.apprice);
            pdisc=dialog.findViewById(R.id.apdis);
            pimage=dialog.findViewById(R.id.apimage);
            add=dialog.findViewById(R.id.add);
            add.setOnClickListener(v1 -> {
                dialog.dismiss();
            });
            dialog.show();
        });



    }
    private void init(){
        navigationView=findViewById(R.id.navigation_view);
        drawerLayout=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar);
        floatingActionButton=findViewById(R.id.fab);
    }

    private void setname(){
        toolbar.setTitle("E_Commerce app ");
        setSupportActionBar(toolbar);
        name=preferences.getString("name","");
        email=preferences.getString("email","");
        View view= navigationView.getHeaderView(0);
        header_name=view.findViewById(R.id.header_name);
        header_email=view.findViewById(R.id.header_email);
        header_name.setText(name);
        header_email.setText(email);
    }
}