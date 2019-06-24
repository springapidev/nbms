package com.coderbd.noticeboard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.coderbd.noticeboard.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class UserProfileActivity extends AppCompatActivity {
private ImageView btnSignOut;
private FirebaseAuth.AuthStateListener authStateListener;
private TextView insName,estYear, regiCode,userType,email,mobile,acCreateDate,address;

private ImageView logo;

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
        btnSignOut = (ImageView)findViewById(R.id.imageView43);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(UserProfileActivity.this,LoginAll.class);
                startActivity(intent);
            }
        });
        insName=(TextView)findViewById(R.id.textView);
        regiCode=(TextView)findViewById(R.id.textView2);
        estYear=(TextView)findViewById(R.id.textView3);
        userType=(TextView)findViewById(R.id.textView4);
        email=(TextView)findViewById(R.id.textView5);
        mobile=(TextView)findViewById(R.id.textView6);
        acCreateDate=(TextView)findViewById(R.id.textView7);
        address=(TextView)findViewById(R.id.textView8);
        logo=(ImageView) findViewById(R.id.imageView2);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null ){

            FirebaseDatabase.getInstance().getReference().child("Users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        User user = dataSnapshot.getValue(User.class);

                        insName.setText(user.getName().toString());
                        String insregiCode = "Regi. Code: "+user.getRegiCode().toString();
                        regiCode.setText(insregiCode.toString());
                        String esYear ="Est. Year: "+user.getEstablishedYear().toString();
                        estYear.setText(esYear.toString());
                        userType.setText(user.getUserType().toUpperCase().toString());
                        email.setText(user.getEmail().toString());
                        mobile.setText(user.getMobile().toString());
                        acCreateDate.setText(String.valueOf(user.getCreateDate().toString()));
                        StringBuilder sb=new StringBuilder();
                        sb.append(user.getAddress()+", ");
                        sb.append(user.getDistrict()+"\n");
                        sb.append(user.getDivision()+", ");
                        sb.append(user.getCountry());
                        address.setText(sb.toString());
                        Picasso.with(UserProfileActivity.this).load(user.getLogoOrPhoto()).into(logo);
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
