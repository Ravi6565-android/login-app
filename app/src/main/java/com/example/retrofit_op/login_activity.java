package com.example.retrofit_op;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofit_op.model_class.LoginData;

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

              retro_instance.callApi().LOGIN_MODEL_CALLIN(semail,spassword).enqueue(new Callback<LoginData>() {
                  @Override
                  public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                    if(response.isSuccessful()){

                        if(response.body().getResult()==1){
                            Toast.makeText(login_activity.this, "account found ", Toast.LENGTH_LONG).show();
                            Log.d("TAG", "name: "+response.body().getUserdata().getName());
                            Log.d("TAG", "id: "+response.body().getUserdata().getId());
                            Log.d("TAG", "email: "+response.body().getUserdata().getEmail());
                            Log.d("TAG", "password: "+response.body().getUserdata().getPassword());

                        }

                    }

                  }

                  @Override
                  public void onFailure(Call<LoginData> call, Throwable t) {

                  }
              });



            }
        });

    }
}