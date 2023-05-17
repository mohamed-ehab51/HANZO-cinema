package com.fcai.ecinema;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Edit_Profile extends AppCompatActivity {
    private Button BTT;
    FirebaseFirestore dp=FirebaseFirestore.getInstance();
    private EditText username, fullname, email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        BTT=findViewById(R.id.continue_button);
        username=findViewById(R.id.username_text_input);
        fullname=findViewById(R.id.fullname_text_input);
        email=findViewById(R.id.email_text_input);
        phone=findViewById(R.id.editTextPhone);
        DocumentReference ref;
        if (signin.user==null)
        {
            ref=dp.collection("users").document(signup.user1);

        }
        else
        {
            ref=dp.collection("users").document(signin.user);

        }






        BTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1= username.getText().toString();
                String fullname1= fullname.getText().toString();
                String email1= email.getText().toString();
                String phone1= phone.getText().toString();
                HashMap hashMap=new HashMap();
                if (!email1.isEmpty())
                {

                    hashMap.put("email",email1);
                }
                if(!fullname1.isEmpty())
                {
                    hashMap.put("fullname",fullname1);

                }
                if (!username1.isEmpty())
                {
                    hashMap.put("user name",username1);
                }

                if(!phone1.isEmpty())
                {
                    hashMap.put("phone",phone1);
                }


                ref.update(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(Edit_Profile.this,"updated",Toast.LENGTH_SHORT).show();
                        if(!username1.isEmpty())
                        {
                            Profile.Name.setText(username1);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Edit_Profile.this,signin.user,Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }
    public  void gopro(View v)
    {
        Intent intent = new Intent(this, Profile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}