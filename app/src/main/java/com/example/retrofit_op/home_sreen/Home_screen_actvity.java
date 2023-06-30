package com.example.retrofit_op.home_sreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.example.retrofit_op.slapsh_screen.Splash_screen_activity.editor;
import static com.example.retrofit_op.slapsh_screen.Splash_screen_activity.preferences;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit_op.R;
import com.example.retrofit_op.adapters.show_product_adapter;
import com.example.retrofit_op.model_class.ProductAddModel;
import com.example.retrofit_op.model_class.Product_get_model;
import com.example.retrofit_op.model_class.Productdatum;
import com.example.retrofit_op.model_class.Userdata;
import com.example.retrofit_op.model_class.model_class;
import com.example.retrofit_op.register_activity.Register_activity;
import com.example.retrofit_op.retro_instance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Home_screen_actvity extends AppCompatActivity {

    NavigationView navigationView;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    FloatingActionButton floatingActionButton;
    TextView header_name, header_email;
    String name, email;
    Toolbar toolbar;
    Button button;
    String imagedata;
    ProgressBar progressBar;
    List<Productdatum> Userdata;


    ImageView pimage;
    int gellery = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_actvity);
        init();//reffrence
        setname(); //toolbar set in drawer user name and email set
        show_product_method();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Home_screen_actvity.this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        logout();


//        floatingActionButton.setOnClickListener(v -> {
//
//
//
//            Dialog dialog = new Dialog(Home_screen_actvity.this);
//            dialog.setContentView(R.layout.add_product_dialog);
//
//            EditText pname, pprice, pdisc;
//
//            int uid;
//            uid = preferences.getInt("userid", 0);
//            Button add, cancel;
//            pname = dialog.findViewById(R.id.apname);
//            pprice = dialog.findViewById(R.id.apprice);
//            pdisc = dialog.findViewById(R.id.apdis);
//            pimage = dialog.findViewById(R.id.apimage);
//            add = dialog.findViewById(R.id.add);
//            cancel = dialog.findViewById(R.id.cancel);
//
//
//            cancel.setOnClickListener(view -> {
//                dialog.dismiss();
//            });
//            pimage.setOnClickListener(view -> {
//
//                CropImage.activity()
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .start(this);
//            });
//
//
//            add.setOnClickListener(v1 -> {
//                String spname, spprice, spdisc;
//                spdisc = pdisc.getText().toString();
//                spprice = pprice.getText().toString();
//                spname = pname.getText().toString();
//
//                Bitmap bitmap = ((BitmapDrawable) pimage.getDrawable()).getBitmap();
//                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//                byte[] imageinarayy = byteArrayOutputStream.toByteArray();
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    imagedata = Base64.getEncoder().encodeToString(imageinarayy);
//                }
//
//                retro_instance.callApi().PRODUCT_ADD_MODEL_CALL(uid, spname, spprice, spdisc, imagedata)
//                        .enqueue(new Callback<ProductAddModel>() {
//                            @Override
//                            public void onResponse(Call<ProductAddModel> call, Response<ProductAddModel> response) {
//                                if (response.body().getProductaddd() == 1) {
//                                    Toast.makeText(Home_screen_actvity.this, "product Added", Toast.LENGTH_LONG).show();
//                                    show_product_method();
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<ProductAddModel> call, Throwable t) {
//
//                            }
//                        });
//
//
//                dialog.dismiss();
//            });
//
//            dialog.show();
//        });
        newmethod();


    }

    private void init() {
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        floatingActionButton = findViewById(R.id.fab);
        View view = navigationView.getHeaderView(0);
       // imageView = view.findViewById(R.id.header_image);
        recyclerView=findViewById(R.id.recycler);
        progressBar=findViewById(R.id.ProgressBar);



    }

    private void setname() {
        toolbar.setTitle("E_Commerce app ");
        setSupportActionBar(toolbar);
        name = preferences.getString("name", "");
        email = preferences.getString("email", "");
        View view = navigationView.getHeaderView(0);
        header_name = view.findViewById(R.id.header_name);
        header_email = view.findViewById(R.id.header_email);
        header_name.setText(name);
        header_email.setText(email);
    }

    private void show_product_method(){
        int userid =preferences.getInt("userid",2);
        retro_instance.callApi().PRODUCT_GET_MODEL_CALL(userid).enqueue(new Callback<Product_get_model>() {
            @Override
            public void onResponse(Call<Product_get_model> call, Response<Product_get_model> response) {

                Userdata= response.body().getProductdata();
                if(Userdata!=null){
                    progressBar.setVisibility(View.GONE);
                }

                if (response.isSuccessful()) {


                    if (response.body().getConnection() == 1) {
                        if (response.body().getResult() == 1) {
                            show_product_adapter adapter = new show_product_adapter(Home_screen_actvity.this, Userdata);
                            LinearLayoutManager manager = new LinearLayoutManager(Home_screen_actvity.this);
                            manager.setOrientation(RecyclerView.VERTICAL);
                            recyclerView.setLayoutManager(manager);

                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<Product_get_model> call, Throwable t) {

            }
        });

    }

    public void newmethod(){
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(Home_screen_actvity.this);
                dialog.setContentView(R.layout.add_product_dialog);

                EditText pname, pprice, pdisc;

                int uid;
                uid = preferences.getInt("userid", 0);
                Button add, cancel;
                pname = dialog.findViewById(R.id.apname);
                pprice = dialog.findViewById(R.id.apprice);
                pdisc = dialog.findViewById(R.id.apdis);
                pimage = dialog.findViewById(R.id.apimage);
                add = dialog.findViewById(R.id.add);
                cancel = dialog.findViewById(R.id.cancel);


                cancel.setOnClickListener(view1 -> {
                    dialog.dismiss();
                });
                pimage.setOnClickListener(view1 -> {

                    CropImage.activity()
                            .setGuidelines(CropImageView.Guidelines.ON)
                            .start(Home_screen_actvity.this);
                });


                add.setOnClickListener(v1 -> {
                    String spname, spprice, spdisc;
                    spdisc = pdisc.getText().toString();
                    spprice = pprice.getText().toString();
                    spname = pname.getText().toString();

                    Bitmap bitmap = ((BitmapDrawable) pimage.getDrawable()).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    byte[] imageinarayy = byteArrayOutputStream.toByteArray();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        imagedata = Base64.getEncoder().encodeToString(imageinarayy);
                    }
                    String path=Userdata.get(0).getPimage();

                    retro_instance.callApi().call_update(2,spname,spprice,spdisc,imagedata,path).enqueue(new Callback<model_class>() {
                        @Override
                        public void onResponse(Call<model_class> call, Response<model_class> response) {
                            if (response.isSuccessful()){
                                if(response.body().getResult()==1){
                                    Toast.makeText(Home_screen_actvity.this, "updated product", Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<model_class> call, Throwable t) {

                        }
                    });


                    dialog.dismiss();
                });

                dialog.show();

            }
        });
    }


    private void logout() {
//        imageView.setOnClickListener(view -> {
//
//            Intent intent = new Intent();
//            intent.setType("image/*");
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), gellery);
//
//        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_logout) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Home_screen_actvity.this);
                    builder.setTitle("LOGOUT ?");
                    builder.setMessage("Are You Sure You Want To Logout ?");
                    builder.setPositiveButton("yes", (dialogInterface, i) -> {
                        editor.putBoolean("isLogin", false);
                        editor.commit();
                        Intent intent = new Intent(Home_screen_actvity.this, Register_activity.class);
                        drawerLayout.close();
                        startActivity(intent);
                    });

                    builder.setNegativeButton("no", (dialogInterface, i) -> {

                        drawerLayout.close();
                    });
                    builder.show();
                } else if (item.getItemId() == R.id.menu_myprofile) {
                    drawerLayout.close();
                }

                return false;
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result= CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) ;
            {
                Uri uri=result.getUri();
                pimage.setImageURI(uri);

            }
        }
    }
}