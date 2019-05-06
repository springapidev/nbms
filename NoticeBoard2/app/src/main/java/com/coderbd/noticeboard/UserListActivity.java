package com.coderbd.noticeboard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.coderbd.noticeboard.adapter.UserAdapter;
import com.coderbd.noticeboard.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserAdapter adapter;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_feed:
                                Intent intentnotice=new Intent(UserListActivity.this, NoticeListActivity.class);
                                startActivity(intentnotice);
                                break;
                            case R.id.navigation_user_list:
                                Intent intent=new Intent(UserListActivity.this,UserListActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.navigation_add_notice:
                                Intent intentNotice=new Intent(UserListActivity.this,NoticeActivity.class);
                                startActivity(intentNotice);
                                break;

                            case R.id.navigation_permission:

                                break;
                            case R.id.navigation_profile:
                                Intent intentProfile=new Intent(UserListActivity.this,UserProfileActivity.class);
                                startActivity(intentProfile);
                                break;
                        }
                        return false;
                    }
                });



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        userList = new ArrayList<>();

        DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference("Users");

        dbUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    for(DataSnapshot productSnapshot : dataSnapshot.getChildren()){
                        User u = productSnapshot.getValue(User.class);
                        userList.add(u);
                    }

                    adapter = new UserAdapter(UserListActivity.this, userList);
                    recyclerView.setAdapter(adapter);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
