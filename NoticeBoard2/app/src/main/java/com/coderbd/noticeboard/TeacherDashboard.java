package com.coderbd.noticeboard;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class TeacherDashboard extends AppCompatActivity {


    private ViewPager viewPager;

    NavigationView navigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_feed_teacher:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_add_notice_teacher:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_profile_teacher:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        // Hide the activity toolbar
      //  getSupportActionBar().hide();

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
                        navigation.setSelectedItemId(R.id.navigation_feed_teacher);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_add_notice_teacher);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_profile_teacher);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void setupViewPager(ViewPager viewPager) {
        BottomNavPagerAdapter adapter = new BottomNavPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FirstFragmentTeacher());
        adapter.addFragment(new PublishFragmentTeacher());
        adapter.addFragment(new ProfileFragmentTeacher());
        viewPager.setAdapter(adapter);
    }

}
