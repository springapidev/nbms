package com.coderbd.noticeboard;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

private FirebaseAuth firebaseAuth;
    private ViewPager viewPager;

    NavigationView navigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_feed:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_user_list:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_add_notice:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_permission:
                    viewPager.setCurrentItem(3);
                    return true;
                case R.id.navigation_profile:
                    viewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the activity toolbar
        getSupportActionBar().hide();

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_feed);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_user_list);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_add_notice);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_permission);
                        break;
                    case 4:
                        navigation.setSelectedItemId(R.id.navigation_profile);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
/////////////////checked user is logged in
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user != null){
            System.out.println("Already Logged In"+user.getEmail());
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
        }else{
            System.out.println("Not Logged In"+user.getEmail());
            Toast.makeText(this, "Not Logged In", Toast.LENGTH_SHORT).show();
        }

    }

    private void setupViewPager(ViewPager viewPager) {
        BottomNavPagerAdapter adapter = new BottomNavPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragment());
        adapter.addFragment(new UsersFragment());
        adapter.addFragment(new PublishFragment());
        adapter.addFragment(new PermissionFragment());
        adapter.addFragment(new ProfileFragment());
        viewPager.setAdapter(adapter);
    }

}
