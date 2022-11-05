package com.example.smuctlms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_activity extends AppCompatActivity {

    EditText title_input,author_input,pages_input;
    Button addBTN ,doneBTM;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().hide();

        title_input = findViewById(R.id.book_name);
        author_input = findViewById(R.id.dept_name);
        pages_input = findViewById(R.id.author_name);
        addBTN = findViewById(R.id.SaddBTN);
        doneBTM = findViewById(R.id.doneBTN);

        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(add_activity.this);
                myDB.addBook(title_input.getText().toString().trim(),
                        author_input.getText().toString().trim(),
                        pages_input.getText().toString().trim());
            }

        });
        doneBTM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_activity.this,bookscreen.class);
                startActivity(intent);
            }
        });
    }
}