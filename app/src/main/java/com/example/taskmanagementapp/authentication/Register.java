package com.example.taskmanagementapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.taskmanagementapp.R;
import com.github.florent37.materialtextfield.MaterialTextField;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    MaterialTextField username, email, phone_num, user_role, password;
    Button btn_Register;
    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressBar registerProgress;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerProgress = findViewById(R.id.registerProgress);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone_num = findViewById(R.id.mobile_number);
        user_role = findViewById(R.id.user_role);
        password = findViewById(R.id.password);

        btn_Register = findViewById(R.id.btn_register);

        auth = FirebaseAuth.getInstance();

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerProgress.setVisibility(View.VISIBLE);
                String txt_username = username.getEditText().getText().toString();
                String txt_email = email.getEditText().getText().toString();
                String txt_phone = phone_num.getEditText().getText().toString();
                String txt_user_role = user_role.getEditText().getText().toString();
                String txt_password = password.getEditText().getText().toString();

                if (TextUtils.isEmpty(txt_username)  || TextUtils.isEmpty(txt_user_role) || TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_phone)|| TextUtils.isEmpty(txt_password)) {

                    Toast.makeText(Register.this, "All Fields are Required", Toast.LENGTH_SHORT).show();

                } else if (txt_password.length() < 6) {

                    Toast.makeText(Register.this, "Password must be at 6 character", Toast.LENGTH_SHORT).show();

                } else {
                    register(txt_username, txt_user_role , txt_email, txt_phone, txt_password);
                }



            }
        });


    }

    private void register(final String username, final String user_role , final String email, final String phone_num, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            registerProgress.setVisibility(View.INVISIBLE);
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String UserID = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(UserID);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", UserID);
                            hashMap.put("username", username);
                            hashMap.put("gender", user_role);
                            hashMap.put("email",email);
                            hashMap.put("Mobile", phone_num);
                            hashMap.put("imageURL", "default");


                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {

                                        Intent intent = new Intent(Register.this, Login.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();


                                    }
                                }
                            });


                        } else {
                            Toast.makeText(Register.this, "You Can't register with this email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
