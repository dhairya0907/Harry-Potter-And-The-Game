package com.hpgames.harrypotterandthegame;

public class listleader {


    private int mrank;
    private String mName;
    private int mscore;


    public listleader(int mrank, String mName, int mscore) {
        this.mrank = mrank;
        this.mName = mName;
        this.mscore = mscore;
    }

    public int getmrank() {
        return mrank;
    }

    public void setmrank(int mrank) {
        this.mrank = mrank;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmscore() {
        return mscore;
    }

    public void setmscore(int mscore) {
        this.mscore = mscore;
    }
}
