package com.coderbd.noticeboard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class UserProfileActivity extends AppCompatActivity {
private ImageButton btnSignOut;
private FirebaseAuth.AuthStateListener authStateListener;
private TextView name, details;

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        authStateListener=new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if(FirebaseAuth.getInstance() == null){
//                    btnSignOut.setVisibility(View.GONE);
//                    Intent i = new Intent(UserProfileActivity.this, LoginAll.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(i);
//                    finish();
//                }
//            }
//        };
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        btnSignOut = (ImageButton)findViewById(R.id.imageView30);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(UserProfileActivity.this,LoginAll.class);
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
                            case R.id.navigation_feed:
                                Intent intentnotice=new Intent(UserProfileActivity.this, NoticeListActivity.class);
                                startActivity(intentnotice);
                                break;
                            case R.id.navigation_user_list:
                                Intent intent=new Intent(UserProfileActivity.this,UserListActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.navigation_add_notice:
                                Intent intentNotice=new Intent(UserProfileActivity.this,NoticeActivity.class);
                                startActivity(intentNotice);
                                break;

                            case R.id.navigation_permission:

                                break;
                            case R.id.navigation_profile:
                                Intent intentProfile=new Intent(UserProfileActivity.this,UserProfileActivity.class);
                                startActivity(intentProfile);
                                break;
                        }
                        return false;
                    }
                });


    }
}
