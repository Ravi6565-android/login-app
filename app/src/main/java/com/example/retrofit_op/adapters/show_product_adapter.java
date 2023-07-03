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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit_op.getPosition;
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
    getPosition getPosition;

    public show_product_adapter(Context context, List<Productdatum> Userdata,getPosition getPosition) {
        this.context = context;
        this.Userdata = Userdata;
        this.getPosition = getPosition;
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu= new PopupMenu(context,itemView);
                    popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            if(menuItem.getItemId()==R.id.update){

                                getPosition.getPosition(getAdapterPosition(),true);

                            }else if (menuItem.getItemId()==R.id.delete)
                            {
                                int id= Integer.parseInt(Userdata.get(getAdapterPosition()).getId());
                                retro_instance.callApi().DELETE_MODEL_CALL(id).enqueue(new Callback<DeleteModel>() {
                                    @Override
                                    public void onResponse(Call<DeleteModel> call, Response<DeleteModel> response) {
                                        if(response.isSuccessful()){
                                            if(response.body().getResult()==1){
                                                Toast.makeText(context, "item deleted successfully", Toast.LENGTH_LONG).show();
                                                Userdata.remove(getAdapterPosition());
                                                notifyDataSetChanged();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<DeleteModel> call, Throwable t) {

                                    }
                                });
                            }
                            return false;
                        }
                    });

                    popupMenu.show();

                }
            });
        }
    }
}

