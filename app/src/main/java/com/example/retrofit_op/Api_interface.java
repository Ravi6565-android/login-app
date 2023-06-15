package com.example.retrofit_op;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_interface {

    //https://my-e-commerce-app.000webhostapp.com/my_new_app/register.php
    //https://my-e-commerce-app.000webhostapp.com/my_new_app/login.php
    @FormUrlEncoded
    @POST("register.php")
    Call<model_class> MODEL_CLASS_CALL(@Field("name") String name,@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<login_model> LOGIN_MODEL_CALLIN (@Field("email") String email,@Field("password") String password);
}
