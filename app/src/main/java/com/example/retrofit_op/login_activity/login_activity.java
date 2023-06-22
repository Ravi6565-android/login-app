package com.example.retrofit_op.login_activity;

import static com.example.retrofit_op.slapsh_screen.Splash_screen_activity.editor;
import static com.example.retrofit_op.slapsh_screen.Splash_screen_activity.preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofit_op.R;
import com.example.retrofit_op.home_sreen.Home_screen_actvity;
import com.example.retrofit_op.model_class.LoginData;
import com.example.retrofit_op.retro_instance;

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
                            editor.putBoolean("isLogin",true);
                            editor.putString("name",response.body().getUserdata().getName());
                            editor.putString("email",response.body().getUserdata().getEmail());
                            editor.putInt("userid",response.body().getUserdata().getId());
                            editor.apply();
                            editor.commit();
                            Toast.makeText(login_activity.this, "account found ", Toast.LENGTH_LONG).show();
                            Intent intent= new Intent(login_activity.this, Home_screen_actvity.class);
                            startActivity(intent);


                        }else{
                            Toast.makeText(login_activity.this, "account not found ", Toast.LENGTH_LONG).show();

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