package com.example.retrofit_op;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retro_instance.callApi().MODEL_CLASS_CALL("Ravi","ravichauhanis420@gmail.com","12345").enqueue(new Callback<model_class>() {
            @Override
            public void onResponse(Call<model_class> call, Response<model_class> response) {
                Log.d("TAG", "onResponse: "+response.body().toString());

                Log.d("con", "onResponse: Connection"+response.body().connection);
                if(response.body().connection==1){

                    Toast.makeText(MainActivity.this, "successfully register", Toast.LENGTH_LONG).show();
                } else if (response.body().connection==2) {
                    Toast.makeText(MainActivity.this, "Already register", Toast.LENGTH_LONG).show();

                }else if(response.body().connection==0){
                    Toast.makeText(MainActivity.this, "somthing went wrong", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<model_class> call, Throwable t) {

            }
        });

    }
}