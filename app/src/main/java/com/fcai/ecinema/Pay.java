package com.fcai.ecinema;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Pay extends AppCompatActivity {
    Button b;
    ImageView im;
    TextView name,cat;
    String s ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        b= findViewById(R.id.payment_m);
        cat= findViewById(R.id.catpay);
        im= findViewById(R.id.imagepay);
        name= findViewById(R.id.namepay);
        cat.setText(Movie.categories);
        Glide.with(Pay.this)
                .load(Movie.movlogo)
                .into(im);
        name.setText(Movie.movname);
        s=getIntent().getStringExtra("seats");
        int counter =0;
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)=='U')
            {
                counter++;
            }
        }
        int x= counter*Integer.parseInt(Movie.pricee);
        b.setText(Integer.toString(x)+" EGP");

    }
    public  void onClick(View v)
    {
        Intent intent = new Intent(this, Invoice.class).putExtra("seats",s);
        startActivity(intent);
    }
    public  void onClick2(View v)
    {
        Intent intent = new Intent(this, Date.class);
        startActivity(intent);
    }
}