package com.example.smuctlms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class teamscreen extends AppCompatActivity {

    public PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teamscreen);

        pdfView = (PDFView) findViewById(R.id.pdfbook);
        pdfView.fromAsset("my_team.pdf").defaultPage(0)
                .enableAnnotationRendering(true).scrollHandle(new DefaultScrollHandle(this))
                .load();
    }
}