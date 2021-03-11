package com.example.animsplashdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

        private EditText loginEmail;
        private EditText loginpassword;
        private Button loginBouton;
        private Button button;

        private TextView forgetPassword;

        private FirebaseAuth mAuth;

        private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        button = (Button) findViewById(R.id.bouton_Id_SignUp);
        loginBouton = (Button) findViewById(R.id.bouton_Id_Login);
        loginEmail = (EditText) findViewById(R.id.email_Id_Login);
        loginpassword = (EditText) findViewById(R.id.password_Id_Login);
        forgetPassword = (TextView) findViewById(R.id.forgetId);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);



        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ForgetPassword.class);
                startActivity(intent);
                finish();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });


        loginBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString().trim();
                String pass  = loginpassword.getText().toString().trim();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
                    progressDialog.setTitle("Login");
                    progressDialog.setMessage("Please wait......");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){

                                senTomain();


                            }else {

                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(Login.this, "Error" + errorMessage, Toast.LENGTH_LONG).show();

                            }
                     progressDialog.dismiss();
                        }
                    });


                }


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

        Intent intent = new Intent(Login.this, Home.class);
        startActivity(intent);
        finish();
    }
}