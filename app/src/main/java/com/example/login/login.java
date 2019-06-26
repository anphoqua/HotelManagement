package com.example.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText et1,et2;
    Button login;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        et1 =(EditText)findViewById(R.id.txtUser);
        et2 = (EditText)findViewById(R.id.txtPassword);
        login = (Button)findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et1.getText().toString();
                String password = et2.getText().toString();
                Boolean checkemailpass = db.emailpassword(email,password);
                if (checkemailpass==true) {
                    Toast.makeText(login.this, "Login Thành Công.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(login.this, MainActivity.class);
                    startActivity(i);
                }

                else {
                    Toast.makeText(getApplicationContext(),"Sai User hoặc Password.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

