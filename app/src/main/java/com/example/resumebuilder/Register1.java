package com.example.resumebuilder;

import static com.example.resumebuilder.R.*;
import static com.example.resumebuilder.R.id.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register1 extends AppCompatActivity {


    TextInputEditText textusername,textemail,textpassword,textconfirmpassword;
    Button register;
    FirebaseAuth auth;

    @SuppressLint("WrongViewCast")
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(Register1.this, Resume.class));
        }
    }

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_register);
         TextView btn=findViewById(R.id.haveaccount);
         textusername=findViewById(inputname);
         textemail=findViewById(inputemail);
         textpassword=findViewById(inputpw);
         textconfirmpassword=findViewById(inputcpw);
         register=findViewById(R.id.buttonreg);
         auth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username,email,password,confrimpassword;
                username=String.valueOf(textusername.getText());
                email=String.valueOf(textemail.getText());
                password=textpassword.getText().toString();
                confrimpassword=textconfirmpassword.getText().toString();
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(Register1.this, "Enter User name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register1.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register1.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(confrimpassword)){
                    Toast.makeText(Register1.this, "Enter confirmpassword", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    Toast.makeText(Register1.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(Register1.this, "Account Failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                startActivity(new Intent(Register1.this,Login.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register1.this,Login.class));
            }
        });
    }
}