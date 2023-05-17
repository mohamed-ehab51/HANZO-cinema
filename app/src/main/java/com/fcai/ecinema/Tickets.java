package com.fcai.ecinema;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tickets extends AppCompatActivity {
    private DatabaseReference mDatabase;

    RecyclerView recyclerView;
    ArrayList<ticket_class> mainmodels= new ArrayList<>();
    ticket_adapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        recyclerView=findViewById(R.id.t_rv);
        mDatabase = FirebaseDatabase.getInstance("https://wireless-cinema-e0d2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("tickets").child(signin.user);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                ArrayList<String> arr= new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String data = snapshot.getKey();
                    arr.add(data);
                }
                for (int ind =0;ind<arr.size();ind++) {
                    String [] s= arr.get(ind).split(",");
                    ticket_class ticket = new ticket_class(s[0],s[1]+" "+s[2],s[3]);
                    mainmodels.add(ticket);
                }
                    mainAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Tickets.this, "Error retrieving data from Firebase Database", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager layoutManager =new LinearLayoutManager(Tickets.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mainAdapter=new ticket_adapter(Tickets.this,mainmodels);
        recyclerView.setAdapter(mainAdapter);
    }
    public  void gopro(View v)
    {
        Intent intent = new Intent(this, Profile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}