package com.hpgames.harrypotterandthegame;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_register extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    EditText fname,lname,remail,rpassword,gamename,lemail,lpassword,input;
    RadioGroup gender;
    RadioButton mf;
    ImageView loginbackground;
    Button l,j;
    TextView lo,p,n,fullname,lastname;



    int selectedId;

    private FirebaseAuth mAuth;
    private DatabaseReference mFirebaseDatabase, rank,total;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.harry_potter_theme_song);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (CustomViewPager) findViewById(R.id.loginregisterview);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");
    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
                switch (position){
                    case 0:
                        login l=new login();
                        return l;
                    case 1:
                        gamename g=new gamename();
                        return g;
                    case 2:
                        register r=new register();
                        return r;
                    case 3:
                        letter le=new letter();
                        return le;

                    default:
                            return null;
                }
        }
        @Override
        public int getCount() {
            return 4;
        }
    }

    public void gotologin(View view) {
        lemail=findViewById(R.id.lenteremail);
        lpassword=findViewById(R.id.lenterpassword);
        lemail.setText("");
        lpassword.setText("");
        mViewPager.setCurrentItem(0);
    }
    public void lgotogamename(View view) {
        gamename=findViewById(R.id.entergamename);
        gamename.setText("");
        mViewPager.setCurrentItem(1);
    }
    public  void ggotoregister(View view){
        gamename=findViewById(R.id.entergamename);
        fname=findViewById(R.id.enterfirstname);
        lname=findViewById(R.id.enterlastname);
        remail=findViewById(R.id.renteremail);
        rpassword=findViewById(R.id.renterpassword);
        gender=findViewById(R.id.gender);

        fname.setText("");
        lname.setText("");
        remail.setText("");
        rpassword.setText("");
        gender.clearCheck();

        global.gamename=gamename.getText().toString();
        if (TextUtils.isEmpty(global.gamename)) {
            Toast.makeText(getApplicationContext(), "Enter GameName", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
            DatabaseReference userRef = rootRef.child("users");
            ValueEventListener eventListener = new ValueEventListener() {
                int flag = 0;

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String gamename = ds.child("gamename").getValue(String.class);
                        if (global.gamename.equalsIgnoreCase(gamename)) {
                            Toast.makeText(login_register.this, "GameName Not Available ", Toast.LENGTH_SHORT).show();
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED){
                            requestPermissions(
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    1000);
                        }
                        else {
                            mViewPager.setCurrentItem(2);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            userRef.addListenerForSingleValueEvent(eventListener);
        }
    }

    public void rgotologin(View view){
        fname=findViewById(R.id.enterfirstname);
        lname=findViewById(R.id.enterlastname);
        remail=findViewById(R.id.renteremail);
        rpassword=findViewById(R.id.renterpassword);
        gender=findViewById(R.id.gender);

        global.fname=fname.getText().toString();
        global.lname=lname.getText().toString();
        global.email=remail.getText().toString();
        global.password=rpassword.getText().toString();
        selectedId = gender.getCheckedRadioButtonId();
        mf = findViewById(selectedId);
        if (selectedId == R.id.female)
            global.gender = "Female";
        else
            global.gender = "Male";

        if (TextUtils.isEmpty(global.email)) {
            Toast.makeText(getApplicationContext(), "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(global.password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (global.password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(global.email, global.password)
                .addOnCompleteListener(login_register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(login_register.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(login_register.this, "Successfully Joined ", Toast.LENGTH_SHORT).show();
                            createUser(global.fname, global.lname, global.gender, global.email,global.gamename);
                        }
                    }
                });
    }
    public void gotowelcometohogwarts(View view) {

        mAuth = FirebaseAuth.getInstance();
        lemail=findViewById(R.id.lenteremail);
        lpassword=findViewById(R.id.lenterpassword);
        loginbackground=findViewById(R.id.loginbackground);
        l=findViewById(R.id.loginnow);
        j=findViewById(R.id.ljoinnow);
        lo=findViewById(R.id.login);
        p=findViewById(R.id.please);
        n=findViewById(R.id.newtohogwarts);
        String email = lemail.getText().toString();
        final String password = lpassword.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(login_register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            if (password.length() < 6) {
                                Toast.makeText(getApplicationContext(), "Password cannot be less then 6 character", Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong Information", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } else {
                            loginbackground.setImageResource(R.drawable.platform);
                            lemail.setVisibility(View.INVISIBLE);
                            lpassword.setVisibility(View.INVISIBLE);
                            l.setEnabled(false);
                            l.setVisibility(View.INVISIBLE);
                            j.setEnabled(false);
                            j.setVisibility(View.INVISIBLE);
                            lo.setVisibility(View.INVISIBLE);
                            p.setVisibility(View.INVISIBLE);
                            n.setVisibility(View.INVISIBLE);
                            login.forgot.setVisibility(View.INVISIBLE);
                            mAuth = FirebaseAuth.getInstance();
                            mFirebaseInstance = FirebaseDatabase.getInstance();
                            mFirebaseDatabase = mFirebaseInstance.getReference("users");
                            FirebaseUser user = mAuth.getCurrentUser();
                            userId = user.getUid();

                        }

                        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                    String activityno=dataSnapshot.child(userId).child("activityno").getValue(String.class);
                                    global.activityno=String.valueOf(activityno);
                                    final ProgressDialog dialog = ProgressDialog.show(login_register.this, "", "Taking You To Hogwarts! ");
                                    dialog.show();
                                    Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        public void run() {
                                            dialog.dismiss();
                                            if(global.activityno.equalsIgnoreCase("0")) {
                                                startActivity(new Intent(login_register.this, welcometohogwarts.class));
                                            }
                                            else if(global.activityno.equalsIgnoreCase("1")){
                                                startActivity(new Intent(login_register.this, maingame.class));
                                            }
                                        }
                                    }, 3000);

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });
    }

    public void lgotologin(View view) {
        mViewPager.setCurrentItem(0);
    }
    private void createUser(String fname, String lname, String gender,String email,String gamename) {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userId=currentFirebaseUser.getUid();
        String activityno="0";
        int score=0;
        String year="One";
        String yearpassed="";

        final User user = new User(fname,lname,gender, email,gamename,activityno,score,year,yearpassed);

        mFirebaseDatabase.child(userId).setValue(user);

            fullname=findViewById(R.id.fullname);
            lastname=findViewById(R.id.lastname);

            mFirebaseInstance = FirebaseDatabase.getInstance();
            mFirebaseDatabase = mFirebaseInstance.getReference("users");
            rank =mFirebaseInstance.getReference("rank");
            total=mFirebaseInstance.getReference("total");
            currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            userId=currentFirebaseUser.getUid();

            mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String gname = dataSnapshot.child(userId).child("gamename").getValue(String.class);
                    int score = dataSnapshot.child(userId).child("score").getValue(Integer.class);
                    String house=dataSnapshot.child(userId).child("house").getValue(String.class);
                    global.housename=String.valueOf(house);
                    global.gamename = String.valueOf(gname);
                    global.score=Integer.valueOf(score);
                    total.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String totalu = dataSnapshot.child("users").getValue(String.class);
                            global.tuser=String.valueOf(totalu);
                            int users=Integer.parseInt(global.tuser);
                            users=users+1;
                            global.tuser=String.valueOf(users);
                            total.child("users").setValue(global.tuser);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                    rank.child("users").child(global.gamename).setValue(global.score);
                    String lname=dataSnapshot.child(userId).child("lname").getValue(String.class);
                    String fname=dataSnapshot.child(userId).child("fname").getValue(String.class);
                    String gender=dataSnapshot.child(userId).child("gender").getValue(String.class);
                    global.gender=String.valueOf(gender);
                    global.lname=String.valueOf(lname);
                    global.fname=String.valueOf(fname);
                    global.lname = global.lname.substring(0, 1).toUpperCase() + global.lname.substring(1).toLowerCase();
                    global.fname = global.fname.substring(0, 1).toUpperCase() + global.fname.substring(1).toLowerCase();
                    if(global.gender.equalsIgnoreCase("Male")) {
                        fullname.setText("Mr. "+global.fname+" "+global.lname);
                        lastname.setText("Mr. "+global.lname);
                    }
                    if(global.gender.equalsIgnoreCase("Female")) {
                        fullname.setText("Ms. "+global.fname+" "+global.lname);
                        lastname.setText("Ms. "+global.lname);
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });
            mViewPager.setCurrentItem(3);
        }
    public void forgotpassword(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(login_register.this);
        builder.setTitle("Enter your Email");

        input = new EditText(login_register.this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                String emailAddress = input.getText().toString();

                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(login_register.this, "Email Sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
    }

