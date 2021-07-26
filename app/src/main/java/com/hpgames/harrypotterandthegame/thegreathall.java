package com.hpgames.harrypotterandthegame;


import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class thegreathall extends Fragment {

    private Button btnSend;
    private EditText edtMessage;
    private RecyclerView rvMessage;

    private AppPreference mAppPreference;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private static DatabaseReference mDatabase;
    private static FirebaseDatabase mFirebaseInstance;
    private static FirebaseAuth mAuth;
    String userId;
    RecyclerView recyclerView;

    private FirebaseRecyclerAdapter<Message, ChatViewHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.thegreathall, container, false);
        recyclerView=rootView.findViewById(R.id.rv_chat);
        btnSend = (Button) rootView.findViewById(R.id.btn_send);
        readData(new MyCallback() {
            @Override
            public void onCallback(String value) {
                AppPreference.ugamename=value;
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.btn_send){
                    String message = edtMessage.getText().toString().trim();
                    if (!TextUtils.isEmpty(message)){
                        Map<String, Object> param = new HashMap<>();
                        readData(new MyCallback() {
                            @Override
                            public void onCallback(String value) {
                                AppPreference.ugamename=value;
                            }
                        });
                        param.put("sender", mAppPreference.getGamename());
                        param.put("message", message);

                        mDatabaseReference.child("chat").child("thegreathall")
                                .push()
                                .setValue(param)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        edtMessage.setText("");
                                    }
                                });
                    }
                }
                recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount()-1);
            }
        });

        edtMessage = (EditText) rootView.findViewById(R.id.edt_message);
        rvMessage = (RecyclerView) rootView.findViewById(R.id.rv_chat);
        rvMessage.setHasFixedSize(true);
        rvMessage.setLayoutManager(new LinearLayoutManager(getContext()));

        mAppPreference = new AppPreference(getContext());
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();

        adapter = new FirebaseRecyclerAdapter<Message, ChatViewHolder>(
                Message.class,
                R.layout.item_row_chat,
                ChatViewHolder.class,
                mDatabaseReference.child("chat").child("thegreathall")
        ) {
            @Override
            protected void populateViewHolder(ChatViewHolder viewHolder, Message model, int position) {
                viewHolder.tvMessage.setText(model.message);
                viewHolder.tvGamename.setPaintFlags(viewHolder.tvGamename.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                viewHolder.tvGamename.setText(model.sender+":");
            }
        };
        rvMessage.setAdapter(adapter);

        return rootView;
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {

        TextView tvGamename, tvMessage;

        public ChatViewHolder(View itemView) {
            super(itemView);

            tvGamename = (TextView) itemView.findViewById(R.id.tv_sender);
            tvMessage = (TextView) itemView.findViewById(R.id.tv_message);
        }
    }

    public interface MyCallback {
        void onCallback(String value);
    }
    public void readData(final MyCallback myCallback) {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseInstance.getReference("users");
        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.child(userId).child("gamename").getValue(String.class);
                myCallback.onCallback(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

}