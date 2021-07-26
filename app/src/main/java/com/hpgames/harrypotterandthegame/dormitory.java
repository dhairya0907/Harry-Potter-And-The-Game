package com.hpgames.harrypotterandthegame;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class dormitory extends Fragment {

    private com.github.clans.fab.FloatingActionButton f1;
    private DatabaseReference mDatabase,mFirebaseDatabase,total;
    private FirebaseDatabase mFirebaseInstance1;
    private FirebaseAuth mAuth;
    String userId;
    int count=0;
    ArrayList<String> hname = new ArrayList<String>();
    ArrayList<Integer> hscore = new ArrayList<Integer>();
    ArrayList<Integer> hurank = new ArrayList<Integer>();
    public static ArrayList<String> hrname = new ArrayList<String>();
    ArrayList<Integer> hrscore = new ArrayList<Integer>();
    ArrayList<Integer> hrurank = new ArrayList<Integer>();
    Query rankleader1;
    TextView setrank;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.dormitory,container,false);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance1 = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseInstance1.getReference("users");
        mFirebaseDatabase = mFirebaseInstance1.getReference("rank");
        total=mFirebaseInstance1.getReference("total");
        setrank=rootView.findViewById(R.id.uhrank);

        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ImageView iprofile = rootView.findViewById(R.id.profile_image);
                TextView uname=rootView.findViewById(R.id.uname);
                TextView ugname = rootView.findViewById(R.id.ugname);
                TextView uscore = rootView.findViewById(R.id.uscore);
                TextView uyear=rootView.findViewById(R.id.yearno);
                String hname1 = dataSnapshot.child(userId).child("house").getValue(String.class);
                String gender=dataSnapshot.child(userId).child("gender").getValue(String.class);
                String fname = dataSnapshot.child(userId).child("fname").getValue(String.class);
                String lname = dataSnapshot.child(userId).child("lname").getValue(String.class);
                String gname = dataSnapshot.child(userId).child("gamename").getValue(String.class);
                int score = dataSnapshot.child(userId).child("score").getValue(Integer.class);
                String year = dataSnapshot.child(userId).child("year").getValue(String.class);
                global.housename = String.valueOf(hname1);
                global.gender=String.valueOf(gender);
                global.fname = String.valueOf(fname);
                global.lname = String.valueOf(lname);
                global.gamename = String.valueOf(gname);
                global.score = Integer.valueOf(score);
                global.year= String.valueOf(year);
                global.fname = global.fname.substring(0, 1).toUpperCase() + global.fname.substring(1).toLowerCase();
                global.lname = global.lname.substring(0, 1).toUpperCase() + global.lname.substring(1).toLowerCase();
                global.year = global.year.substring(0, 1).toUpperCase() + global.year.substring(1).toLowerCase();
                if(global.gender.equalsIgnoreCase("Male")) {
                    uname.setText("Mr. "+global.fname+" "+global.lname);
                }
                if(global.gender.equalsIgnoreCase("Female")) {
                    uname.setText("Ms."+global.fname+" "+global.lname);
                }
                ugname.setText(global.gamename);
                uscore.setText(""+global.score);
                uyear.setText(global.year);
                if (global.housename.equalsIgnoreCase("gryffindor")) {
                    iprofile.setImageResource(R.drawable.house_badge_gryffindor);
                } else if (global.housename.equalsIgnoreCase("hufflepuff")) {
                    iprofile.setImageResource(R.drawable.house_badge_hufflepuff);
                } else if (global.housename.equalsIgnoreCase("ravenclaw")) {
                    iprofile.setImageResource(R.drawable.house_badge_ravenclaw);
                } else if (global.housename.equalsIgnoreCase("slytherin")) {
                    iprofile.setImageResource(R.drawable.house_badge_slytherin);
                }
                final Query rank = mFirebaseDatabase.child("users").orderByValue();
                rank.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final TextView srank = rootView.findViewById(R.id.usrank);
                        count = 0;
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String gname = ds.getKey();
                            if (global.gamename.equalsIgnoreCase(gname)) {
                                total.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        String totalu = dataSnapshot.child("users").getValue(String.class);
                                        String user = String.valueOf(totalu);
                                        int users = Integer.parseInt(user);
                                        count = (users - count);
                                        srank.setText("" + count);
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                                break;
                            }
                            count = count + 1;
                        }
                        mDatabase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String house = dataSnapshot.child(userId).child("house").getValue(String.class);
                                if (house.equalsIgnoreCase("gryffindor")) {
                                    rankleader1 = mFirebaseDatabase.child("Gryffindor").orderByValue();
                                }
                                if (house.equalsIgnoreCase("hufflepuff")) {
                                    rankleader1 = mFirebaseDatabase.child("Hufflepuff").orderByValue();
                                }
                                if (house.equalsIgnoreCase("ravenclaw")) {
                                    rankleader1 = mFirebaseDatabase.child("Ravenclaw").orderByValue();
                                }
                                if (house.equalsIgnoreCase("slytherin")) {
                                    rankleader1 = mFirebaseDatabase.child("Slytherin").orderByValue();
                                }

                                rankleader1.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        hrname.clear();
                                        hname.clear();
                                        hrscore.clear();
                                        hscore.clear();
                                        hurank.clear();
                                        hrurank.clear();
                                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                            String gamename = ds.getKey();
                                            int uscore = ds.getValue(Integer.class);
                                            hname.add(gamename);
                                            hscore.add(uscore);
                                        }
                                        for (int i = hname.size() - 1; i >= 0; i--) {
                                            hrname.add(hname.get(i));
                                            hrscore.add(hscore.get(i));
                                            hurank.add(i + 1);
                                        }
                                        for (int i = hurank.size() - 1; i >= 0; i--) {
                                            hrurank.add(hurank.get(i));
                                        }
                                        for (int j = 0; j <= hrurank.size() - 1; j++) {
                                         if(global.gamename.equalsIgnoreCase(hrname.get(j))){
                                             setrank.setText(hrurank.get(j)+"");
                                         }
                                        }

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                    }
                                });
                            }


                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase.onDisconnect();
        mFirebaseDatabase.onDisconnect();
        total.onDisconnect();
        return rootView;
    }
}