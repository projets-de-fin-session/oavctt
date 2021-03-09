package com.example.animsplashdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FeedBack extends AppCompatActivity {
    private Button feedbak;
    private EditText etTo, atSubject, etMessage;
    private Button btnSend;
    private ImageView image;

    private String sEmail, sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        feedbak = (Button) findViewById(R.id.bouton_Id_feebac);
        atSubject = (EditText) findViewById(R.id.namemail_Id_Login);
        etTo = (EditText) findViewById(R.id.email_Id_fed);
        etMessage = (EditText) findViewById(R.id.EditText_FeedbackBody);

        image = (ImageView) findViewById(R.id.back);


        sEmail = "lucksonsurprice94@gmail.com";
        sPassword = "Jesus12345!?";



    image.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

                Intent intent = new Intent(FeedBack.this, Home.class);
                startActivity(intent);
                finish();

        }
    });

        feedbak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail,sPassword);


                    }
                });



                try {
                    Message message = new MimeMessage(session);

                    message.setFrom(new InternetAddress(sEmail));

                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(etTo.getText().toString().trim()));

                    message.setSubject(atSubject.getText().toString().trim());

                    message.setSubject(etMessage.getText().toString().trim());

                    new SendMail().execute(message);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }
//                Intent intent = new Intent(FeedBack.this, ForgetPassword.class);
//                startActivity(intent);
//                finish();
            }
        });



    }

    private class SendMail extends AsyncTask <Message, String,String>{

        private ProgressDialog progressDialog;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(FeedBack.this,"Please Wait...", "Send Mail...", true, false);
        }

        @Override
        protected String doInBackground(Message... messages) {

            try {
                Transport.send(messages[0]);

                return "Success !!";

            } catch (MessagingException e) {


                e.printStackTrace();

                return "Error";

            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();

            if (s.equals("Success !!")){
                AlertDialog.Builder builder = new AlertDialog.Builder(FeedBack.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324>Success</font>"));
                builder.setMessage("Email send Success. ");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        etMessage.setText("");
                        atSubject.setText("");
                        etTo.setText("");
                    }
                });

                builder.show();
            }else {

                Toast.makeText(FeedBack.this,"Something went wrong ",Toast.LENGTH_LONG).show();
            }
        }
    }
}