package com.fcai.ecinema;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Movie extends AppCompatActivity {
    public static String movname;
    public static String movlogo;
    public static String categories;
    public static String description;
    public static String rating;

    public static String url;
    public static String cast;
    TextView textView,textView2,textView1;
    ImageView imageView;
    RatingBar ratingBar;
    public  static String pricee;
    private DatabaseReference mDatabase;
    public static String movn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        movn = getIntent().getStringExtra("movname");


        mDatabase = FirebaseDatabase.getInstance("https://wireless-cinema-e0d2b-default-rtdb.europe-west1.firebasedatabase.app").getReference("movies");
        textView=(TextView)findViewById(R.id.category_t);
        textView2=(TextView)findViewById(R.id.descr_t);
        textView1=(TextView)findViewById(R.id.cast_t)    ;
        TextView textView4 = (TextView) findViewById(R.id.movie_name_t);
        imageView=(ImageView) findViewById(R.id.poster_view);
        ratingBar=(RatingBar) findViewById(R.id.ratingBar);
        Button Button = (android.widget.Button) findViewById(R.id.trailerbutton);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot snapshot= dataSnapshot.child(movn);
                movname =snapshot.child("name").getValue(String.class);
                movlogo = snapshot.child("poster").getValue(String.class);
                categories = snapshot.child("categories").getValue(String.class);
                description = snapshot.child("description").getValue(String.class);
                rating = snapshot.child("rating").getValue(String.class);
                url = snapshot.child("url").getValue(String.class);

                cast = snapshot.child("cast").getValue(String.class);
                pricee=snapshot.child("cost").getValue(String.class);

                System.out.println(movlogo);
                System.out.println( categories );

                textView4.setText(movname);
                textView.setText(categories);
                textView1.setText(cast);
                textView2.setText(description);

                Glide.with(Movie.this)
                        .load(movlogo)
                        .into(imageView);
                ratingBar.setRating(Float.parseFloat(rating));
                Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gotoUrl(url);
                    }

                });




                // update the RecyclerView adapter

            }

            private void gotoUrl(String url) {
                Uri uri=Uri.parse(url);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // handle any errors
                Toast.makeText(Movie.this, "Error retrieving data from Firebase Database", Toast.LENGTH_SHORT).show();
            }
        });

    }



    public  void gobill2(View v)
    {
        Intent intent = new Intent(this, Billboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public  void godate(View v)
    {
        startActivity(new Intent(getApplicationContext(), Date.class));
    }

}