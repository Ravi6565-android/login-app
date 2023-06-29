package com.example.retrofit_op.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofit_op.R;
import com.example.retrofit_op.model_class.Productdatum;

import java.util.List;


public class show_product_adapter extends RecyclerView.Adapter<show_product_adapter.userholder> {

    Context context;
    List<Productdatum> Userdata;

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

