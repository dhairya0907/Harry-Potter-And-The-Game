package com.hpgames.harrypotterandthegame;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.concurrent.TimeUnit;


public class owl extends Fragment {

    static TextView timer;
    static TextView question;
    static TextView cquestion;
    static Button choice1;
    static Button choice2;
    static Button choice3;
    static Button choice4;
    static int i=0;
    static int wronganswer=0;
    private static DatabaseReference mDatabase,rankDatabase;
    private static FirebaseDatabase mFirebaseInstance;
    private static FirebaseAuth mAuth;
    public static TextView tquestion;
    public static CountDownTimer time;
    public static Activity a;
    static String yp="",cy="";
    static String userId;
    public static boolean isRunning=false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.owl, container, false);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseInstance.getReference("questions");

        timer = rootView.findViewById(R.id.timeleft);
        question = rootView.findViewById(R.id.questiontext);
        choice1 = rootView.findViewById(R.id.optionone);
        choice2 = rootView.findViewById(R.id.optiontwo);
        choice3 = rootView.findViewById(R.id.optionthree);
        choice4 = rootView.findViewById(R.id.optionfour);
        cquestion=rootView.findViewById(R.id.questionnumber);
        tquestion=rootView.findViewById(R.id.tquesionnumber);

        a=getActivity();


        final Vibrator v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String ca = String.valueOf(i);
                        String correctanswer = dataSnapshot.child(global.cyear).child(ca).child("ca").getValue(String.class);
                        if (choice1.getText().toString().equals(correctanswer)) {
                            global.score = global.score + 5;
                            Toast.makeText(getContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                            question(a);
                        } else {
                            v.vibrate(500);
                            global.score=global.score-5;
                            wronganswer++;
                            Toast.makeText(getContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                            question(a);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String j = String.valueOf(i);
                        String correctanswer = dataSnapshot.child(global.cyear).child(j).child("ca").getValue(String.class);
                        if (choice2.getText().toString().equals(correctanswer)) {
                            global.score = global.score + 5;
                            Toast.makeText(getContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                            question(a);
                        } else {
                            v.vibrate(500);
                            global.score=global.score-5;
                            wronganswer++;
                            Toast.makeText(getContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                            question(a);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String j = String.valueOf(i);
                        String correctanswer = dataSnapshot.child(global.cyear).child(j).child("ca").getValue(String.class);
                        if (choice3.getText().toString().equals(correctanswer)) {
                            global.score = global.score + 5;
                            Toast.makeText(getContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                            question(a);
                        } else {
                            v.vibrate(500);
                            global.score=global.score-5;
                            wronganswer++;
                            Toast.makeText(getContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                            question(a);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String j = String.valueOf(i);
                        String correctanswer = dataSnapshot.child(global.cyear).child(j).child("ca").getValue(String.class);
                        if (choice4.getText().toString().equals(correctanswer)) {
                            global.score = global.score + 5;
                            Toast.makeText(getContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
                            question(a);
                        } else {
                            v.vibrate(500);
                            global.score=global.score-5;
                            wronganswer++;
                            Toast.makeText(getContext(), "Wrong Answer", Toast.LENGTH_SHORT).show();
                            question(a);
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

    public static void question(final Activity a){
        time = new CountDownTimer(global.milisec, 1000) {
            public void onTick(long millisUntilFinished) {
                isRunning = true;
                timer.setText("    " + String.format("%d : %d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

            }

            public void onFinish() {
                isRunning = false;
                global.flag = 0;
                timer.setText(" Time Over");
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("Time's Up");
                builder.setMessage("You Were Unable To Complete Current Year In Given Time.\n\nThere For Galleons From This Year Will be Counted As 0.\n\n" +
                        "And You Have To Retake Current Year's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }
        };
        if(global.cyear.equalsIgnoreCase("year one")&&global.j>4){
            time.cancel();
            if(wronganswer>2){
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Failed");
                builder.setMessage("You Have Given More Then 2 Wrong Answers.\n\nThere For Galleons From This Year Will be Counted As 0.\n\n" +
                        "And You Have To Retake Year One's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }
            else {
                cy="Two";
                yp="one";
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Passed");
                builder.setMessage("You Have Passed Year One.\n\nThere For Galleons From This Year Will be Added To Your Total Galleons.\n\n" +
                        "And You Can Take Year Two's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        update();
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }
        }
        if(global.cyear.equalsIgnoreCase("year two")&&global.j>4){
            time.cancel();
            if(wronganswer>2){
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Failed");
                builder.setMessage("You Have Given More Then 2 Wrong Answers.\n\nThere For Galleons From This Year Will be Counted As 0.\n\n" +
                        "And You Have To Retake Year Two's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }
            else {
                cy="Three";
                yp="two";
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Passed");
                builder.setMessage("You Have Passed Year Two.\n\nThere For Galleons From This Year Will be Added To Your Total Galleons.\n\n" +
                        "And You Can Take Year Three's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        update();
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }
        }
        if(global.cyear.equalsIgnoreCase("year three")&&global.j>5){
            time.cancel();
            if(wronganswer>2){
                global.score=0;
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Failed");
                builder.setMessage("You Have Given More Then 2 Wrong Answers.\n\nThere For Galleons From This Year Will be Counted As 0.\n\n" +
                        "And You Have To Retake Year Three's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }
            else {
                cy="Four";
                yp="three";
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Passed");
                builder.setMessage("You Have Passed Year Three.\n\nThere For Galleons From This Year Will be Added To Your Total Galleons.\n\n" +
                        "And You Can Take Year Four's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        update();
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }

        }
        if(global.cyear.equalsIgnoreCase("year four")&&global.j>5){
            time.cancel();
            if(wronganswer>2){
                global.score=0;
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Failed");
                builder.setMessage("You Have Given More Then 2 Wrong Answers.\n\nThere For Galleons From This Year Will be Counted As 0.\n\n" +
                        "And You Have To Retake Year Four's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }
            else {
                cy="Five";
                yp="four";
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Passed");
                builder.setMessage("You Have Passed Year Four.\n\nThere For Galleons From This Year Will be Added To Your Total Galleons.\n\n" +
                        "And You Can Take Year Five's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        update();
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }

        }
        if(global.cyear.equalsIgnoreCase("year five")&&global.j>6){
            time.cancel();
            if(wronganswer>2){
                global.score=0;
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Failed");
                builder.setMessage("You Have Given More Then 2 Wrong Answers.\n\nThere For Galleons From This Year Will be Counted As 0.\n\n" +
                        "And You Have To Retake Year Five's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }
            else {
                cy="Six";
                yp="five";
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Passed");
                builder.setMessage("You Have Passed Year Five.\n\nThere For Galleons From This Year Will be Added To Your Total Galleons.\n\n" +
                        "And You Can Take Year Five's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        update();
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }

        }
        if(global.cyear.equalsIgnoreCase("year six")&&global.j>6){
            time.cancel();
            if(wronganswer>2){
                global.score=0;
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Failed");
                builder.setMessage("You Have Given More Then 2 Wrong Answers.\n\nThere For Galleons From This Year Will be Counted As 0.\n\n" +
                        "And You Have To Retake Year Six's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }
            else {
                cy="Seven";
                yp="six";
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Passed");
                builder.setMessage("You Have Passed Year Six.\n\nThere For Galleons From This Year Will be Added To Your Total Galleons.\n\n" +
                        "And You Can Take Year Six's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        update();
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }

        }
        if(global.cyear.equalsIgnoreCase("year seven")&&global.j>9){
            time.cancel();
            if(wronganswer>2){
                global.score=0;
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Failed");
                builder.setMessage("You Have Given More Then 2 Wrong Answers.\n\nThere For Galleons From This Year Will be Counted As 0.\n\n" +
                        "And You Have To Retake Year Seven's O.W.L.");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }
            else {
                cy="Passed All The Years";
                yp="seven";
                AlertDialog.Builder builder = new AlertDialog.Builder(a);
                builder.setTitle("You Are Passed");
                builder.setMessage("You Have Passed Year Seven.\n\nThere For Galleons From This Year Will be Added To Your Total Galleons.\n\n" +
                        "Now You Can Play Whole Game Again With Different sets Questions");
                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        update();
                        maingame.mViewPager.setCurrentItem(4);
                        maingame.mTitle.setText("O.W.L");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
            }

        }
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(global.f==1) {
                    i = global.number.get(global.j);
                }
                String s = String.valueOf(i);
                String q = dataSnapshot.child(global.cyear).child(s).child("question").getValue(String.class);
                String o1 = dataSnapshot.child(global.cyear).child(s).child("o1").getValue(String.class);
                String o2 = dataSnapshot.child(global.cyear).child(s).child("o2").getValue(String.class);
                String o3 = dataSnapshot.child(global.cyear).child(s).child("o3").getValue(String.class);
                String o4 = dataSnapshot.child(global.cyear).child(s).child("o4").getValue(String.class);
                question.setText(q);
                choice1.setText(o1);
                choice2.setText(o2);
                choice3.setText(o3);
                choice4.setText(o4);
                if(global.f==1) {
                    int tq=global.j+1;
                    cquestion.setText(""+tq);
                    global.j++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public static void update(){
        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseInstance.getReference("users");
        rankDatabase=mFirebaseInstance.getReference("rank");
        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String passedyear=dataSnapshot.child(userId).child("yearpassed").getValue(String.class);
                int score=dataSnapshot.child(userId).child("score").getValue(Integer.class);
                String house=dataSnapshot.child(userId).child("house").getValue(String.class);
                String gamename=dataSnapshot.child(userId).child("gamename").getValue(String.class);

                mDatabase.child(userId).child("score").setValue(global.score+score);
                mDatabase.child(userId).child("yearpassed").setValue(passedyear+yp);
                mDatabase.child(userId).child("year").setValue(cy);
                rankDatabase.child("users").child(gamename).setValue(global.score+score);
                rankDatabase.child(house).child(gamename).setValue(global.score+score);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}