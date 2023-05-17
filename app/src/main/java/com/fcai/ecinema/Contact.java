package com.fcai.ecinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Contact extends AppCompatActivity {
    TextView hot;
    TextView email;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        hot = findViewById(R.id.hotline);
        hot.setOnClickListener(view -> {
            Uri number = Uri.parse("tel:10040105");
            Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
            startActivity(callIntent);
        });
        email = findViewById(R.id.oemail);
        email.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:hanzo.cinemas.egypt@gmail.com"));
            startActivity(intent);
        });
        back = findViewById(R.id.bac);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(Contact.this,Profile.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}