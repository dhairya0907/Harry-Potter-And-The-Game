package com.hpgames.harrypotterandthegame;


import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class leaderhouse extends Fragment {
    public static View rootView;
    TextView rank,gamename,galleons;
    private DatabaseReference mDatabase2,mDatabase1;
    private FirebaseDatabase mFirebaseInstance;
    ArrayList<String> hname = new ArrayList<String>();
    ArrayList<Integer> hscore = new ArrayList<Integer>();
    ArrayList<Integer> hurank = new ArrayList<Integer>();
    public static ArrayList<String> hrname = new ArrayList<String>();
    ArrayList<Integer> hrscore = new ArrayList<Integer>();
    ArrayList<Integer> hrurank = new ArrayList<Integer>();
    Query rankleader1;
    private ListView hlistView;
    private listviewAdapter mAdapter;
    private FirebaseAuth mAuth;
    String userId;
    public static ArrayList<listleader> leaderList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      rootView = inflater.inflate(R.layout.leardhouse,container,false);
        rank=rootView.findViewById(R.id.rank);
        gamename=rootView.findViewById(R.id.gamename);
        galleons=rootView.findViewById(R.id.galleons);
        rank.setPaintFlags(rank.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        gamename.setPaintFlags(gamename.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        galleons.setPaintFlags(galleons.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        if(global.sflag==1) {
            mAuth = FirebaseAuth.getInstance();
            mFirebaseInstance = FirebaseDatabase.getInstance();
            mDatabase1 = mFirebaseInstance.getReference("users");
            FirebaseUser user = mAuth.getCurrentUser();
            userId = user.getUid();
            mDatabase2 = mFirebaseInstance.getReference("rank");

            mDatabase1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String house = dataSnapshot.child(userId).child("house").getValue(String.class);

                    if (house.equalsIgnoreCase("gryffindor")) {
                        rankleader1 = mDatabase2.child("Gryffindor").orderByValue();
                    }
                    if (house.equalsIgnoreCase("hufflepuff")) {
                        rankleader1 = mDatabase2.child("Hufflepuff").orderByValue();
                    }
                    if (house.equalsIgnoreCase("ravenclaw")) {
                        rankleader1 = mDatabase2.child("Ravenclaw").orderByValue();
                    }
                    if (house.equalsIgnoreCase("slytherin")) {
                        rankleader1 = mDatabase2.child("Slytherin").orderByValue();
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
                            leaderList.clear();
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
                            hlistView = (ListView) rootView.findViewById(R.id.schoollist);
                            for (int j = 0; j <= hrurank.size() - 1; j++) {
                                leaderList.add(new listleader(hrurank.get(j), hrname.get(j), hrscore.get(j)));
                            }
                            mAdapter = new listviewAdapter(getContext(), leaderList);
                            hlistView.setAdapter(mAdapter);

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
            global.sflag=0;

        }
        return rootView;

    }
}