package com.example.animsplashdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    private Button button;
    private EditText signupName;
    private EditText signupEmail;
    private EditText signupPassword;
    private EditText SignConfirmPass;
    private EditText signupPhone;
    private Button signUpBouton;

    private ImageView image;

    private FirebaseAuth mAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        button = (Button) findViewById(R.id.bouton_Id_Login);
        signupName = (EditText) findViewById(R.id.name_Id_SignUp);
        signupEmail = (EditText) findViewById(R.id.email_Id_SignUP);
        signupPhone = (EditText) findViewById(R.id.phone_Id_SignUp);
        signupPassword = (EditText) findViewById(R.id.password_Id_SignUp);
        SignConfirmPass = (EditText) findViewById(R.id.confirmpass_Id_SignUp);
        signUpBouton = (Button) findViewById(R.id.bouton_Id_SignUp);

        image = (ImageView) findViewById(R.id.back);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
                finish();
            }
        });


//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//                Intent intent = new Intent(SignUp.this, Login.class);
//                startActivity(intent);
//            }
//        });


        signUpBouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = signupName.getText().toString().trim();
                String email = signupEmail.getText().toString().trim();
                String phone = signupPhone.getText().toString().trim();
                String pss = signupPassword.getText().toString().trim();
                String Cpass = SignConfirmPass.getText().toString().trim();

               if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pss) && !TextUtils.isEmpty(Cpass)){

                   if (pss.equals(Cpass)){

                       progressDialog.setTitle("Login");
                       progressDialog.setMessage("Please wait......");
                       progressDialog.setCanceledOnTouchOutside(false);
                       progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(email,pss).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                
                                senTomain();

                            }else {

                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(SignUp.this, "Error", Toast.LENGTH_LONG).show();

                            }
                    progressDialog.dismiss();

                        }
                    });


                   }else {


                       Toast.makeText(SignUp.this, "password doesn't mach", Toast.LENGTH_LONG).show();
                   }

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
        Intent intent = new Intent(SignUp.this, Login.class);
        startActivity(intent);
        finish();
    }
}