package com.coderbd.noticeboard;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.coderbd.noticeboard.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import java.util.Date;

public class SignUpActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static String downloadUrl="";
    private RadioButton rInstitute, rTeacher, rStudent;
    private EditText eInsName, eName, eEmail, ePass;
    private ImageView btnSignUp, btnLogin, emailBg, showImage, btnChooseImage;
    private RadioGroup eradioGroup;
    private Spinner spinnerDep;

    private ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    ////////////Image////////////////
    private Uri mImageUri;
    private StorageReference storageReference;
    //for preventing multiple image upload
    private StorageTask storageTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        rInstitute = (RadioButton) findViewById(R.id.radioInstitute);
        rTeacher = (RadioButton) findViewById(R.id.radioTeacher);
        rStudent = (RadioButton) findViewById(R.id.radioStudent);

        eInsName = (EditText) findViewById(R.id.editText2);
        eEmail = (EditText) findViewById(R.id.editText3);
        ePass = (EditText) findViewById(R.id.editText5);

        btnSignUp = (ImageView) findViewById(R.id.imageView33);
        btnLogin = (ImageView) findViewById(R.id.imageViewLogin);
        emailBg = (ImageView) findViewById(R.id.imageView25);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        spinnerDep = (Spinner) findViewById(R.id.spinner);
        final ImageView spinnerBgImage = (ImageView) findViewById(R.id.imageView27);
        eName = (EditText) findViewById(R.id.editText);

        //////image//////
        btnChooseImage = (ImageView) findViewById(R.id.imageView32);
        showImage = (ImageView) findViewById(R.id.imageView37);
        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storageTask != null && storageTask.isInProgress()) {
                    Toast.makeText(SignUpActivity.this, "Upload In Progress", Toast.LENGTH_SHORT).show();
                } else {
                    openFileChooser();
                }
            }
        });
        ////////////image end////////////

        eradioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        eradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rInstitute.isChecked()) {
                    eInsName.setHint("Enter Institute Name");
                    eName.setHint("Enter Institute Id");
                    spinnerBgImage.setVisibility(View.GONE);
                    spinnerDep.setVisibility(View.GONE);
                } else if (rTeacher.isChecked() || rStudent.isChecked()) {
                    eInsName.setHint("Enter Institute ID");
                    eName.setHint("Enter Name");
                    spinnerBgImage.setVisibility(View.VISIBLE);
                    spinnerDep.setVisibility(View.VISIBLE);
                }
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        storageReference = FirebaseStorage.getInstance().getReference("Users");



        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String insname = eInsName.getText().toString().trim();
                final String email = eEmail.getText().toString().trim();
                final String password = ePass.getText().toString().trim();
                final String department = spinnerDep.getSelectedItem().toString().trim();
                final String nameOfUser = eName.getText().toString().trim();
                if (TextUtils.isEmpty(insname)) {
                    if (rInstitute.isChecked()) {
                        Toast.makeText(SignUpActivity.this, "Enter Institute Name ", Toast.LENGTH_SHORT).show();
                    } else if (rTeacher.isChecked() || rStudent.isChecked()) {
                        Toast.makeText(SignUpActivity.this, "Enter Institute ID ", Toast.LENGTH_SHORT).show();
                    }

                }
                if (TextUtils.isEmpty(nameOfUser)) {
                    if (rInstitute.isChecked()) {
                        Toast.makeText(SignUpActivity.this, "Enter Institute ID ", Toast.LENGTH_SHORT).show();
                    } else if (rTeacher.isChecked() || rStudent.isChecked()) {
                        Toast.makeText(SignUpActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
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
                if (rStudent.isChecked() || rTeacher.isChecked()) {
                    if (TextUtils.isEmpty(department)) {
                        Toast.makeText(SignUpActivity.this, "Enter Department ", Toast.LENGTH_SHORT).show();
                    }
                }
                if(mImageUri == null){
                    Toast.makeText(SignUpActivity.this, "No Selected File", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);
                uploadFile();
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    String authID = firebaseAuth.getCurrentUser().getUid();

                                    if (rInstitute.isChecked()) {

                                        User userForInstitue = new User(authID, insname, downloadUrl, email, "0168600000", "100 Dhaka", "Dhaka", "Dhaka", "BD", true, new Date(), null, nameOfUser, "1980", "admin", null, null, null, null);
                                        databaseReference.child(authID).setValue(userForInstitue);
                                    } else if (rTeacher.isChecked()) {
                                        User userForTeacher = new User(authID, nameOfUser, downloadUrl, email, "0178600022", "10 Dhaka", "Dhaka", "Dhaka", "BD", false, new Date(), insname, null, null, "teacher", department, null, null, "Teacher");
                                        System.out.println(userForTeacher);
                                        databaseReference.child(authID).setValue(userForTeacher);
                                    } else if (rStudent.isChecked()) {
                                        User userForStudent = new User(authID, nameOfUser, downloadUrl, email, "0188600022", "10 Dhaka", "Dhaka", "Dhaka", "BD", false, new Date(), insname, null, null, "student", department, "9895458", "2019-20", null);
                                        databaseReference.child(authID).setValue(userForStudent);
                                    }
                                    Toast.makeText(SignUpActivity.this, "Registration Successfull!!!", Toast.LENGTH_LONG).show();
                                } else {
                                    System.out.println();
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

    //////////////////image////////////
    public void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, requestCode, intent);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && intent != null && intent.getData() != null) {
            mImageUri = intent.getData();
            Picasso.with(this).load(mImageUri).into(showImage);

        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
    private void uploadFile() {
        if (mImageUri != null) {
            final StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(mImageUri));
            storageTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                   progressBar.setVisibility(View.VISIBLE);
                                }
                            }, 500);

                            Toast.makeText(SignUpActivity.this, "Upload Successful!!", Toast.LENGTH_SHORT).show();
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                     downloadUrl = uri.toString();
//                                    Upload upload = new Upload(editTextFileName.getText().toString().trim(),downloadUrl);
//                                    String uploadId = databaseReference.push().getKey();
//                                    databaseReference.child(uploadId).setValue(upload);

                                }
                            });

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressBar.setProgress((int) progress);

                        }
                    });

        } else {
            Toast.makeText(this, "No Selected File", Toast.LENGTH_SHORT).show();
        }
    }
    ///////////////image end
}
