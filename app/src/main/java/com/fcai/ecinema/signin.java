package com.fcai.ecinema;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class signin extends AppCompatActivity {
    String type;

    public static String USER_NAME;
    public static String USER_type;
    private EditText password_sp,email_sp;
    public static String user;
    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
                Intent intent = new Intent(signin.this, Billboard.class);
                startActivity(intent);
                db.collection("users")
                        .whereEqualTo("email", mAuth.getCurrentUser().getEmail())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        USER_NAME = document.getString("user name");
                                        USER_type = document.getString("type");
                                    }
                                    user= mAuth.getUid();
                                }
                            }
                        });
            }
    }
    public void signup(View v) {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }
    public void si(View v){
        //mAuth = FirebaseAuth.getInstance();
        email_sp = findViewById(R.id.email_signin);
        password_sp = findViewById(R.id.Password_signin);
        String email = email_sp.getText().toString().trim();
        String password = password_sp.getText().toString().trim();
        if (TextUtils.isEmpty(email_sp.getText().toString())){
            email_sp.setError("please enter your email");
            return;
        }
        if (TextUtils.isEmpty(password_sp.getText().toString())){
            password_sp.setError("please enter the password");
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user= mAuth.getUid();
                            load_user_data();
                        } else {
                            Toast.makeText(signin.this, "Signing in failed, check your credentials",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    public void load_user_data()
    {
        email_sp = findViewById(R.id.email_signin);
        String email = email_sp.getText().toString().trim();
        db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                USER_NAME=document.getString("user name");
                                USER_type=document.getString("type");
                                Intent intent;
                                if(USER_type.equals("basic")){
                                intent = new Intent(signin.this, Billboard.class);
                            }
                            else{
                                intent = new Intent(signin.this, Admin_Profile.class);
                            }
                                startActivity(intent);

                            }
                        } else {
                            Toast.makeText(signin.this, "Error in loading data",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}