package com.fcai.ecinema;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Add_movie extends AppCompatActivity {
    private static final int pick_req=1;
    public Uri imageuri;

    private EditText mov_name,cast,desc,rating,cat;
    private ImageView r3d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        mov_name = findViewById(R.id.mov_name_text_input);
        cast = findViewById(R.id.cast_text_input);
        desc = findViewById(R.id.desc_text_input);
        rating = findViewById(R.id.rating_text_input);
        cat = findViewById(R.id.cat_text_input);
        r3d = findViewById(R.id.movie_imageView);
    }
public void filechoose(View v) {
    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
    intent.setType("image/*");
    pickImageLauncher.launch(intent);
}

    private ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        Uri imageUri = result.getData().getData();
                        r3d.setImageURI(imageUri);
                        imageuri = imageUri;
                    }
                }
            }
    );

 public  void  bb(View v)
 {
     Intent intent = new Intent(Add_movie.this,Admin_Profile.class);
     intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
     startActivity(intent);
 }

    public  void cont(View v)
    {

        String casting = cast.getText().toString().trim();
        String movn = mov_name.getText().toString().trim();
        String descr = desc.getText().toString().trim();
        String ratin = rating.getText().toString().trim();
        String cate = cat.getText().toString().trim();
        if (TextUtils.isEmpty(mov_name.getText().toString())){
            mov_name.setError("please add Movie Name");
            return;
        }
        if (TextUtils.isEmpty(cast.getText().toString())){
            cast.setError("please add Cast");
            return;
        }
        if (TextUtils.isEmpty(desc.getText().toString())){
            desc.setError("please add Description");
            return;
        }
        if (TextUtils.isEmpty(rating.getText().toString())){
            rating.setError("please add Rating");
            return;
        }
        if (TextUtils.isEmpty(cat.getText().toString())){
            cat.setError("please add the categories");
            return;
        }
        if (imageuri == null) {
            Toast.makeText(Add_movie.this, "Please choose the poster", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent in = new Intent(getApplicationContext(), Add_movie2.class);
        in.putExtra("cast",casting);
        in.putExtra("photo",imageuri.toString());
        in.putExtra("name",movn);
        in.putExtra("desc", descr);
        in.putExtra("rating",ratin);
        in.putExtra("cat",cate);
        startActivity(in);


    }

}