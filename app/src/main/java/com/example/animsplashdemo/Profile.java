package com.example.animsplashdemo;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;




public class Profile extends AppCompatActivity {
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        image = (ImageView) findViewById(R.id.back);

        //image.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
               // onBackPressed();
               // Intent intent = new Intent(Profile.this, Home.class);
               // startActivity(intent);
          //  }
       // });
    }
}