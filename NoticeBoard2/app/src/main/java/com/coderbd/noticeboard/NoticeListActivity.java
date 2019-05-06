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
import com.coderbd.noticeboard.adapter.UserAdapter;
import com.coderbd.noticeboard.model.Notice;
import com.coderbd.noticeboard.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NoticeListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    NoticeAdapter adapter;
    List<Notice> noticeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_feed:
                                Intent intentnotice=new Intent(NoticeListActivity.this, NoticeListActivity.class);
                                startActivity(intentnotice);
                                break;
                            case R.id.navigation_user_list:
                                Intent intent=new Intent(NoticeListActivity.this, NoticeListActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.navigation_add_notice:
                                Intent intentNotice=new Intent(NoticeListActivity.this,NoticeActivity.class);
                                startActivity(intentNotice);
                                break;

                            case R.id.navigation_permission:

                                break;
                            case R.id.navigation_profile:
                                Intent intentProfile=new Intent(NoticeListActivity.this,UserProfileActivity.class);
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

        DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference("Notices");

        dbUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Notice n = snapshot.getValue(Notice.class);
                        noticeList.add(n);
                    }

                    adapter = new NoticeAdapter(NoticeListActivity.this, noticeList);
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
