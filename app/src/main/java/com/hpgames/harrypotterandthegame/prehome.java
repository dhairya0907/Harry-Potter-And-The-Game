package com.hpgames.harrypotterandthegame;


import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.VideoView;

public class prehome extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prehome);
        VideoView videoview =findViewById(R.id.videoView2);
        DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) videoview.getLayoutParams();
        params.width =  metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
        videoview.setLayoutParams(params);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.yeartwo);
        videoview.setVideoURI(uri);
        videoview.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(prehome.this, login_register.class);
                prehome.this.startActivity(mainIntent);
                prehome.this.finish();

            }
        }, 20000);
    }
}
