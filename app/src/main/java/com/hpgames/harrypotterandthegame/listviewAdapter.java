package com.hpgames.harrypotterandthegame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class listviewAdapter extends ArrayAdapter<listleader> {

    private Context mContext;
    private List<listleader> leaderList = new ArrayList<>();

    public listviewAdapter(Context context,  ArrayList<listleader> list) {
        super(context, 0 , list);
        mContext = context;
        leaderList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_leaderboard,parent,false);

        listleader currentMovie = leaderList.get(position);

        TextView rank = listItem.findViewById(R.id.urank);
        rank.setText(""+currentMovie.getmrank());

        TextView name = (TextView) listItem.findViewById(R.id.ugamename);
        name.setText(currentMovie.getmName());

        TextView release = (TextView) listItem.findViewById(R.id.ugalleons);
        release.setText(""+currentMovie.getmscore());

        return listItem;
    }
}
