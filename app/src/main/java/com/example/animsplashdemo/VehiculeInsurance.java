package com.example.animsplashdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
//import com.itextpdf.layout.Document;
//import com.itextpdf.kernel.pdf.PdfWriter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.annotation.Documented;

public class VehiculeInsurance extends AppCompatActivity {


    private EditText editText;
    private EditText noassurance;
    private EditText vehicule;
    private EditText noassurances;
    private EditText noplaque;
    private  EditText annedue;
    private Button submitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_vehicule_insurance );

        editText= findViewById ( R.id.editTextName );
        noassurance= findViewById ( R.id.noassurance );
        vehicule= findViewById ( R.id.vehicule);
        noassurance= findViewById ( R.id.noassurance );
        noplaque= findViewById ( R.id.noplaque );
        annedue= findViewById ( R.id.anneDue);
        submitButton = findViewById (R.id.button);

        ActivityCompat.requestPermissions ( VehiculeInsurance.this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED );

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


public void createMyPDF(View view){
        PdfDocument myPdfDocument= new PdfDocument ();
        PdfDocument.PageInfo myPageInfo= new PdfDocument.PageInfo.Builder ( 300,600,1 ).create ();
        PdfDocument.Page myPage= myPdfDocument.startPage (myPageInfo);

        Paint myPaint =new Paint ();
        String myString = editText.getText ().toString();
        int x= 10 , y=25;
        myPage.getCanvas().drawText (myString,x,y,myPaint);
        myPdfDocument.finishPage (myPage);
        String myFilePath= Environment.getExternalStorageDirectory().getPath () +"/myPDFFile.pdf";
        File myFile= new File ( myFilePath );
        try {
            myPdfDocument.writeTo ( new FileOutputStream ( myFile ) );

        }
        catch (Exception e){e.printStackTrace ();
        editText.setText ( "ERROR" );
        }
        myPdfDocument.close ();
}
}