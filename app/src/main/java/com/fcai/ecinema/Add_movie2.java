package com.fcai.ecinema;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Add_movie2 extends AppCompatActivity {
    private EditText cost,se,url;
    private ImageView imageView;
    private final String[] times= new String[]{"9:12","12:3","3:6","6:9","9:12 night","12:3 night"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie2);
        cost = findViewById(R.id.cost_text_input);
        se = findViewById(R.id.available_in_text_input);
        url = findViewById(R.id.trailer_url_textf);
        imageView = findViewById(R.id.addedfilm);
        imageView.setImageURI(Uri.parse(getIntent().getStringExtra("photo")));
    }
    public  void backstep(View v)
    {
        startActivity(new Intent(getApplicationContext(), Add_movie.class));
    }

    public void mov_add(View v) {

        DatabaseReference moviesRef = FirebaseDatabase.getInstance("https://wireless-cinema-e0d2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("movies");

        String urll = url.getText().toString().trim();
        String costp = cost.getText().toString().trim();
        String sea = se.getText().toString().trim();
        if (TextUtils.isEmpty(se.getText().toString())){
            se.setError("please enter number of seats");
            return;
        }
        if (TextUtils.isEmpty(cost.getText().toString())){
            cost.setError("please enter the cost of the ticket");
            return;
        }
        if (TextUtils.isEmpty(url.getText().toString())){
            url.setError("please add the url of the trailer");
            return;
        }
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();

        // Create a reference to the image file you want to upload
        Uri fileUri = Uri.parse(getIntent().getStringExtra("photo"));
        StorageReference imageRef = storageRef.child("images/"+getIntent().getStringExtra("name")+".jpg");
        imageRef.putFile(fileUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUrl = uri.toString();
                                Map<String, Object> day = new HashMap<>();
                                Movie_Data m= new Movie_Data(getIntent().getStringExtra("name"),urll,getIntent().getStringExtra("cast"),costp,sea,getIntent().getStringExtra("desc"),imageUrl,getIntent().getStringExtra("rating"),getIntent().getStringExtra("cat"));
                                moviesRef.child(getIntent().getStringExtra("name")).setValue(m)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                DatabaseReference datesRef = FirebaseDatabase.getInstance("https://wireless-cinema-e0d2b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("date&time");
                                                Map<String, Object> day = new HashMap<>();
                                                double numm= Integer.parseInt(sea);
                                                numm=Math.floor(numm/8);
                                                String seats="AAAAAAAA";
                                                seats=seats.repeat((int)numm);
                                                for(int i=0;i<5;i++)
                                                {
                                                    Map<String, Object> dat = new HashMap<>();
                                                    if(i%2==0) {
                                                        for (int d = 0; d < 3; d++) {
                                                            dat.put(times[d], seats);
                                                        }
                                                    }
                                                    else {
                                                        for (int d = 3; d < 6; d++) {
                                                            dat.put(times[d], seats);
                                                        }
                                                    }
                                                    day.put("day "+i,dat);
                                                }
                                                datesRef.child(getIntent().getStringExtra("name")).setValue(day);
                                                Toast.makeText(Add_movie2.this,"Movie Posted successfully",Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Add_movie2.this, Admin_Profile.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(intent);

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(Add_movie2.this,"ERR , please try again",Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Add_movie2.this,"ERR in uploading the photo , please try again",Toast.LENGTH_SHORT).show();
                    }
                });
                    }
                        }
