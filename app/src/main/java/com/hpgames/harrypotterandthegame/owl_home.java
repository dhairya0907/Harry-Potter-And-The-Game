package com.hpgames.harrypotterandthegame;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;


public class owl_home extends Fragment {

    Button y1,y2,y3,y4,y5,y6,y7;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;
    String userId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.owl_home,container,false);

        y1=rootView.findViewById(R.id.obyearone);
        y2=rootView.findViewById(R.id.obyeartwo);
        y3=rootView.findViewById(R.id.obyearthree);
        y4=rootView.findViewById(R.id.obyearfour);
        y5=rootView.findViewById(R.id.obyearfive);
        y6=rootView.findViewById(R.id.obyearsix);
        y7=rootView.findViewById(R.id.obyearseven);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseInstance.getReference("users");
        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();
        if(owl.isRunning==true){
            owl.time.cancel();
        }

        y1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String year=dataSnapshot.child(userId).child("year").getValue(String.class);
                        String cyear=String.valueOf(year);
                        if(cyear.equalsIgnoreCase("one")){
                            global.cyear="year one";
                            global.f=1;
                            global.j=0;
                            global.flag=0;
                            global.score=0;
                            global.milisec = 180000;
                            owl.question(owl.a);
                            owl.tquestion.setText("5");
                            for (int a = 0; a <= 49; a++)

                            {
                                global.number.add(a);
                            }
                            Collections.shuffle(global.number);
                            maingame.mViewPager.setCurrentItem(5);
                            maingame.mTitle.setText("YEAR ONE");
                            owl.time.start();
                        }
                        else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("You Have Already Passed Year One.");
                            builder.setMessage("Try Year Second");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
        y2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String year=dataSnapshot.child(userId).child("year").getValue(String.class);
                        String passedyear=dataSnapshot.child(userId).child("yearpassed").getValue(String.class);
                        String cyear=String.valueOf(year);
                        global.f=1;
                        global.j=0;
                        global.flag=0;
                        global.score=0;
                        global.milisec = 180000;
                        owl.tquestion.setText("5");
                        owl.question(owl.a);
                        if(cyear.equalsIgnoreCase("two")){
                            global.cyear="year two";
                            for (int a = 0; a <= 49; a++)

                            {
                                global.number.add(a);
                            }
                            Collections.shuffle(global.number);
                            maingame.mViewPager.setCurrentItem(5);
                            maingame.mTitle.setText("YEAR TWO");
                            owl.time.start();
                        }
                        else if(passedyear.length()<3){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("First Pass Year One To Play Year Two");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("You Have Already Passed Year Two");
                            builder.setMessage("Try Year Third");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        y3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String year=dataSnapshot.child(userId).child("year").getValue(String.class);
                        String cyear=String.valueOf(year);
                        String passedyear=dataSnapshot.child(userId).child("yearpassed").getValue(String.class);
                        if(cyear.equalsIgnoreCase("three")){
                            global.cyear="year three";
                            global.f=1;
                            global.j=0;
                            global.flag=0;
                            global.score=0;
                            global.milisec = 216000;
                            owl.question(owl.a);
                            owl.tquestion.setText("6");
                            for (int a = 0; a <= 59; a++)

                            {
                                global.number.add(a);
                            }
                            Collections.shuffle(global.number);
                            maingame.mViewPager.setCurrentItem(5);
                            maingame.mTitle.setText("YEAR THREE");
                            owl.time.start();
                        }
                        else if(passedyear.length()<6){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("First Pass Year Two To Play Year Three");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("You Have Already Passed Year Three");
                            builder.setMessage("Try Year Fourth");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        y4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String year=dataSnapshot.child(userId).child("year").getValue(String.class);
                        String cyear=String.valueOf(year);
                        String passedyear=dataSnapshot.child(userId).child("yearpassed").getValue(String.class);
                        if(cyear.equalsIgnoreCase("four")){
                            global.cyear="year four";
                            global.f=1;
                            global.j=0;
                            global.flag=0;
                            global.score=0;
                            global.milisec = 216000;
                            owl.question(owl.a);
                            owl.tquestion.setText("6");
                            for (int a = 0; a <= 59; a++)

                            {
                                global.number.add(a);
                            }
                            Collections.shuffle(global.number);
                            maingame.mViewPager.setCurrentItem(5);
                            maingame.mTitle.setText("YEAR FOUR");
                            if(owl.isRunning==true){
                                owl.time.cancel();
                            }
                            owl.time.start();
                        }
                        else if(passedyear.length()<11){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("First Pass Year Three To Play Year Four");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("You Have Already Passed Year Fourth");
                            builder.setMessage("Try Year Five");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        y5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String year=dataSnapshot.child(userId).child("year").getValue(String.class);
                        String cyear=String.valueOf(year);
                        String passedyear=dataSnapshot.child(userId).child("yearpassed").getValue(String.class);
                        if(cyear.equalsIgnoreCase("five")){
                            global.cyear="year five";
                            global.f=1;
                            global.j=0;
                            global.score=0;
                            global.milisec = 252000;
                            owl.question(owl.a);
                            owl.tquestion.setText("7");
                            for (int a = 0; a <= 69; a++)

                            {
                                global.number.add(a);
                            }
                            Collections.shuffle(global.number);
                            maingame.mViewPager.setCurrentItem(5);
                            maingame.mTitle.setText("YEAR FIVE");
                            owl.time.start();
                        }
                        else if(passedyear.length()<15){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("First Pass Year Four To Play Year Five");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("You Have Already Passerd Year Five");
                            builder.setMessage("Try Year Six");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        });
        y6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String year=dataSnapshot.child(userId).child("year").getValue(String.class);
                        String cyear=String.valueOf(year);
                        String passedyear=dataSnapshot.child(userId).child("yearpassed").getValue(String.class);
                        if(cyear.equalsIgnoreCase("six")){
                            global.cyear="year six";
                            global.f=1;
                            global.j=0;
                            global.score=0;
                            global.milisec = 252000;
                            owl.question(owl.a);
                            owl.tquestion.setText("7");
                            for (int a = 0; a <= 49; a++)

                            {
                                global.number.add(a);
                            }
                            Collections.shuffle(global.number);
                            maingame.mViewPager.setCurrentItem(5);
                            maingame.mTitle.setText("YEAR SIX");
                            owl.time.start();
                        }
                        else if(passedyear.length()<19){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("First Pass Year Five To Play Year Six");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("You Have Already Passed Year Six");
                            builder.setMessage("Try Year Seven");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        y7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String year=dataSnapshot.child(userId).child("year").getValue(String.class);
                        String cyear=String.valueOf(year);
                        String passedyear=dataSnapshot.child(userId).child("yearpassed").getValue(String.class);
                        if(cyear.equalsIgnoreCase("seven")){
                            global.cyear="year seven";
                            global.f=1;
                            global.j=0;
                            global.score=0;
                            global.milisec = 360000;
                            owl.question(owl.a);
                            owl.tquestion.setText("10");
                            for (int a = 0; a <= 99; a++)

                            {
                                global.number.add(a);
                            }
                            Collections.shuffle(global.number);
                            maingame.mViewPager.setCurrentItem(5);
                            maingame.mTitle.setText("YEAR SEVEN");
                            owl.time.start();
                        }
                        else if(passedyear.length()<22){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("First Pass Year Six To Play Year Seven");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("You Have Already Passed Year Seven");
                            builder.setMessage("Click on Reset Link In Your Dormitory to Play from year One Again!!\n\nOnly Your Year will be reset to Year one again\n\nAnd Play With All new " +
                                    "Sets Questions");
                            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            dialog.setCancelable(false);
                            dialog.setCanceledOnTouchOutside(false);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        return rootView;
    }
}