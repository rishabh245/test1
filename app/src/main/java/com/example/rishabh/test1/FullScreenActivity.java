package com.example.rishabh.test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        ImageView imageView = findViewById(R.id.full_screen_image_view);
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");
        Glide.with(this).load(imageUrl).into(imageView);

    }
}
