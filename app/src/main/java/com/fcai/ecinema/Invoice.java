package com.fcai.ecinema;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Invoice extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ticket_class> mainmodels;
    ticket_adapter mainAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        String a=getIntent().getStringExtra("seats");
        int counter =0;
        a=a.replace("/","");
        ArrayList <Integer> hold= new ArrayList<>();
        for(int i=0;i<a.length();i++)
        {
            if(a.charAt(i)=='U')
            {
                counter++;
                hold.add(i);
            }
        }

        ArrayList <String> seatsp= new ArrayList<>();
        for(int k=0;k<hold.size();k++)
        {
            int place = hold.get(k);
            seatsp.add(Integer.toString(place));
        }
        a=a.replace('U','R');
        String cc=a;
        DatabaseReference datesRef = FirebaseDatabase.getInstance("https://wireless-cinema-e0d2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("date&time");
        datesRef.child(Movie.movname).child(Date.day).child(Date.time).setValue(a);
        recyclerView=findViewById(R.id.tic);
        mainmodels =new ArrayList<>();
        for(int i=0;i<seatsp.size();i++)
        {
            int c = Integer.parseInt(seatsp.get(i));
            c++;
            String v=Integer.toString(c);
            ticket_class model =new ticket_class(Movie.movname,Date.date+" "+Date.time,v);
            mainmodels.add(model);
        }

        LinearLayoutManager layoutManager =new LinearLayoutManager(Invoice.this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mainAdapter=new ticket_adapter(Invoice.this,mainmodels);
        recyclerView.setAdapter(mainAdapter);



        DatabaseReference ticref = FirebaseDatabase.getInstance("https://wireless-cinema-e0d2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("tickets");
        for(int i=0;i<seatsp.size();i++)
        {
            String z= String.valueOf(Integer.parseInt(seatsp.get(i))+1);

            ticref.child(signin.user).child(Movie.movname+","+Date.date+","+Date.time+","+z).setValue("");
        }


        MediaPlayer mp = MediaPlayer.create(this,R.raw.mon);
        try {
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  void onClick(View v)
    {
        Intent intent = new Intent(this, Billboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void share(View v){
        String T= "HI , i just bought tickets to the movie : "+Movie.movname+ " using HANZO cinema app , join me ";
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, T);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent,"Share to"));

    }
}