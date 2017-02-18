package com.gmonetix.slambook;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.gmonetix.slambook.user_login.UserLoginActivity;

public class SplashScreen extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView ivFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SplashScreen.this.setTitle("");

        ivFrame = (ImageView) findViewById(R.id.splash_screen_frame_iv);
        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.frame1),1000);
        animation.addFrame(getResources().getDrawable(R.drawable.frame2),1000);
        animation.addFrame(getResources().getDrawable(R.drawable.frame3),1000);
        animation.setOneShot(false);
        ivFrame.setBackgroundDrawable(animation);
        ivFrame.setScaleType(ImageView.ScaleType.FIT_XY);
        animation.start();

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(SplashScreen.this, UserLoginActivity.class));
                    finish();
                }

            }
        };
        timer.start();

    }
}
