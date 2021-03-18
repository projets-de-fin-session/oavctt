package com.example.animsplashdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Services extends AppCompatActivity {
    private ViewFlipper flipper;
    private ImageView image;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);


        int images[] = {R.drawable.vc, R.drawable.vct, R.drawable.image, R.drawable.oavct_tabarre_2};
        flipper = (ViewFlipper) findViewById(R.id.viewFliper);
        image = (ImageView) findViewById(R.id.back);
        btn1= findViewById ( R.id.bouton1 );

        for (int image: images){
            flipers(image);
        }


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Intent intent = new Intent(Services.this, Home.class);
                startActivity(intent);
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Intent intent = new Intent(Services.this, VehiculeInsurance.class);
                startActivity(intent);
            }
        });
    }

    public void flipers(int images){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);
        flipper.addView(imageView);
        flipper.setFlipInterval(4000);
        flipper.setAutoStart(true);

        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}