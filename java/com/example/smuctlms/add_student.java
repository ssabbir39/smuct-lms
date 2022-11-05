package com.example.smuctlms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;

public class add_student extends AppCompatActivity {

   int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        EditText editText = findViewById(R.id.editTextTextPersonName);
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID",-1);
        if(noteID != -1)
        {
            editText.setText(studentscreen.notes.get(noteID));
        }else {
            studentscreen.notes.add("");
            noteID = studentscreen.notes.size()-1;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                studentscreen.notes.set(noteID,String.valueOf(s));
                studentscreen.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences = getApplication().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(studentscreen.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}