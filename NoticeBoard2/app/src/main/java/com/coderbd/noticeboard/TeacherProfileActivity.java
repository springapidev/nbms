package com.coderbd.noticeboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.coderbd.noticeboard.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherProfileActivity extends AppCompatActivity {
private ImageButton btnSignOut;
private FirebaseAuth.AuthStateListener authStateListener;
private TextView name, details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
        btnSignOut = (ImageButton)findViewById(R.id.imageView30);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(TeacherProfileActivity.this,LoginAll.class);
                startActivity(intent);
            }
        });
        name=(TextView)findViewById(R.id.textView20);
        details=(TextView)findViewById(R.id.textView17);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null ){

            FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        User user = dataSnapshot.getValue(User.class);
                        String userType = user.getUserType();
                        name.setText(user.getName());
                        StringBuilder sb=new StringBuilder();
                        sb.append("User Type: "+user.getUserType()+"\n");
                        sb.append("Name: "+user.getName()+"\n");
                        sb.append("Email: "+user.getEmail()+"\n");
                        sb.append("Department: "+user.getDepartment()+"\n");
                        details.setText(sb.toString());

                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_feed_student:
                                Intent intentnotice=new Intent(TeacherProfileActivity.this, StudentNoticeListActivity.class);
                                startActivity(intentnotice);
                                break;
                            case R.id.navigation_add_notice_teacher:
                                Intent intentNotice=new Intent(TeacherProfileActivity.this,TeacherNoticeActivity.class);
                                startActivity(intentNotice);
                                break;
                            case R.id.navigation_profile_student:
                                Intent intentProfile=new Intent(TeacherProfileActivity.this, TeacherProfileActivity.class);
                                startActivity(intentProfile);
                                break;
                        }
                        return false;
                    }
                });


    }
}
