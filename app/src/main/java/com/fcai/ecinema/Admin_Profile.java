package com.fcai.ecinema;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Admin_Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        TextView Name = findViewById(R.id.username);
        Name.setText(signin.USER_NAME);
        BottomNavigationView bottomNavigationView=findViewById(R.id.backpro);
        bottomNavigationView.setSelectedItemId(R.id.actionprofile);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.actionprofile) {
                return true;
            } else if (item.getItemId() == R.id.billboard) {
                Intent intent= new Intent (getApplicationContext(), Billboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
    }
    public  void logout(View v)
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent= new Intent(getApplicationContext(), signin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
    public  void soldtickets(View v)
    {
        startActivity(new Intent(getApplicationContext(), Sold_tickets.class));
    }
    public  void addf(View v)
    {
        startActivity(new Intent(getApplicationContext(), Add_movie.class));
    }
}