package com.example.retrofit_op;

import kotlin.contracts.Returns;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retro_instance {

    public static Api_interface callApi() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-e-commerce-app.000webhostapp.com/my_new_app/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api_interface api_interface = retrofit.create(Api_interface.class);

        return api_interface;

    }


}
