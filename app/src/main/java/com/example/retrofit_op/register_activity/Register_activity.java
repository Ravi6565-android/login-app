package com.example.retrofit_op.register_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofit_op.R;
import com.example.retrofit_op.login_activity.login_activity;
import com.example.retrofit_op.model_class.model_class;
import com.example.retrofit_op.retro_instance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register_activity extends AppCompatActivity {

    EditText name,email,password;
    Button submit,login;
    String sname,semail,spassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.edit_name);
        email=findViewById(R.id.edit_email);
        password=findViewById(R.id.edit_password);

        submit=findViewById(R.id.submit);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Register_activity.this, login_activity.class);
                startActivity(intent);
            }
        });




            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(name.getText().toString().equals("")){
                        name.setError("empty field ",getResources().getDrawable(R.drawable.baseline_error_24));

                    }
                    if(email.getText().toString().equals("")){
                        email.setError("empty field ",getResources().getDrawable(R.drawable.baseline_error_24));

                    }
                    if(password.getText().toString().equals("")){
                        password.setError("empty field ",getResources().getDrawable(R.drawable.baseline_error_24));

                    }
                    sname=name.getText().toString();
                    semail=email.getText().toString();
                    spassword=password.getText().toString();


                    retro_instance.callApi().MODEL_CLASS_CALL(sname,semail,spassword).enqueue(new Callback<model_class>() {
                        @Override
                        public void onResponse(Call<model_class> call, Response<model_class> response) {
                            Log.d("TAG", "onResponse: "+response.body().toString());

                            Log.d("con", "onResponse: Connection"+ response.body().getConnection());
                            if(response.body().getResult()==1){

                                Toast.makeText(Register_activity.this, "successfully register", Toast.LENGTH_LONG).show();
                            } else if (response.body().getResult()==2) {
                                Toast.makeText(Register_activity.this, "Already register", Toast.LENGTH_LONG).show();

                            }else if(response.body().getResult()==0){
                                Toast.makeText(Register_activity.this, "somthing went wrong", Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<model_class> call, Throwable t) {

                        }
                    });

                }
            });







    }
}