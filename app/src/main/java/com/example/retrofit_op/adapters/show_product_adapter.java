package com.example.retrofit_op.adapters;


import static com.example.retrofit_op.slapsh_screen.Splash_screen_activity.preferences;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit_op.home_sreen.Home_screen_actvity;
import com.example.retrofit_op.model_class.DeleteModel;
import com.example.retrofit_op.model_class.ProductAddModel;
import com.example.retrofit_op.model_class.model_class;
import com.example.retrofit_op.retro_instance;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit_op.R;
import com.example.retrofit_op.model_class.Product_get_model;
import com.example.retrofit_op.model_class.Productdatum;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class show_product_adapter extends RecyclerView.Adapter<show_product_adapter.userholder> {

    Context context;
    List<Productdatum> Userdata;
    String imagedata;


    public show_product_adapter(Context context, List<Productdatum> Userdata) {
        this.context = context;
        this.Userdata = Userdata;
    }

    @NonNull
    @Override
    public show_product_adapter.userholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.show_product_item_layout, parent, false);
        userholder holder = new userholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull show_product_adapter.userholder holder, int position) {
        holder.tname.setText(Userdata.get(position).getPname()+"name");
        holder.tprice.setText(Userdata.get(position).getPprice()+"price");
        Glide.with(context).load("https://my-e-commerce-app.000webhostapp.com/my_new_app/"+Userdata.get(position).getPimage()).into(holder.imageView);

        holder.tname.setOnClickListener(view -> {
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.add_product_dialog);

            EditText pname, pprice, pdisc;
            ImageView pimage;

            int uid;
            uid = preferences.getInt("userid", 0);
            Button add, cancel;
            pname = dialog.findViewById(R.id.apname);
            pprice = dialog.findViewById(R.id.apprice);
            pdisc = dialog.findViewById(R.id.apdis);
            pimage = dialog.findViewById(R.id.apimage);
            add = dialog.findViewById(R.id.add);
            cancel = dialog.findViewById(R.id.cancel);


            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

           pimage.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   CropImage.activity()
                           .setGuidelines(CropImageView.Guidelines.ON)
                           .start((Activity) context);
               }
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
                int id= Integer.parseInt(Userdata.get(position).getId());
                String path=Userdata.get(position).getPimage();


                retro_instance.callApi().call_update(id,spname,spprice,spdisc,imagedata,path).enqueue(new Callback<model_class>() {
                    @Override
                    public void onResponse(Call<model_class> call, Response<model_class> response) {
                        if(response.isSuccessful()){
                            if(response.body().getResult()==1){
                                Toast.makeText(context, "Updated Successfully ", Toast.LENGTH_LONG).show();
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

        });

        holder.tname.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                int id= Integer.parseInt(Userdata.get(position).getId());
                retro_instance.callApi().DELETE_MODEL_CALL(id).enqueue(new Callback<DeleteModel>() {
                    @Override
                    public void onResponse(Call<DeleteModel> call, Response<DeleteModel> response) {
                        if(response.isSuccessful()){
                            if(response.body().getResult()==1){
                                Toast.makeText(context, "item deleted successfully", Toast.LENGTH_LONG).show();
                                Userdata.remove(position);
                                notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteModel> call, Throwable t) {

                    }
                });

                        return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return Userdata.size();
    }

    public class userholder extends RecyclerView.ViewHolder {
        TextView tname, tprice;
        ImageView imageView;

        public userholder(@NonNull View itemView) {
            super(itemView);
            tname = itemView.findViewById(R.id.spname);
            tprice = itemView.findViewById(R.id.spprice);
            imageView = itemView.findViewById(R.id.spimage);
        }
    }
}

