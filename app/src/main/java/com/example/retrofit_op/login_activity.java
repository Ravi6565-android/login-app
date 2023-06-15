package com.example.retrofit_op;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                    if(response.isSuccessful()){
                        String tag ="tag";
                        Log.d(tag, "name: "+response.body().name);
                        Log.d(tag, "email: "+response.body().email);
                        Log.d(tag, "id: "+response.body().id);
                        Log.d(tag, "password: "+response.body().password);
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