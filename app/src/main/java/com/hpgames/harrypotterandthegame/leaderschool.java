package com.hpgames.harrypotterandthegame;

import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class leaderschool extends Fragment {
    TextView rank,gamename,galleons;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebaseInstance;
    ArrayList<String> name = new ArrayList<String>();
    ArrayList<Integer> score = new ArrayList<Integer>();
    ArrayList<Integer> urank = new ArrayList<Integer>();
    public static ArrayList<String> rname = new ArrayList<String>();
    ArrayList<Integer> rscore = new ArrayList<Integer>();
    ArrayList<Integer> rurank = new ArrayList<Integer>();
    Query rankleader;
    private ListView listView;
    private listviewAdapter mAdapter;
    public static ArrayList<listleader> leaderList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.leaderschool,container,false);
        rank=rootView.findViewById(R.id.rank);
        gamename=rootView.findViewById(R.id.gamename);
        galleons=rootView.findViewById(R.id.galleons);
        rank.setPaintFlags(rank.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        gamename.setPaintFlags(gamename.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        galleons.setPaintFlags(galleons.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseInstance.getReference("rank");
        rankleader = mDatabase.child("users").orderByValue();
        rankleader.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                rname.clear();
                name.clear();
                rscore.clear();
                score.clear();
                urank.clear();
                rurank.clear();
                leaderList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String gamename = ds.getKey();
                    int uscore=ds.getValue(Integer.class);
                    name.add(gamename);
                    score.add(uscore);
                }
                for (int i = name.size() - 1; i >= 0; i--) {
                    rname.add(name.get(i));
                    rscore.add(score.get(i));
                    urank.add(i + 1);
                }
                for (int i = urank.size() - 1; i >= 0; i--) {
                    rurank.add(urank.get(i));
                }
                listView = (ListView) rootView.findViewById(R.id.schoollist);

                for(int i=0;i<rurank.size();i++){
                    leaderList.add(new listleader(rurank.get(i), rname.get(i),rscore.get(i)));
                }
                mAdapter = new listviewAdapter(getContext(),leaderList);
                    listView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        return rootView;
    }
}