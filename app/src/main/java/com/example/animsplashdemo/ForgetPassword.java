package com.example.animsplashdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class ForgetPassword extends AppCompatActivity {
    private Button resert;
    private EditText email;
    private ImageView imagee;
    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mAuth = FirebaseAuth.getInstance();

        resert = (Button) findViewById(R.id.resertId);
        email = (EditText) findViewById(R.id.emailId);

        imagee = (ImageView) findViewById(R.id.back);

        progressDialog = new ProgressDialog(this);


        resert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Login");
                progressDialog.setMessage("Please wait......");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                String emails = email.getText().toString().trim();

                if (TextUtils.isEmpty(emails)){
                    email.setError("please enter your email");
                    email.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
                    email.setError("Email is invalid");
                    email.requestFocus();
                }else {

                    mAuth.sendPasswordResetEmail(emails).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(ForgetPassword.this,"Reset link send to your Email",Toast.LENGTH_LONG).show();
                            senTomain();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(ForgetPassword.this,"Error link not send" + e.getMessage(),Toast.LENGTH_LONG).show();


                        }
                    });
                }
            }
        });

        imagee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Intent intent = new Intent(ForgetPassword.this, Login.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){

            senTomain();
        }

    }

    private void senTomain() {

        Intent intent = new Intent(ForgetPassword.this, Login.class);
        startActivity(intent);
        finish();
    }
}