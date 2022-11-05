package com.example.smuctlms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

public class Login extends AppCompatActivity {

    private EditText inputEmail, inputPassword ;
    private TextView signUpBtn;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button  btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        auth =FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!= null){
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_login);



        inputEmail= (EditText)findViewById(R.id.username);
        inputPassword= (EditText)findViewById(R.id.password);
        progressBar= (ProgressBar)findViewById(R.id.progressBar);
        btnLogin=(Button)findViewById(R.id.loginButton);
        signUpBtn= (TextView) findViewById(R.id.signUpText);

        auth = FirebaseAuth.getInstance();

        Dexter.withContext(this)
                .withPermission(Manifest.permission.INTERNET)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {/*
                     ... */}
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {/* .

                    .. */}

                    @Override
                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }

                }).check();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter email address !",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter password ! ",Toast.LENGTH_LONG).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //auth user
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(!task.isSuccessful()){
                            //there was an error
                            if(password.length()<6){
                                inputPassword.setError(getString(R.string.minimum_pass));
                            }else {
                                Toast.makeText(Login.this,getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Intent intent = new Intent(Login.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, signup.class);
                startActivity(intent);
            }
        });

    }
}