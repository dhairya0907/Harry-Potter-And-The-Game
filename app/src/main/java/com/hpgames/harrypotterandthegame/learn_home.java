package com.hpgames.harrypotterandthegame;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class learn_home extends Fragment {

    Button y1,y2,y3,y4,y5,y6,y7,dyk;
    private com.github.clans.fab.FloatingActionButton f1;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;
    String userId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.learn_home,container,false);

        y1=rootView.findViewById(R.id.byearone);
        y2=rootView.findViewById(R.id.byeartwo);
        y3=rootView.findViewById(R.id.byearthree);
        y4=rootView.findViewById(R.id.byearfour);
        y5=rootView.findViewById(R.id.byearfive);
        y6=rootView.findViewById(R.id.byearsix);
        y7=rootView.findViewById(R.id.byearseven);
        dyk=rootView.findViewById(R.id.bclicktoknow);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseInstance.getReference("users");
        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();

                y1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String passedyear=dataSnapshot.child(userId).child("yearpassed").getValue(String.class);
                                String year=dataSnapshot.child(userId).child("year").getValue(String.class);
                                String cyear=String.valueOf(year);
                                if(cyear.equalsIgnoreCase("one")||passedyear.length()>=3){
                                    learn.refresh();
                                    global.cyear="yearone";
                                    global.y=0;
                                    maingame.mViewPager.setCurrentItem(3);
                                    Toast.makeText(getContext(), "Swipe", Toast.LENGTH_SHORT).show();
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
                                String passedyear=dataSnapshot.child(userId).child("yearpassed").getValue(String.class);
                                String year=dataSnapshot.child(userId).child("year").getValue(String.class);
                                String cyear=String.valueOf(year);
                                if(cyear.equalsIgnoreCase("two")||passedyear.length()>=6){
                                    learn.refresh();
                                    global.cyear="yeartwo";
                                    global.y=0;
                                    maingame.mViewPager.setCurrentItem(3);
                                    Toast.makeText(getContext(), "Swipe", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("First Pass Year One To Know About Year Two");
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
                                if(cyear.equalsIgnoreCase("three")||passedyear.length()>=11){
                                    learn.refresh();
                                    global.y=0;
                                    global.cyear="yearthree";
                                    maingame.mViewPager.setCurrentItem(3);
                                    Toast.makeText(getContext(), "Swipe", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("First Pass Year Two To Know About Year Three");
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
                                if(cyear.equalsIgnoreCase("four")||passedyear.length()>=15){
                                    learn.refresh();
                                    global.y=0;
                                    global.cyear="yearfour";
                                    maingame.mViewPager.setCurrentItem(3);
                                    Toast.makeText(getContext(), "Swipe", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("First Pass Year Three To Know About Year Four");
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
                                if(cyear.equalsIgnoreCase("five")||passedyear.length()>=19){
                                    learn.refresh();
                                    global.y=0;
                                    global.cyear="yearfive";
                                    maingame.mViewPager.setCurrentItem(3);
                                    Toast.makeText(getContext(), "Swipe", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("First Pass Year Four To Know About Year Five");
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
                                if(cyear.equalsIgnoreCase("six")||passedyear.length()>=22){
                                    learn.refresh();
                                    global.y=0;
                                    global.cyear="yearsix";
                                    maingame.mViewPager.setCurrentItem(3);
                                    Toast.makeText(getContext(), "Swipe", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("First Pass Year Five To Know About Year Six");
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
                                if(cyear.equalsIgnoreCase("seven")||passedyear.length()>=27){
                                    learn.refresh();
                                    global.y=0;
                                    global.cyear="yearseven";
                                    maingame.mViewPager.setCurrentItem(3);
                                    Toast.makeText(getContext(), "Swipe", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setTitle("First Pass Year Six To Know About Year Seven");
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
                dyk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String year=dataSnapshot.child(userId).child("year").getValue(String.class);
                                String cyear=String.valueOf(year);
                                if(cyear.equalsIgnoreCase("one")){
                                    global.d=1;
                                }
                                else if(cyear.equalsIgnoreCase("two")){
                                    global.d=6;
                                }
                                else if(cyear.equalsIgnoreCase("three")){
                                    global.d=11;
                                }
                                else if(cyear.equalsIgnoreCase("four")){
                                    global.d=16;
                                }
                                else if(cyear.equalsIgnoreCase("five")){
                                    global.d=21;
                                }
                                else if(cyear.equalsIgnoreCase("six")){
                                    global.d=26;
                                }
                                else if(cyear.equalsIgnoreCase("seven")){
                                    global.d=31;
                                }
                                learn.refresh();
                                global.count=global.d+6;
                                global.cyear="didyouknow";
                                maingame.mViewPager.setCurrentItem(3);
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