package com.example.retrofit_op;

import static com.example.retrofit_op.slapsh_screen.Splash_screen_activity.preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.retrofit_op.home_sreen.Home_screen_actvity;
import com.example.retrofit_op.model_class.model_class;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Update_activity extends AppCompatActivity {

    ImageView pimage;
    String imagedata;
   // @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update);
//
//        Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.add_product_dialog);
//
//        EditText pname, pprice, pdisc;
//
//        int uid;
//        uid = preferences.getInt("userid", 0);
//        Button add, cancel;
//        pname = dialog.findViewById(R.id.apname);
//        pprice = dialog.findViewById(R.id.apprice);
//        pdisc = dialog.findViewById(R.id.apdis);
//        pimage = dialog.findViewById(R.id.apimage);
//        add = dialog.findViewById(R.id.add);
//        cancel = dialog.findViewById(R.id.cancel);
//
//
//        cancel.setOnClickListener(view1 -> {
//            dialog.dismiss();
//        });
//        pimage.setOnClickListener(view1 -> {
//
//            CropImage.activity()
//                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .start(Update_activity.this);
//        });
//
//
//        add.setOnClickListener(v1 -> {
//            String spname, spprice, spdisc;
//            spdisc = pdisc.getText().toString();
//            spprice = pprice.getText().toString();
//            spname = pname.getText().toString();
//
//            Bitmap bitmap = ((BitmapDrawable) pimage.getDrawable()).getBitmap();
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//            byte[] imageinarayy = byteArrayOutputStream.toByteArray();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                imagedata = Base64.getEncoder().encodeToString(imageinarayy);
//            }
//            String path=Userdata.get(0).getPimage();
//
//            retro_instance.callApi().call_update(2,spname,spprice,spdisc,imagedata,path).enqueue(new Callback<model_class>() {
//                @Override
//                public void onResponse(Call<model_class> call, Response<model_class> response) {
//                    if (response.isSuccessful()){
//                        if(response.body().getResult()==1){
//                            Toast.makeText(Home_screen_actvity.this, "updated product", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<model_class> call, Throwable t) {
//
//                }
//            });
//
//
//            dialog.dismiss();
//        });
//
//        dialog.show();
//
//    }

    }
