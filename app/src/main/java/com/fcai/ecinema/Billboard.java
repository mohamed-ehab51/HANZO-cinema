package com.fcai.ecinema;

import static android.content.ContentValues.TAG;

import static com.fcai.ecinema.signin.USER_type;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.guieffect.qual.UI;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Billboard extends AppCompatActivity {
    private DatabaseReference mDatabase;

    RecyclerView recyclerView;
    ArrayList<Mainmodel> mainmodels;
    MainAdapter mainAdapter;
    String[] item={"Assuit","Cairo","Alexandria"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItem;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billboard);
        recyclerView=findViewById(R.id.recycler_view);
        mainmodels =new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance("https://wireless-cinema-e0d2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("movies");

        // add a ValueEventListener to the reference
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // retrieve the data and update the UI
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String movname = snapshot.child("name").getValue(String.class);
                    String movlogo = snapshot.child("poster").getValue(String.class);
                    Mainmodel model = new Mainmodel(Uri.parse(movlogo), movname);
                    mainmodels.add(model);
                }
                // update the RecyclerView adapter
                mainAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // handle any errors
                Toast.makeText(Billboard.this, "Error retrieving data from Firebase Database", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager layoutManager =new LinearLayoutManager(Billboard.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mainAdapter=new MainAdapter(Billboard.this,mainmodels);
        recyclerView.setAdapter(mainAdapter);


        BottomNavigationView bottomNavigationView=findViewById(R.id.back);
        bottomNavigationView.setSelectedItemId(R.id.billboard);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.actionprofile) {
                if(USER_type.equals("basic")){
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                    return true;
                }else{
                    startActivity(new Intent(getApplicationContext(), Admin_Profile.class));
                    return true;
                }

            } else return item.getItemId() == R.id.billboard;
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        BottomNavigationView bottomNavigationView=findViewById(R.id.back);
        bottomNavigationView.setSelectedItemId(R.id.billboard);


    }
}
