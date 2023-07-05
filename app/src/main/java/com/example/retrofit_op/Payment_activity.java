package com.example.retrofit_op;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Payment_activity extends AppCompatActivity implements PaymentResultListener {

ImageView pimage;
TextView txtname,txtprice;
Button payment;
int famount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        init();
        String image=getIntent().getStringExtra("image");
        String name=getIntent().getStringExtra("name");
        String price=getIntent().getStringExtra("price");
        Picasso.get().load("https://my-e-commerce-app.000webhostapp.com/my_new_app/"+image).into(pimage);
        txtname.setText(name);
        txtprice.setText("rs "+price);
        famount=100 * Integer.parseInt(price);
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Checkout checkout= new Checkout();
                checkout.setKeyID("");
               Drawable drawable=pimage.getDrawable();
                checkout.setImage(Integer.parseInt(drawable.toString()) );

                JSONObject  object= new JSONObject();
                try {
                    object.put("name",name);
                    object.put("amount",famount);
                    object.put("prefill.contact","6351274120");
                    object.put("prefill.email","ravichauhanis420@gmail.com");
                    object.put("currency", "INR");
                    checkout.open(Payment_activity.this,object);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }
    private void init(){
        pimage=findViewById(R.id.Pimage);
        txtname=findViewById(R.id.Pname);
        txtprice=findViewById(R.id.Pprice);
        payment=findViewById(R.id.Ppayment);
    }

    @Override
    public void onPaymentSuccess(String s) {

    }

    @Override
    public void onPaymentError(int i, String s) {

    }
}
