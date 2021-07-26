package com.hpgames.harrypotterandthegame;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class home extends Fragment {

    Button bdormitory,blearn,bowl, bleaderboard, bowlus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.home,container,false);

        bdormitory=rootView.findViewById(R.id.buttondormitory);
        blearn=rootView.findViewById(R.id.buttonlearn);
        bowl=rootView.findViewById(R.id.buttonowl);
        bleaderboard =rootView.findViewById(R.id.buttonleaderboard);
        bowlus =rootView.findViewById(R.id.buttonowlus);


        bdormitory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maingame.mTitle.setText("Your Dormitory");
                maingame.mViewPager.setCurrentItem(1);
            }
        });
        blearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maingame.mTitle.setText("Learn");
                maingame.mViewPager.setCurrentItem(2);
            }
        });
        bowl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                maingame.mTitle.setText("O.W.L.");
                maingame.mViewPager.setCurrentItem(4);

            }
        });
        bleaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maingame.mTitle.setText("Leaderboard");
                maingame.mViewPager.setCurrentItem(8);
            }
        });
        bowlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maingame.mViewPager.setCurrentItem(12);
                maingame.mTitle.setText("OWL US");
            }
        });
        return rootView;
    }
}