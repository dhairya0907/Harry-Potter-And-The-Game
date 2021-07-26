package com.hpgames.harrypotterandthegame;

import com.google.firebase.database.IgnoreExtraProperties;



@IgnoreExtraProperties
public class User {

    public String fname="";
    public String lname="";
    public String gender="";
    public String email="";
    public String gamename="";
    public String activityno="";
    public int score=0;
    public String year="";
    public String yearpassed="";

    public User() {

    }

    public User(String fname,String lname,String gender, String email, String gamename, String activityno, int score,String year,String yearpassed) {
        this.fname = fname;
        this.lname=lname;
        this.gender=gender;
        this.email = email;
        this.gamename=gamename;
        this.activityno=activityno;
        this.score=score;
        this.year=year;
        this.yearpassed=yearpassed;
    }

}

