package com.example.retrofit_op;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_activity extends AppCompatActivity {
    Button sigin;
    EditText email,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sigin=findViewById(R.id.signin);
        email=findViewById(R.id.editl_email);
        password=findViewById(R.id.editl_password);

        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String semail,spassword;
              semail  = email.getText().toString();
              spassword  = password.getText().toString();

              retro_instance.callApi().LOGIN_MODEL_CALLIN(semail,spassword).enqueue(new Callback<login_model>() {
                  @Override
                  public void onResponse(Call<login_model> call, Response<login_model> response) {
                    if(response.isSuccessful()==true){
                        String tag ="tag";
                        ArrayList<login_model> list = new ArrayList<>();

                        Log.d(tag, "name: "+list.get(0).getName());
                        Log.d(tag, "email: "+list.get(0).getEmail());
                        Log.d(tag, "password: "+list.get(0).getPassword());
                        Log.d(tag, "id: "+list.get(0).getId());
                        Toast.makeText(login_activity.this, "name=="+response.body().toString() , Toast.LENGTH_LONG).show();


                    }else {
                        Toast.makeText(login_activity.this, "account not found", Toast.LENGTH_LONG).show();
                    }

                  }

                  @Override
                  public void onFailure(Call<login_model> call, Throwable t) {

                  }
              });



            }
        });

    }
}