package com.gmonetix.slambook.user_registration;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gmonetix.slambook.R;

public class UserRegistrationActivity extends AppCompatActivity {

    private de.hdodenhof.circleimageview.R circleimageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // circleimageview = findViewById(R.id.iv_profile_image);
    }
}
