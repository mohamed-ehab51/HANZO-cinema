package com.fcai.ecinema;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class intro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        MediaPlayer mp = MediaPlayer.create(this,R.raw.intro);
        try {
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Handler().postDelayed(() -> {
            Intent intent = new Intent( intro.this, signin.class);
            startActivity(intent);
            finish();
        }, 2000);

    }
}