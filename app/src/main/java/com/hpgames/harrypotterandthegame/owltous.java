package com.hpgames.harrypotterandthegame;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class owltous extends Fragment {
    EditText sub,body;
    Button send;
    String s,b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.owltous,container,false);
        sub=rootView.findViewById(R.id.subject);
        body=rootView.findViewById(R.id.body);
        send=rootView.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s=sub.getText().toString();
                b=body.getText().toString();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"dhairya.sharma532@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, s);
                i.putExtra(Intent.EXTRA_TEXT   , b);
                try {
                    startActivity(Intent.createChooser(i, "Send OWL..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return rootView;
    }
}