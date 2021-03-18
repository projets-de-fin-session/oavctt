package com.example.animsplashdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class VehiculeInsurance extends AppCompatActivity implements View.OnClickListener {
    private EditText myEditText;

    private EditText title, desc, txtn, txtasu, vehi, pla, ane;

    private Button generate_pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicule_insurance);

        txtn = findViewById(R.id.editTextName);
        txtasu = findViewById(R.id.noassurance);
        vehi = findViewById(R.id.vehicule);
        pla = findViewById(R.id.noplaque);
        ane = findViewById(R.id.anneDue);
        generate_pdf = findViewById(R.id.button);

        ActivityCompat.requestPermissions(VehiculeInsurance.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        generate_pdf.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (!txtn.getText().toString().isEmpty() && !txtasu.getText().toString().isEmpty()){

                    generatePdf(txtn.getText().toString(), txtasu.getText().toString());
                }
                break;

        }

    }

    public void generatePdf(String title, String desc) {
        try {
            String pah = getExternalFilesDir(null) + "/PDF practice";
            File file = new File(pah);
            if (!file.exists()) {
                file.mkdirs();
            }

            File filepdf = new File(file.getAbsolutePath() + "MYPDF_" + getCurrentTime() + " " + getToDaysDate() + " " + ".pdf");
            if (!filepdf.exists()){
                filepdf.createNewFile();
            }

            Document documented = new Document();
            PdfWriter.getInstance(documented, new FileOutputStream(filepdf.getAbsoluteFile()));
            documented.open();

            Paragraph titleParagraph = new Paragraph();
            titleParagraph.add(title);
            titleParagraph.setSpacingAfter(10.0f);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            documented.add(titleParagraph);

            Paragraph dscParagraph = new Paragraph();
            dscParagraph.add(desc);
            documented.add(dscParagraph);
            documented.close();

            Toast.makeText(this, "you are save a padf document" + filepdf.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

        private String getCurrentTime() {
            return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
        }

        private String getToDaysDate() {
            return new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(new Date());
        }
    }
