package com.example.yogamitra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText email;
    private EditText password,conpass;
    private Button register;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.editTextTextPassword2);
        conpass = findViewById(R.id.editTextTextPassword);
        register = findViewById(R.id.button3);
        auth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String con_password = conpass.getText().toString();
                if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password))
                {
                    Toast.makeText(RegisterActivity.this,"Empty Credentials",Toast.LENGTH_SHORT).show();
                }
                else if(txt_password.length()<6)
                {
                    Toast.makeText(RegisterActivity.this,"Invalid Password",Toast.LENGTH_SHORT).show();
                }
                else
                {    if(!con_password.equals(txt_password)) {
                    Toast.makeText(RegisterActivity.this, "Password didn't match!Retry", Toast.LENGTH_SHORT).show();
                }
                else

                    registeruser(txt_email,txt_password);
                }
            }
        });
    }

    private void registeruser(String txt_email, String txt_password) {


        auth.createUserWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                try {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, com.example.yogamitra.DetailsActivity.class));
                        // finish();

                    }
                }
                catch(Exception e)
                {
                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}