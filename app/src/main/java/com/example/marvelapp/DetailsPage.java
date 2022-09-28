package com.example.marvelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailsPage extends AppCompatActivity {

    TextView actorName, actorChar, bio;
    ImageView actorImage;
    ImageButton arrowBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);

        actorName = findViewById(R.id.actor_name);
        actorChar = findViewById(R.id.actor_character);
        bio = findViewById(R.id.actor_bio);
        actorImage = findViewById(R.id.actor_character_image1);
        arrowBack = findViewById(R.id.back_arrow);

        //Back Arrow
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsPage.this, MainActivity.class);
                startActivity(i);
            }
        });

        //Get Data
        Intent ii = getIntent();
        Bundle bun = ii.getExtras();
        if (bun!=null){
            actorName.setText(String.valueOf(bun.get("name")));
            actorChar.setText(String.valueOf(bun.get("char")));
            bio.setText(String.valueOf(bun.get("bio")));

            String img = String.valueOf(bun.get("image"));
            Log.d("TAG", "URL: "+img);

            Glide.with(this)
                    .load(String.valueOf(bun.get("image")))
                    .centerCrop()
                    .into(actorImage);
        }

    }
}