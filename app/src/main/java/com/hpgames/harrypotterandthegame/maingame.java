package com.hpgames.harrypotterandthegame;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class maingame extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static ViewPager mViewPager;
    public static SectionsPagerAdapter mSectionsPagerAdapter;


    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;
    String userId;
   public static TextView mTitle;
   public static Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maingame);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.maingameview);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        getSupportActionBar().setTitle("");
        mTitle.setText("  Harry Potter And The Game");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_content);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseInstance.getReference("users");

        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ImageView badge = (ImageView) findViewById(R.id.housebadge);
                TextView uname = (TextView) findViewById(R.id.usergamename);
                TextView quality = (TextView) findViewById(R.id.housequality);
                String gname = dataSnapshot.child(userId).child("gamename").getValue(String.class);
                String hname = dataSnapshot.child(userId).child("house").getValue(String.class);
                global.gamename = String.valueOf(gname);
                global.housename = String.valueOf(hname);
                uname.setText(""+global.gamename);
                if (global.housename.equalsIgnoreCase("gryffindor")) {
                    badge.setImageResource(R.drawable.house_badge_gryffindor);
                    quality.setText("Courageous, Brave and Determinant.");
                } else if (global.housename.equalsIgnoreCase("hufflepuff")) {
                    badge.setImageResource(R.drawable.house_badge_hufflepuff);
                    quality.setText("Hard Working, Patient and Loyal.");
                } else if (global.housename.equalsIgnoreCase("ravenclaw")) {
                    badge.setImageResource(R.drawable.house_badge_ravenclaw);
                    quality.setText("Intelligent, Wise and Creative.");
                } else if (global.housename.equalsIgnoreCase("slytherin")) {
                    badge.setImageResource(R.drawable.house_badge_slytherin);
                    quality.setText("Cunning, Leader and Resourceful");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    home h=new home();
                    return h;
                case 1:
                    dormitory d=new dormitory();
                    return d;
                case 2:
                    learn_home lh=new learn_home();
                    return lh;
                case 3:
                    learn l=new learn();
                    return l;
                case 4:
                    owl_home oh=new owl_home();
                    return oh;
                case 5:
                    owl o=new owl();
                    return o;
                case 6:
                    thegreathall gh=new thegreathall();
                    return gh;
                case 7:
                    housecommonroom hcr=new housecommonroom();
                    return hcr;
                case 8:
                    leaderboard le=new leaderboard();
                    return le;
                case 9:
                    leaderschool ls=new leaderschool();
                    return ls;
                case 10:
                    leaderhouse lhouse=new leaderhouse();
                    return lhouse;
                case 11:
                case 12:
                    owltous owl=new owltous();
                    return owl;
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            return 13;
        }
        }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_content);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(mViewPager.getCurrentItem()==0){
            mViewPager.setCurrentItem(0);
        }
        else if(mViewPager.getCurrentItem()==1){
            mViewPager.setCurrentItem(1);
        }
        else if(mViewPager.getCurrentItem()==2){
            mViewPager.setCurrentItem(2);
        }
        else if(mViewPager.getCurrentItem()==3){
            mViewPager.setCurrentItem(3);
        }
        else if(mViewPager.getCurrentItem()==4){
            mViewPager.setCurrentItem(4);
        }
        else if(mViewPager.getCurrentItem()==5){
            mViewPager.setCurrentItem(5);
        }

        else if(mViewPager.getCurrentItem()==6){
            mViewPager.setCurrentItem(6);
        }
        else if(mViewPager.getCurrentItem()==7){
            mViewPager.setCurrentItem(7);
        }
        else if(mViewPager.getCurrentItem()==8){
            mViewPager.setCurrentItem(9);
        }
        else if(mViewPager.getCurrentItem()==10){
            mViewPager.setCurrentItem(10);
        }
        else if(mViewPager.getCurrentItem()==11){
            mViewPager.setCurrentItem(11);
        }
        else if(mViewPager.getCurrentItem()==12){
            mViewPager.setCurrentItem(12);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maingame, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.nav_home){
            mViewPager.setCurrentItem(1);
            mTitle.setText("Your Dormitory");
            if(owl.isRunning==true) {
                owl.time.cancel();
            }

        }
        else if (id == R.id.nav_learn) {
            mViewPager.setCurrentItem(2);
            mTitle.setText("Learn");
            if(owl.isRunning==true) {
                owl.time.cancel();
            }

        } else if (id == R.id.nav_owl) {
            mViewPager.setCurrentItem(4);
            global.f=0;
            mTitle.setText("O.W.L.");
            if(owl.isRunning==true) {
                owl.time.cancel();
            }

        } else if (id == R.id.nav_leaderboard) {
            mViewPager.setCurrentItem(8);
            mTitle.setText("Leaderboard");
            if(owl.isRunning==true) {
                owl.time.cancel();
            }

        } else if (id == R.id.nav_contactus) {
            mViewPager.setCurrentItem(12);
            mTitle.setText("OWL US");
            if(owl.isRunning==true) {
                owl.time.cancel();
            }

        } else if (id == R.id.nav_hall) {
            mViewPager.setCurrentItem(6);
            mTitle.setText("The Great Hall");
            if(owl.isRunning==true) {
                owl.time.cancel();
            }

        } else if (id == R.id.nav_room) {
            mViewPager.setCurrentItem(7);
            if(owl.isRunning==true) {
                owl.time.cancel();
            }
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String house=dataSnapshot.child(userId).child("house").getValue(String.class);
                    if(house.equalsIgnoreCase("gryffindor")){
                        maingame.mTitle.setText("Gryffindor Common Room");
                        housecommonroom.rootView.setBackgroundResource(R.drawable.gryffindorcommonroom);
                    }
                    if(house.equalsIgnoreCase("hufflepuff")){
                        maingame.mTitle.setText("Hufflepuff Common Room");
                        housecommonroom.rootView.setBackgroundResource(R.drawable.hufflepuffcommonroom);
                    }
                    if(house.equalsIgnoreCase("ravenclaw")){
                        maingame.mTitle.setText("Ravenclaw Common Room");
                        housecommonroom.rootView.setBackgroundResource(R.drawable.ravenclawcommonroom);
                    }
                    if(house.equalsIgnoreCase("slytherin")){
                        maingame.mTitle.setText("Slytherin Common Room");
                        housecommonroom.rootView.setBackgroundResource(R.drawable.slytherincommonroom);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if (id == R.id.nav_signout) {
            startActivity(new Intent(maingame.this, login_register.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_content);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void reset(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(maingame.this);
        builder.setTitle("Reset");
        builder.setMessage("Your Current Year Will be Reset To Year One.");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mDatabase.child(userId).child("year").setValue("One");
                mDatabase.child(userId).child("yearpassed").setValue("");

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
    }
}

