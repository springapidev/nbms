package com.coderbd.noticeboard;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.coderbd.noticeboard.model.Institute;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginSignup extends AppCompatActivity {

    EditText polytechnicName, email, mobile;
    ImageView signUp;
    DatabaseReference databaseInstitute;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        databaseInstitute = FirebaseDatabase.getInstance().getReference("institutes");


        polytechnicName = (EditText) findViewById(R.id.editText5);
        email = (EditText) findViewById(R.id.editText6);
        mobile = (EditText) findViewById(R.id.editText7);
        signUp = (ImageView)this.findViewById(R.id.imageView35);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInstitute();
            }
        });


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        ///FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /// fab.setOnClickListener(new View.OnClickListener() {
        ///   @Override
        ///  public void onClick(View view) {
        ///  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        ///  .setAction("Action", null).show();
        /// }
        /// });

    }

    private void addInstitute() {
        String instituteName = polytechnicName.getText().toString().trim();
        String emailID = email.getText().toString().trim();
        String mobileNo = mobile.getText().toString().trim();
        if (TextUtils.isEmpty(instituteName)) {
            Toast.makeText(this, "Enter Institute Name", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(emailID)) {
            Toast.makeText(this, "Enter Email Address", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(mobileNo)) {
            Toast.makeText(this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
        } else {
            String id= databaseInstitute.push().getKey();
          //  Institute institute=new Institute(id, instituteName, emailID, mobileNo, "578700", "1980", "Dhaka", "Dhaka", "Dhaka", "BD");
         //   databaseInstitute.child(id).setValue(institute);
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    Polytechnic tab1 = new Polytechnic();
                    return tab1;

                case 1:
                    Teacher tab2 = new Teacher();
                    return tab2;

                case 2:
                    Student tab3 = new Student();
                    return tab3;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

}
