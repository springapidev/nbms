package com.coderbd.noticeboard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.coderbd.noticeboard.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Date;

public class SignUpActivity extends AppCompatActivity {

    private RadioButton rInstitute, rTeacher, rStudent;
    private EditText eName, eEmail, ePass;
    private ImageView btnSignUp, btnLogin, emailBg;
    private RadioGroup radioGroup;

    private ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        rInstitute = (RadioButton) findViewById(R.id.radioInstitute);
        rTeacher = (RadioButton) findViewById(R.id.radioTeacher);
        rStudent = (RadioButton) findViewById(R.id.radioStudent);

        eName = (EditText) findViewById(R.id.editTextInstitute);
        eEmail = (EditText) findViewById(R.id.editTextEmail);
        ePass = (EditText) findViewById(R.id.editTextPass);

        btnSignUp = (ImageView) findViewById(R.id.signup);
        btnLogin = (ImageView) findViewById(R.id.login);
        emailBg = (ImageView) findViewById(R.id.imageView33);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        radioGroup = (RadioGroup) findViewById(R.id.userType);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rInstitute.isChecked()) {
                    eName.setHint("Enter Institute Name");
                } else if (rTeacher.isChecked()) {
                    eName.setHint("Enter Institute ID");
                } else if (rStudent.isChecked()) {
                    eName.setHint("Enter Institute ID");
                }
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = eName.getText().toString().trim();
                final String email = eEmail.getText().toString().trim();
                final String password = ePass.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    if (rInstitute.isChecked()) {
                        Toast.makeText(SignUpActivity.this, "Enter Institute Name ", Toast.LENGTH_SHORT).show();
                    } else if (rTeacher.isChecked()) {
                        Toast.makeText(SignUpActivity.this, "Enter Institute ID ", Toast.LENGTH_SHORT).show();
                    } else if (rStudent.isChecked()) {
                        Toast.makeText(SignUpActivity.this, "Enter Institute ID ", Toast.LENGTH_SHORT).show();
                    }

                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignUpActivity.this, "Enter Email Address ", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUpActivity.this, "Enter Password ", Toast.LENGTH_SHORT).show();
                }

                if (password.length() < 4) {
                    Toast.makeText(SignUpActivity.this, "Password too Short!!!", Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    String authID = firebaseAuth.getCurrentUser().getUid();

                                    if (rInstitute.isChecked()) {

                                    User userForInstitue = new User(authID, name, "No Photo", email, "0168600000", "100 Dhaka", "Dhaka", "Dhaka", "BD", true, new Date(), null, "1052", "1980", "admin", null, null, null, null);
                                                                         databaseReference.child(authID).setValue(userForInstitue);
                                    } else if (rTeacher.isChecked()) {
                                        User userForTeacher = new User(authID, "Mr. Teacher", "No Photo", email, "0178600022", "10 Dhaka", "Dhaka", "Dhaka", "BD", false, new Date(), name, null, null, "teacher", "None", null, null, "Teacher");
                                        databaseReference.child(authID).setValue(userForTeacher);
                                    } else if (rStudent.isChecked()) {
                                        User userForStudent = new User(authID, "Mr. Student", "No Photo", email, "0188600022", "10 Dhaka", "Dhaka", "Dhaka", "BD", false, new Date(), name, null, null, "student", "None", "9895458", "2019-20", null);
                                        databaseReference.child(authID).setValue(userForStudent);
                                    }
                                    Toast.makeText(SignUpActivity.this, "Registration Successfull!!!", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(SignUpActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginAll.class);
                startActivity(intent);
            }
        });
    }

}
