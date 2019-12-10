package com.example.taskmanagementapp.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.taskmanagementapp.R;

public class Regis_Login extends AppCompatActivity {

    Button btn_register,btn_login,home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis__login);

        btn_login =  findViewById(R.id.login);
        btn_register =  findViewById(R.id.register);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Regis_Login.this, Login.class));
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Regis_Login.this, Register.class));
            }
        });

    }
}
