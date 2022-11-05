package com.example.smuctlms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class signup extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private TextView btnSignIn;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth =FirebaseAuth.getInstance();



        inputEmail= (EditText)findViewById(R.id.username);
        inputPassword= (EditText)findViewById(R.id.password);
        progressBar= (ProgressBar)findViewById(R.id.progressBar2);
        btnLogin=(Button)findViewById(R.id.loginButton);
        btnSignIn= (TextView) findViewById(R.id.signUpText);




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
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(!task.isSuccessful()){
                            //there was an error
                            if(password.length()<6){
                                inputPassword.setError(getString(R.string.minimum_pass));
                            }else {
                                Toast.makeText(signup.this,getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Intent intent = new Intent(signup.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });
                
            }
        });


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this,Login.class);
                startActivity(intent);
            }
        });

    }
}