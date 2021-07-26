package com.hpgames.harrypotterandthegame;



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


public class leaderboard extends Fragment {
    Button school,house;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;
    String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.leaderboard,container,false);
        school=rootView.findViewById(R.id.hogwarts);
        house=rootView.findViewById(R.id.house);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseInstance.getReference("users");

        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();
        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maingame.mTitle.setText("Hogwarts Leaderboard");
                maingame.mViewPager.setCurrentItem(9);
            }
        });
        house.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                global.sflag=1;
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String house=dataSnapshot.child(userId).child("house").getValue(String.class);
                        if(house.equalsIgnoreCase("gryffindor")){
                            maingame.mTitle.setText("Gryffindor Leaderboard");
                            leaderhouse.rootView.setBackgroundResource(R.drawable.gryffindor_background);
                        }
                        if(house.equalsIgnoreCase("hufflepuff")){
                            maingame.mTitle.setText("Hufflepuff Leaderboard");
                            leaderhouse.rootView.setBackgroundResource(R.drawable.hufflepuff_background);
                        }
                        if(house.equalsIgnoreCase("ravenclaw")){
                            maingame.mTitle.setText("Ravenclaw Leaderboard");
                            leaderhouse.rootView.setBackgroundResource(R.drawable.ravenvlaw_background);
                        }
                        if(house.equalsIgnoreCase("slytherin")){
                            maingame.mTitle.setText("Slytherin Leaderboard");
                            leaderhouse.rootView.setBackgroundResource(R.drawable.slytherin_backgroung);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                maingame.mViewPager.setCurrentItem(10);
            }
        });

        return rootView;

    }


}
