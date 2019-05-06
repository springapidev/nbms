package com.coderbd.noticeboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.coderbd.noticeboard.model.Notice;
import com.coderbd.noticeboard.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class TeacherNoticeActivity extends AppCompatActivity {
    private EditText etitle, edetails;
    private Spinner spinner;
    private ImageButton btnDraft, btnPublish;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_notice);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        etitle = (EditText) findViewById(R.id.editText);
        edetails = (EditText) findViewById(R.id.editText3);
        spinner = (Spinner) findViewById(R.id.editText2);
        btnDraft = (ImageButton) findViewById(R.id.imageView18);
        btnPublish = (ImageButton) findViewById(R.id.imageView19);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Notices");


        btnPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String title = etitle.getText().toString().trim();
                final String details = edetails.getText().toString().trim();
              //  final String dep = spinner.getSelectedItem().toString().trim();
                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(TeacherNoticeActivity.this, "Enter Title", Toast.LENGTH_SHORT).show();
                }
//                if (TextUtils.isEmpty(dep)) {
//                    Toast.makeText(TeacherNoticeActivity.this, "Enter Department", Toast.LENGTH_SHORT).show();
//                }

                if (TextUtils.isEmpty(details)) {
                    Toast.makeText(TeacherNoticeActivity.this, "Enter Details", Toast.LENGTH_SHORT).show();
                }

                final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {

                    FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                User user = dataSnapshot.getValue(User.class);
                                String id = databaseReference.push().getKey();
                                String insID = null;
                                if (user.getUserType().equalsIgnoreCase("admin")) {
                                    insID = user.getRegiCode();
                                } else {
                                    insID = user.getInstituteId();
                                }
                                Notice notice = new Notice(id, title, details, "No Pdf", user.getDepartment(), new Date(), new Date(), firebaseUser.getUid(), firebaseUser.getUid(), insID, "draft");
                                databaseReference.child(id).setValue(notice);
                                Toast.makeText(TeacherNoticeActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } else {
                    Toast.makeText(TeacherNoticeActivity.this, "Logged In First", Toast.LENGTH_SHORT).show();
                }
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_feed_teacher:
                                Intent intentnotice=new Intent(TeacherNoticeActivity.this, TeacherNoticeListActivity.class);
                                startActivity(intentnotice);
                                break;
                            case R.id.navigation_add_notice_teacher:
                                Intent intentNotice=new Intent(TeacherNoticeActivity.this,TeacherNoticeActivity.class);
                                startActivity(intentNotice);
                                break;
                            case R.id.navigation_profile_teacher:
                                Intent intentProfile=new Intent(TeacherNoticeActivity.this,StudentProfileActivity.class);
                                startActivity(intentProfile);
                                break;
                        }
                        return false;
                    }
                });

    }
}
