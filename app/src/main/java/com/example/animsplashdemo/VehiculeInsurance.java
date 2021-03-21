package com.example.animsplashdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.ImageView;
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

    private EditText editText1, editText2, editText3, editText4, editText5, editText6, editText7, editText8, editText9, editText10, editText11, editText12;

    private Button generate_pdf;

    private ImageView bak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicule_insurance);

        editText1 = findViewById(R.id.tex1);
        editText2 = findViewById(R.id.tex2);
        editText3 = findViewById(R.id.tex3);
        editText4 = findViewById(R.id.tex4);
        editText5 = findViewById(R.id.tex5);
        editText6 = findViewById(R.id.tex6);
        editText7 = findViewById(R.id.tex7);
        editText8 = findViewById(R.id.tex8);
        editText9 = findViewById(R.id.tex9);
        editText10 = findViewById(R.id.tex10);
        editText11 = findViewById(R.id.tex11);
        editText12 = findViewById(R.id.tex12);

        generate_pdf = findViewById(R.id.button);
        bak = findViewById(R.id.back);

        ActivityCompat.requestPermissions(VehiculeInsurance.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        generate_pdf.setOnClickListener(this);


        bak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Intent intent = new Intent(VehiculeInsurance.this,Home.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                if (!editText1.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty()
                        && !editText1.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty()
                        && !editText1.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty()
                        && !editText1.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty()
                        && !editText1.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty()
                        && !editText1.getText().toString().isEmpty() && !editText2.getText().toString().isEmpty()){

                    generatePdf(editText1.getText().toString(), editText2.getText().toString(),
                                editText3.getText().toString(),editText4.getText().toString(),
                                editText5.getText().toString(),editText6.getText().toString(),
                                editText7.getText().toString(),editText8.getText().toString(),
                                editText9.getText().toString(),editText10.getText().toString(),
                                editText11.getText().toString(),editText12.getText().toString());
                }
                break;

        }

    }

    public void generatePdf(String title, String desc, String title1, String desc1,String title2, String desc2,String title3, String desc3,String title4, String desc5,String title6, String desc6) {
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

            Paragraph titleParagraph1 = new Paragraph();
            titleParagraph.add(title1);
            titleParagraph.setSpacingAfter(10.0f);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            documented.add(titleParagraph1);

            Paragraph titleParagraph2 = new Paragraph();
            titleParagraph.add(title);
            titleParagraph.setSpacingAfter(10.0f);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            documented.add(titleParagraph2);

            Paragraph titleParagraph3 = new Paragraph();
            titleParagraph.add(title);
            titleParagraph.setSpacingAfter(10.0f);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            documented.add(titleParagraph3);

            Paragraph titleParagraph4 = new Paragraph();
            titleParagraph.add(title);
            titleParagraph.setSpacingAfter(10.0f);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            documented.add(titleParagraph4);

            Paragraph titleParagraph5 = new Paragraph();
            titleParagraph.add(title);
            titleParagraph.setSpacingAfter(10.0f);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);
            documented.add(titleParagraph5);


            Paragraph dscParagraph = new Paragraph();
            dscParagraph.add(desc);
            documented.add(dscParagraph);
            documented.close();

            Paragraph dscParagraph1 = new Paragraph();
            dscParagraph.add(desc1);
            documented.add(dscParagraph1);
            documented.close();

            Paragraph dscParagraph2 = new Paragraph();
            dscParagraph.add(desc2);
            documented.add(dscParagraph2);
            documented.close();

            Paragraph dscParagraph3 = new Paragraph();
            dscParagraph.add(desc3);
            documented.add(dscParagraph3);
            documented.close();

            Paragraph dscParagraph5 = new Paragraph();
            dscParagraph.add(desc5);
            documented.add(dscParagraph);
            documented.close();

            Paragraph dscParagraph6 = new Paragraph();
            dscParagraph.add(desc5);
            documented.add(dscParagraph6);
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
