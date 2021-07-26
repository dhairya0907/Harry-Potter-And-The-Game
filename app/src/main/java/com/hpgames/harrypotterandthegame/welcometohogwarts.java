package com.hpgames.harrypotterandthegame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class welcometohogwarts extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    TextView speech,tap,hquestion,hatspeech,gotomain;
    ImageView housebackgroung;
    Button choice1,choice2,choice3,choice4;
    String s1,s2,s3,s4,s5,s6,s7,s8,s9,q,o1,o2,o3,o4,ca,housename="",questionnumber="0",userId="";
    int count=1,flag=0,i=0,no,j=1,alert=0,hatspeechcount=0;
    Dialog houseDialog;
    private DatabaseReference mDatabase,rank,total;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseAuth mAuth;
    ArrayList<Integer> number = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcometohogwarts);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (CustomViewPager) findViewById(R.id.welcometohogwartsview);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        blink();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    mcgonagallspeech m=new mcgonagallspeech();
                    return m;
                case 1:
                    the_sorting_ceremony t=new the_sorting_ceremony();
                    return t;
                case 2:
                    house h=new house();
                    return h;

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    public void gotothesortingceremony(View view) {
        speech=findViewById(R.id.speech);
        tap=findViewById(R.id.taptocintinue);

        s1="\"The start-of-term banquet will begin shortly,\"\"";
        s2 = "\"The Sorting is a very important ceremony because, while you are here,\"";
        s3 = "\"your House will be something like your family within Hogwarts.\"";
        s4 = "\"The four houses are called\"";
        s5 = "\"Gryffindor\"";
        s6 = "\"Hufflepuff\"";
        s7 = "\"Ravenclaw\"";
        s8 = "\"and Slytherin.\"";
        s9 = "\"The Sorting Ceremony will take place in a few minutes in front of the rest of the school. " +
                "I suggest you all smarten yourselves up as much as you can while you are waiting.\"";
        if(count==1){
            count=count+1;
            tap.setText("");
            flag=1;
            speech.setText(s1);
        }
        else if(count==2){
            count=count+1;
            speech.setText(s2);
        }
        else if(count==3){
            count=count+1;
            speech.setText(s3);
        }
        else if(count==4){
            count=count+1;
            speech.setText(s4);
        }
        else if(count==5){
            count=count+1;
            speech.setText(s5);
        }
        else if(count==6){
            count=count+1;
            speech.setText(s6);
        }
        else if(count==7){
            count=count+1;
            speech.setText(s7);
        }
        else if(count==8){
            count=count+1;
            speech.setText(s8);
        }
        else if(count==9){
            count=count+1;
            speech.setText(s9);
        }
        else {
            mViewPager.setCurrentItem(1);
        }
    }

    public void selecthouse(View view) {
        houseDialog = new Dialog(this);
        houseDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        houseDialog.setContentView(getLayoutInflater().inflate(R.layout.dialog_houses
                , null));
        houseDialog.show();
    }

    public void gryffindor(View view) {
        houseDialog.hide();
        housebackgroung=findViewById(R.id.housebackroud);
        housebackgroung.setImageResource(R.drawable.gryffindor_background);
        mViewPager.setCurrentItem(2);
        housename="gryffindor";
        question();
    }


    public void hufflepuff(View view) {
        houseDialog.hide();
        housebackgroung=findViewById(R.id.housebackroud);
        housebackgroung.setImageResource(R.drawable.hufflepuff_background);
        mViewPager.setCurrentItem(2);
        housename="hufflepuff";
        question();
    }

    public void ravenclaw(View view) {
        houseDialog.hide();
        housebackgroung=findViewById(R.id.housebackroud);
        housebackgroung.setImageResource(R.drawable.ravenvlaw_background);
        mViewPager.setCurrentItem(2);
        housename="ravenclaw";
        question();
    }

    public void slytherin(View view) {
        houseDialog.hide();
        housebackgroung=findViewById(R.id.housebackroud);
        housebackgroung.setImageResource(R.drawable.slytherin_backgroung);
        mViewPager.setCurrentItem(2);
        housename="slytherin";
        question();
    }

    private void blink() {
        if (flag == 0) {
            final Handler handler = new Handler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int timeToBlink = 1000;    //in milissegunds
                    try {
                        Thread.sleep(timeToBlink);
                    } catch (Exception e) {
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            tap = findViewById(R.id.taptocintinue);
                            if (tap.getVisibility() == View.VISIBLE) {
                                tap.setVisibility(View.INVISIBLE);
                            } else {
                                tap.setVisibility(View.VISIBLE);
                            }
                            blink();
                        }
                    });
                }
            }).start();
        }
    }
    public void question()
    {
        hquestion=findViewById(R.id.housequestion);
        choice1=findViewById(R.id.hchoice1);
        choice2=findViewById(R.id.hchoice2);
        choice3=findViewById(R.id.hchoice3);
        choice4=findViewById(R.id.hchoice4);
        hatspeech=findViewById(R.id.hatspeech);
        hatspeech.setEnabled(false);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userId=user.getUid();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseInstance.getReference("questions");
        if(alert==0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(welcometohogwarts.this);
            builder.setTitle("You Have Selected "+housename.substring(0, 1).toUpperCase()+housename.substring(1)+".");
            builder.setMessage("Now You Have To Prove That You Belong In "+housename.substring(0, 1).toUpperCase()+housename.substring(1)+"."+"\nSorting Hat will Now" +
                    " Ask You Some Questions About "+housename.substring(0, 1).toUpperCase()+housename.substring(1)+".\nIf You Will be Able To Give All Answers Correctly then" +
                    ", You Will be Placed In The House " +
                    "Of Your Choice Or Else The Sorting Hat Will Put You In The House Of His Choice\n\nAre You Ready?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alert=1;
                    question();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mViewPager.setCurrentItem(1);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
        }
        if(alert==1) {
            if (i > 3) {
                if(hatspeechcount==0) {
                    if(global.housepoints!=4) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(welcometohogwarts.this);
                        builder.setMessage("As You Are Failed To Prove Your Place In "
                                +housename.substring(0, 1).toUpperCase()+housename.substring(1)
                                +"."+"\n\nNow The Sorting Hat Will Place You In one Of The House Of His Choice!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hatspeechfunction();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(false);
                    }
                    if(global.housepoints==4) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(welcometohogwarts.this);
                        builder.setMessage("As You Were Able To Prove Your Place In "
                                +housename.substring(0, 1).toUpperCase()+housename.substring(1)
                                +"."+"\n\nThe Sorting Hat Will Place You In The House Of Your Choice!!");
                        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                hatspeechcount=8;
                                question();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(false);
                    }
                }
                mDatabase = mFirebaseInstance.getReference("users");
                choice1.setEnabled(false);
                choice2.setEnabled(false);
                choice3.setEnabled(false);
                choice4.setEnabled(false);
                if (hatspeechcount == 8) {
                    if (global.housepoints == 4) {
                        if (housename.equals("gryffindor")) {
                            mDatabase.child(userId).child("house").setValue("Gryffindor");
                            total();
                            housebackgroung.setImageResource(R.drawable.selected_in_gryffindor);
                            clickonlogo();
                        }
                        if (housename.equals("hufflepuff")) {
                            mDatabase.child(userId).child("house").setValue("Hufflepuff");
                            total();
                            housebackgroung.setImageResource(R.drawable.selected_in_hufflepuff);
                            clickonlogo();
                        }
                        if (housename.equals("ravenclaw")) {
                            mDatabase.child(userId).child("house").setValue("Ravenclaw");
                            total();
                            housebackgroung.setImageResource(R.drawable.selected_in_ravenclaw);
                            clickonlogo();
                        }
                        if (housename.equals("slytherin")) {
                            mDatabase.child(userId).child("house").setValue("Slytherin");
                            total();
                            housebackgroung.setImageResource(R.drawable.selected_in_slytherin);
                            clickonlogo();
                        }
                    }
                    if (global.housepoints != 4) {
                        for (int a = 1; a <= 4; a++) {
                            number.add(a);
                        }
                        Collections.shuffle(number);
                        no = number.get(j);
                        if (no == 1) {
                            if (housename.equals("gryffindor")) {
                                j = j + 1;
                                question();
                            } else {
                                mDatabase.child(userId).child("house").setValue("Gryffindor");
                                total();
                                housebackgroung.setImageResource(R.drawable.selected_in_gryffindor);
                                clickonlogo();
                            }
                        }
                        if (no == 2) {
                            if (housename.equals("huff1epuff")) {
                                j = j + 1;
                                question();
                            } else {
                                mDatabase.child(userId).child("house").setValue("Hufflepuff");
                                total();
                                housebackgroung.setImageResource(R.drawable.selected_in_hufflepuff);
                                clickonlogo();
                            }
                        }
                        if (no == 3) {
                            if (housename.equals("ravenclaw")) {
                                j = j + 1;
                                question();
                            } else {
                                mDatabase.child(userId).child("house").setValue("Ravenclaw");
                                total();
                                housebackgroung.setImageResource(R.drawable.selected_in_ravenclaw);
                                clickonlogo();
                            }
                        }
                        if (no == 4) {
                            if (housename.equals("slytherin")) {
                                j = j + 1;
                                question();
                            } else {
                                mDatabase.child(userId).child("house").setValue("Slytherin");
                                total();
                                housebackgroung.setImageResource(R.drawable.selected_in_slytherin);
                                clickonlogo();
                            }
                        }
                    }
                }
            }
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    q = dataSnapshot.child("houses").child(housename).child(questionnumber).child("question").getValue(String.class);
                    o1 = dataSnapshot.child("houses").child(housename).child(questionnumber).child("o1").getValue(String.class);
                    o2 = dataSnapshot.child("houses").child(housename).child(questionnumber).child("o2").getValue(String.class);
                    o3 = dataSnapshot.child("houses").child(housename).child(questionnumber).child("o3").getValue(String.class);
                    o4 = dataSnapshot.child("houses").child(housename).child(questionnumber).child("o4").getValue(String.class);
                    ca = dataSnapshot.child("houses").child(housename).child(questionnumber).child("ca").getValue(String.class);
                    hquestion.setText(q);
                    choice1.setText(o1);
                    choice2.setText(o2);
                    choice3.setText(o3);
                    choice4.setText(o4);
                    i = i + 1;
                    questionnumber = Integer.toString(i);
                    choice1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (choice1.getText().toString().equals(ca)) {
                                global.housepoints = global.housepoints + 1;
                                Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                                question();
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                                question();
                            }
                        }
                    });
                    choice2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (choice2.getText().toString().equals(ca)) {
                                global.housepoints = global.housepoints + 1;
                                Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                                question();
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                                question();
                            }
                        }
                    });
                    choice3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (choice3.getText().toString().equals(ca)) {
                                global.housepoints = global.housepoints + 1;
                                Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                                question();
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                                question();
                            }
                        }
                    });
                    choice4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (choice4.getText().toString().equals(ca)) {
                                global.housepoints = global.housepoints + 1;
                                Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                                question();
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                                question();
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }
    public void hatspeechfunction() {
        hatspeech=findViewById(R.id.hatspeech);
        if(hatspeechcount==0) {
            hatspeechcount=1;
            housebackgroung.setImageResource(R.color.black);
            hatspeech.setText("Difficult. Very difficult....\n\n\n\nTap To Continue");
            hatspeech.setEnabled(true);
            hatspeech.setHeight(100);
        }
        else if(hatspeechcount==1){
            hatspeechcount=2;
            hatspeech.setText("Plenty of courage, I see.");
        }
        else if (hatspeechcount==2){
            hatspeechcount=3;
            hatspeech.setText("Not a bad mind either.");
        }
        else if (hatspeechcount==3){
            hatspeechcount=4;
            hatspeech.setText("There's talent, oh my goodness, yes ...");
        }
        else if (hatspeechcount==4){
            hatspeechcount=5;
            hatspeech.setText("and a nice thirst to prove yourself, now that's interesting....");
        }
        else if (hatspeechcount==5){
            hatspeechcount=6;
            hatspeech.setText("So where shall I put you?");
        }
       else if (hatspeechcount==6){
            hatspeechcount=7;
            hatspeech.setText(" better be....");
        }
        else if(hatspeechcount==7){
            hatspeechcount=8;
            hatspeech.setText("");
            question();
        }
    }
    public void onclickhatspeechfunction(View view) {
        hatspeechfunction();
    }
    public void clickonlogo(){
        gotomain=findViewById(R.id.gotomain);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(welcometohogwarts.this);
                builder.setTitle("Click On Logo");
                builder.setMessage("Click On House Logo To Get Into Your House Dormitory.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        gotomain.setVisibility(View.VISIBLE);
                        gotomain.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                global.activityno="1";
                                mDatabase.child(userId).child("activityno").setValue(global.activityno);
                                startActivity(new Intent(welcometohogwarts.this, maingame.class));

                            }
                        });
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }
        }, 2000);

    }
    public void total(){
        rank =mFirebaseInstance.getReference("rank");
        total=mFirebaseInstance.getReference("total");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userId=user.getUid();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String gname = dataSnapshot.child(userId).child("gamename").getValue(String.class);
                int score = dataSnapshot.child(userId).child("score").getValue(Integer.class);
                String house=dataSnapshot.child(userId).child("house").getValue(String.class);
                global.housename=String.valueOf(house);
                global.gamename = String.valueOf(gname);
                global.score=Integer.valueOf(score);
                rank.child(global.housename).child(global.gamename).setValue(global.score);
                total.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String htotalu=dataSnapshot.child(global.housename.toLowerCase()).getValue(String.class);
                        String utotalh=String.valueOf(htotalu);
                        int totalhu=Integer.parseInt(utotalh);
                        totalhu=totalhu+1;
                        String totalu=String.valueOf(totalhu);
                        total.child(global.housename.toLowerCase()).setValue(totalu);
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
}
