package com.fcai.ecinema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    public static TextView Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Name = findViewById(R.id.username);
        Name.setText(signin.USER_NAME);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.inflateMenu(R.menu.contact);
        BottomNavigationView bottomNavigationView=findViewById(R.id.backpro);
        bottomNavigationView.setSelectedItemId(R.id.actionprofile);
        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.actionprofile) {
                return true;
            } else if (item.getItemId() == R.id.billboard) {
                startActivity(new Intent(getApplicationContext(), Billboard.class));
                return true;
            } else {
                return false;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item1){
            Intent intent = new Intent(this, Contact.class);
            startActivity(intent);
            return  true;
        }
        return true;
    }
    public  void logout(View v)
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, signin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public  void tickets(View v)
    {
        Intent intent = new Intent(Profile.this, Tickets.class);
        startActivity(intent);
    }
    public  void update(View v)
    {
        startActivity(new Intent(getApplicationContext(), Edit_Profile.class));
    }
}