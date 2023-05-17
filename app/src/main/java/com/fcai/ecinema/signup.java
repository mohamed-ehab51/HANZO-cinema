package com.fcai.ecinema;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class signup extends AppCompatActivity {
    public static String user1;

    private EditText phone_sp,password_sp,email_sp,name_sp;
    public FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name_sp = findViewById(R.id.name_sp);
        email_sp = findViewById(R.id.email_sp);
        password_sp = findViewById(R.id.Password_sp);
        phone_sp = findViewById(R.id.phone_sp);
        mAuth = FirebaseAuth.getInstance();


    }


    public void go_billboard(View v) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        String name = name_sp.getText().toString().trim();
        String email = email_sp.getText().toString().trim();
        String password = password_sp.getText().toString().trim();
        String phone = phone_sp.getText().toString().trim();
        if (TextUtils.isEmpty(name_sp.getText().toString())){
            name_sp.setError("this field is required");
            return;
        }
        if (TextUtils.isEmpty(email_sp.getText().toString())){
            email_sp.setError("this field is required");
            return;
        }
        if (TextUtils.isEmpty(password_sp.getText().toString())){
            password_sp.setError("this field is required");
            return;
        }
        if (TextUtils.isEmpty(phone_sp.getText().toString())){
            phone_sp.setError("this field is required");
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        user.put("user name",name);
                        user.put("UserID", Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
                        user.put("password", password);
                        user.put("type","basic");
                        user.put("email", email);
                        user.put("phone", phone);
                        db.collection("users").document(Objects.requireNonNull(mAuth.getCurrentUser()).getUid())
                                .set(user)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void avoid) {
                                        Toast.makeText(signup.this,"User registered successfully",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(signup.this, signin.class);
                                        user1= mAuth.getUid();
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);



                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(signup.this,"ERR , check your internet connection",Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(signup.this,"ERR , check your inputs",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void sign_in(View v) {
        Intent intent = new Intent(this, signin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}