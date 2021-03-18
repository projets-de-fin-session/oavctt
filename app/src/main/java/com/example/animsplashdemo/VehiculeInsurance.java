
    
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
    private ImageView bac;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);


        int images[] = {R.drawable.vc, R.drawable.vct, R.drawable.image, R.drawable.oavct_tabarre_2};
        flipper = (ViewFlipper) findViewById(R.id.viewFliper);
        btn = findViewById(R.id.bouton1);
        bac = (ImageView) findViewById(R.id.backs);

        for (int image: images){
            flipers(image);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vhome = new Intent(Services.this, VehiculeInsurance.class);
                startActivity(vhome);
                finish();
            }
        });

        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Intent vhome = new Intent(Services.this, Home.class);
                startActivity(vhome);
                finish();
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

