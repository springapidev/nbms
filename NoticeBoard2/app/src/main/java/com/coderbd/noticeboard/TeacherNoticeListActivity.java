package com.coderbd.noticeboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.coderbd.noticeboard.adapter.NoticeAdapter;
import com.coderbd.noticeboard.model.Notice;
import com.coderbd.noticeboard.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeacherNoticeListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NoticeAdapter adapter;
    List<Notice> noticeList;
    List<Notice> noticeListByDepAndIns;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_notice_list);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_feed_teacher:
                                Intent intentnotice=new Intent(TeacherNoticeListActivity.this, TeacherNoticeListActivity.class);
                                startActivity(intentnotice);
                                break;
                            case R.id.navigation_add_notice_teacher:
                                Intent intentNotice=new Intent(TeacherNoticeListActivity.this,TeacherNoticeActivity.class);
                                startActivity(intentNotice);
                                break;
                            case R.id.navigation_profile_teacher:
                                Intent intentProfile=new Intent(TeacherNoticeListActivity.this,TeacherProfileActivity.class);
                                startActivity(intentProfile);
                                break;
                        }
                        return false;
                    }
                });



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        noticeList = new ArrayList<>();
        noticeListByDepAndIns = new ArrayList<>();


        DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference("Notices");

        dbUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Notice n = snapshot.getValue(Notice.class);
                        noticeList.add(n);
                    }


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null ){

            FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        User user = dataSnapshot.getValue(User.class);
                        for(Notice notice : noticeList){
                            if(user.getInstituteId().equalsIgnoreCase(notice.getInstituteId()) && ( user.getDepartment().equalsIgnoreCase(notice.getDepartment()) || notice.getDepartment().equalsIgnoreCase("All"))){
                                noticeListByDepAndIns.add(notice);
                            }
                        }
                        adapter = new NoticeAdapter(TeacherNoticeListActivity.this, noticeListByDepAndIns);
                        recyclerView.setAdapter(adapter);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
}
