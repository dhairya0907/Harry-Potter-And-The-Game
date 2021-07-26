package com.hpgames.harrypotterandthegame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;



public class CardStackAdapter extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Spot> spots;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    public CardStackAdapter(Context context, List<Spot> spots) {
        this.inflater = LayoutInflater.from(context);
        this.spots = spots;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("learn");
        return new ViewHolder(inflater.inflate(R.layout.learn_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (global.cyear.equalsIgnoreCase("didiyouknow")==false) {
                    global.y = global.y + 1;
                    if (global.cyear.equalsIgnoreCase("yearone")) {
                        holder.name.setText("Harry Potter and the Philosopher's Stone");
                    } else if (global.cyear.equalsIgnoreCase("yeartwo")) {
                        holder.name.setText("Harry Potter and the Chamber of Secrets");
                    } else if (global.cyear.equalsIgnoreCase("yearthree")) {
                        holder.name.setText("Harry Potter and the Prisoner of Azkaban");
                    } else if (global.cyear.equalsIgnoreCase("yearfour")) {
                        holder.name.setText("Harry Potter and the Goblet of Fire");
                    } else if (global.cyear.equalsIgnoreCase("yearfive")) {
                        holder.name.setText("Harry Potter and the Order of the Phoenix");
                    } else if (global.cyear.equalsIgnoreCase("yearsix")) {
                        holder.name.setText("Harry Potter and the Half-Blood Prince");
                    } else if (global.cyear.equalsIgnoreCase("yearseven")) {
                        holder.name.setText("Harry Potter and the Deathly Hallows");
                    }
                    String j = String.valueOf(global.y);
                    String value = dataSnapshot.child(global.cyear).child(j).getValue(String.class);
                    String value1 = String.valueOf(value);
                    holder.content.setText(value1);

                    if (global.y == 16) {
                        holder.name.setText("");
                        if (global.cyear.equalsIgnoreCase("yearone")) {
                            holder.content.setText("Now You Know All the Facts About Harry Potter and the Philosopher's Stone");
                        }
                        if (global.cyear.equalsIgnoreCase("yeartwo")) {
                            holder.content.setText("Now You Know All the Facts About Harry Potter and the Chamber of Secrets");
                        }
                        if (global.cyear.equalsIgnoreCase("yearthree")) {
                            holder.content.setText("Now You Know All the Facts About Harry Potter and the Prisoner of Azkaban");
                        }
                        if (global.cyear.equalsIgnoreCase("yearfour")) {
                            holder.content.setText("Now You Know All the Facts About Harry Potter and the  Goblet of Fire");
                        }
                        if (global.cyear.equalsIgnoreCase("yearfive")) {
                            holder.content.setText("Now You Know All the Facts About Harry Potter and the Order of the Phoenix");
                        }
                        if (global.cyear.equalsIgnoreCase("yearsix")) {
                            holder.content.setText("Now You Know All the Facts About Harry Potter and the Half-Blood Prince");
                        }
                        if (global.cyear.equalsIgnoreCase("yearseven")) {
                            holder.content.setText("Now You Know All the Facts About Harry Potter and the Deathly Hallows");
                        }
                    }
                    if (global.y == 17) {
                        holder.name.setText("");
                        holder.content.setText("");
                    }
                    if (global.y == 19) {
                        maingame.mViewPager.setCurrentItem(2);
                    }
                }
                if(global.cyear.equalsIgnoreCase("didyouknow")){
                        holder.name.setText("Did You Know");
                            String j = String.valueOf(global.d);
                            String value = dataSnapshot.child(global.cyear).child(j).getValue(String.class);
                            String value1 = String.valueOf(value);
                            holder.factnumber.setText("Fact "+global.d+" of 35");
                            holder.content.setText(value1);
                            global.d=global.d+1;
                        if (global.d==global.count&&global.count<=35){
                            holder.factnumber.setText("");
                            holder.name.setText("");
                            holder.content.setText("Pass Next Year To See Next Five Did You Know Facts");
                        }
                        if (global.d==global.count&&global.count>35){
                        holder.factnumber.setText("");
                        holder.name.setText("");
                        holder.content.setText("Now You Know All The Did You Know Facts About Harry Potter World");
                        }
                        if (global.d==global.count+1){
                            holder.factnumber.setText("");
                            holder.name.setText("");
                            holder.content.setText("");
                        }
                        if (global.d==global.count+3){
                            maingame.mViewPager.setCurrentItem(2);
                        }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return spots.size();
    }

    public void addSpots(List<Spot> spots) {
        this.spots.addAll(spots);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView content;
        TextView factnumber;
        ViewHolder(View view) {
            super(view);
            this.name = view.findViewById(R.id.item_heading);
            this.content = view.findViewById(R.id.item_content);
            this.factnumber=view.findViewById(R.id.factnumber);
        }
    }

}
