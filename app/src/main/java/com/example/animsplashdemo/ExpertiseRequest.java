package com.example.animsplashdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ExpertiseRequest extends AppCompatActivity {
        private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expertise_request);

        image = (ImageView) findViewById(R.id.back);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Intent intent = new Intent(ExpertiseRequest.this, Home.class);
                startActivity(intent);
            }
        });
    }
    }
