package com.coderbd.noticeboard;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.coderbd.noticeboard.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAll extends AppCompatActivity {
    ImageView btnSignUp;
    private EditText eEmail, ePass;
    private ImageButton btnLogin;
    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;
    public static final String ADMIN = "admin";
    public static final String TEACHER = "teacher";
    public static final String STUDENT = "student";
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_all);

        eEmail = (EditText) findViewById(R.id.editTextEmail);
        ePass = (EditText) findViewById(R.id.editTextPass);
        btnLogin = (ImageButton) findViewById(R.id.imageViewLogin);
        btnSignUp = (ImageView) findViewById(R.id.imageViewSignUp);
        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                User user = dataSnapshot.getValue(User.class);
                                String userType = user.getUserType();
                                switch (userType) {
                                    case ADMIN:
                                        Intent intent = new Intent(LoginAll.this, UserProfileActivity.class);
                                        startActivity(intent);
                                        break;
                                    case TEACHER:
                                        Intent intent2 = new Intent(LoginAll.this, TeacherProfileActivity.class);
                                        startActivity(intent2);
                                        break;
                                    case STUDENT:
                                        Intent intent3 = new Intent(LoginAll.this, StudentProfileActivity.class);
                                        startActivity(intent3);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } else { // User is signed out

                }
                    }
                } ;


                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final String email = eEmail.getText().toString().trim();
                        final String password = ePass.getText().toString().trim();

                        if (TextUtils.isEmpty(email)) {
                            Toast.makeText(LoginAll.this, "Enter Email Address ", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(password)) {
                            Toast.makeText(LoginAll.this, "Enter Password ", Toast.LENGTH_SHORT).show();
                            return;
                        }


                        firebaseAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(LoginAll.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                            if (user != null) {
                                                FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.exists()) {
                                                            User user = dataSnapshot.getValue(User.class);
                                                            String userType = user.getUserType();
                                                            switch (userType) {
                                                                case ADMIN:
                                                                    Intent intent = new Intent(LoginAll.this, UserProfileActivity.class);
                                                                    startActivity(intent);
                                                                    break;
                                                                case TEACHER:
                                                                    Intent intent2 = new Intent(LoginAll.this, TeacherNoticeListActivity.class);
                                                                    startActivity(intent2);
                                                                    break;
                                                                case STUDENT:
                                                                    Intent intent3 = new Intent(LoginAll.this, StudentNoticeListActivity.class);
                                                                    startActivity(intent3);
                                                                    break;
                                                                default:
                                                                    break;
                                                            }
                                                        }
                                                    }
                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });

                                            }
//                                            if (user != null) {
//                                                // Name, email address, and profile photo Url
//                                                String name = user.getDisplayName();
//                                                String email = user.getEmail();
//                                                //  Uri photoUrl = user.getPhotoUrl();
//
//                                                // Check if user's email is verified
//                                                boolean emailVerified = user.isEmailVerified();
//
//                                                // The user's ID, unique to the Firebase project. Do NOT use this value to
//                                                // authenticate with your backend server, if you have one. Use
//                                                // FirebaseUser.getIdToken() instead.
//                                                String uid = user.getUid();
//                                                System.out.println("User Details: " + "Name: " + name + " Email: " + email + " isActivate: " + emailVerified + " UID: " + uid);
//                                            }
                                            Intent intent = new Intent(LoginAll.this, MainActivity.class);
                                            startActivity(intent);
                                        }

                                    }
                                }).addOnFailureListener(LoginAll.this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginAll.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                });
                btnSignUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoginAll.this, SignUpActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }
