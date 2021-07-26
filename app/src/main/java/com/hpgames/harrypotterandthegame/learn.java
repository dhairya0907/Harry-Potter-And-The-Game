package com.hpgames.harrypotterandthegame;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;

import java.util.ArrayList;
import java.util.List;


public class learn extends Fragment implements CardStackListener {
    private static CardStackLayoutManager manager;
    private static CardStackAdapter adapter;
    private static CardStackView cardStackView;
    static View  rootView;
    String learn;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.learn, container, false);
        //setupCardStackView();
        setupButton();
        return rootView;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.learn_menu, menu);
        return true;
    }

    private MenuInflater getMenuInflater() {
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                refresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {
    }

    @Override
    public void onCardSwiped(Direction direction) {
        if (manager.getTopPosition() == adapter.getItemCount()-34) {
            adapter.addSpots(createSpots());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCardRewound() {
    }

    @Override
    public void onCardCanceled() {
    }

    public void setupCardStackView() {
        refresh();
    }

    public void setupButton() {
    }

    public static void refresh() {
        Context context = staticcontext.getContext();
        manager = new CardStackLayoutManager(context);
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(true);
        adapter = new CardStackAdapter(context, createSpots());
        cardStackView = rootView.findViewById(R.id.card_stack_view);
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
    }
    private static List<Spot> createSpots() {
        List<Spot> spots = new ArrayList<>();
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        spots.add(new Spot());
        return spots;
    }
}
